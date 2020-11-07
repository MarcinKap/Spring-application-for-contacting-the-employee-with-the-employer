package com.example.Project_Spring.controllers;

import com.example.Project_Spring.models.ServiceResponse;
import com.example.Project_Spring.security.CustomUserService;
import com.example.Project_Spring.security.UserApp;
import com.example.Project_Spring.services.ProposalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
public class ProposalsController {

    private CustomUserService customUserService;
    private ProposalService proposalService;


    @GetMapping("/applications-and-needs")
    public String applications_and_needs(Model model) {

        // ZDOBYWANIE EMAILA ZALOGOWANEGO UŻYTKOWNIKA I WYSYLANIE GO DO FORMULARZA NA STRONIE
        if (customUserService.getLoggedUsersEmail() != null) {
            model.addAttribute("email_sender", customUserService.getLoggedUsersEmail());
        }

        return "applications-and-needs package/applications-and-needs";
    }


    @PostMapping("/sendMessage")
    public String addMessage(Model model,
                             @org.springframework.lang.Nullable @RequestParam(value = "senderEmail") String senderEmail,
                             @RequestParam(value = "textMsg") String text_msg,
                             @org.springframework.lang.Nullable @RequestParam(value = "subject") String topic
    ) {


        Long senderId = null;
        String nameAndSurname = null;
        System.out.println("uzytkownik");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());


        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserApp userApp = customUserService.getLoggedUser();
            senderId = userApp.getId();
            nameAndSurname = userApp.getName() + " " + userApp.getLastName();
        }


        proposalService.saveProposal(topic, text_msg, senderId, senderEmail, nameAndSurname);


        return "redirect:/";
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-menu/account-proposals")
    public String accountPrivateMessages(ModelMap model) {

        model.addAttribute("current_logged_user", customUserService.getLoggedUser());
        model.addAttribute("proposals_list", proposalService.findAllProposals());

        return "account-menu/account-proposals";
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("account-proposals-readed")
    public ModelAndView accountProposalsReadedUpdate(ModelMap model,
                                                     @RequestParam(value = "proposalId") Long id) {

        proposalService.updateProposalReadedParameter(id);
        ModelAndView mv = new ModelAndView("redirect:/account-menu/account-proposals");
        return mv;
    }


    //    @PostMapping("/saveBook")
//    public ResponseEntity<Object> addBook(@RequestParam Long id) {
//        proposalService.updateProposalReadedParameter(id);
//        ServiceResponse<Long> response = new ServiceResponse<Long>("success", id);
//        return new ResponseEntity<Object>(response, HttpStatus.OK);
//
//    }
    @PostMapping("/saveBook")
    public ResponseEntity<Object> addBook(@RequestParam Long id) {
        System.out.println("Zapisywanie");
        proposalService.updateProposalReadedParameter(id);
        System.out.println("Zapisanie");
        ServiceResponse<Long> response = new ServiceResponse<Long>("success", id);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/event-count")
    public String getEventCount(ModelMap map) {
        System.out.println("Metoda get");
        map.addAttribute("proposals_list", proposalService.findAllProposals());

        return "account-menu/account-proposals :: #eventCount";
    }


}
