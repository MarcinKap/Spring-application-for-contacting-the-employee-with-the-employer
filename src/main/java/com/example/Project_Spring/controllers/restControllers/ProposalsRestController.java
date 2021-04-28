package com.example.Project_Spring.controllers.restControllers;

import com.example.Project_Spring.security.CustomUserService;
import com.example.Project_Spring.security.UserApp;
import com.example.Project_Spring.services.ProposalService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(produces = {"application/json"})
public class ProposalsRestController {

    private CustomUserService customUserService;
    private ProposalService proposalService;

    @PostMapping("/sendQuestionRest")
    public String addMessage(Model model,
                             @org.springframework.lang.Nullable @RequestParam(value = "nameAndSurname") String nameAndSurname,
                             @org.springframework.lang.Nullable @RequestParam(value = "senderEmail") String senderEmail,
                             @org.springframework.lang.Nullable @RequestParam(value = "subject") String topic,
                             @RequestParam(value = "textMsg") String text_msg
    ) {


        Long senderId = null;

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

    @PostMapping("/rest/sendQuestionRestForLoggedUser")
    public String addMessageForLoggedUser(Model model,
                                          @org.springframework.lang.Nullable @RequestParam(value = "senderEmail") String senderEmail,
                                          @org.springframework.lang.Nullable @RequestParam(value = "subject") String topic,
                                          @RequestParam(value = "textMsg") String text_msg
    ) {


        UserApp userApp = customUserService.findUserByEmail(senderEmail);


        proposalService.saveProposal(topic, text_msg, userApp.getId(), null , null);


        return "redirect:/";
    }


}
