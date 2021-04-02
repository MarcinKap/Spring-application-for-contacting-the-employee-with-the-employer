package com.example.Project_Spring.security;

import com.example.Project_Spring.models.Messages;
import com.example.Project_Spring.services.MessagesService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CustomUserService implements UserDetailsService {

    private UserAppRepository userAppRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    private MessagesService messagesService;

    public CustomUserService(UserAppRepository userAppRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAppRepository
                .findUserAppByName(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Email not find!"));
    }

    public UserApp findUserByEmail(String email) {
        return Optional
                .ofNullable(userAppRepository.findUserAppByEmail(email))
                .orElse(null);
    }

    public UserApp findUserById(Long id) {
        return Optional
                .ofNullable(userAppRepository.findUserAppById(id))
                .orElse(null);
    }

    public String findUserEmailAdressById(Long id) {
        return Optional
                .ofNullable(userAppRepository.findUserAppById(id).getEmail())
                .orElse(null);
    }


    public void saveUserApp(LoginUser loginUser) {
        Role role = roleRepository.findRoleByName("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        UserApp result = UserApp
                .builder()
//                .username(loginUser.getUsername())
                .email(loginUser.getEmail())
                .password(passwordEncoder.encode(loginUser.getPassword()))
                .name(loginUser.getName())
                .lastName(loginUser.getLastName())
                .active(1)
                .roles(roles)
                .build();
        userAppRepository.save(result);
    }

    public void updateUserAppPassword(String newPassword) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserApp userApp = (UserApp) authentication.getPrincipal();
        Optional
                .ofNullable(userAppRepository.findUserAppById(userApp.getId()))
                .map(c -> {
//                        c.setId(currentUser.getId());
//                        c.setEmail(currentUser.getEmail());
                    c.setPassword(passwordEncoder.encode(newPassword));
//                        c.setRoles(currentUser.getRoles());
//                        c.setActive(currentUser.getActive());
//                        c.setAdresses(currentUser.getAdresses());
//                        c.setCompanies_adresses(currentUser.getCompanies_adresses());
//                        c.setLastName(currentUser.getLastName());
//                        c.setTitle(currentUser.getTitle());

                    return userAppRepository.save(c);
                })
                .orElse(null);
    }

    public String getLoggedUsersEmail() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserApp userApp = (UserApp) authentication.getPrincipal();
            UserApp currentUser = userAppRepository.findUserAppById(userApp.getId());
            String email = currentUser.getEmail();
            return email;
        } else {
            return null;
        }
    }

    public String getLoggedUserName() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserApp userApp = (UserApp) authentication.getPrincipal();
            UserApp currentUser = userAppRepository.findUserAppById(userApp.getId());
            String name = currentUser.getName();
            return name;
        } else {
            return null;
        }
    }

    public UserApp getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserApp userApp = (UserApp) authentication.getPrincipal();
        UserApp currentUser = userAppRepository.findUserAppById(userApp.getId());

        return currentUser;
    }


    public Long getLoggedUsersId() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserApp userApp = (UserApp) authentication.getPrincipal();
            UserApp currentUser = userAppRepository.findUserAppById(userApp.getId());
            Long id = currentUser.getId();
            return id;
        } else {
            return null;
        }
    }

    public List<UserApp> getUserAppListById(Set<Long> idList) {

        List<UserApp> userAppList = new ArrayList<>(userAppRepository.findUserAppsByIdIsIn(idList));

        return userAppList;
    }



    public Set<UserApp> findUsersFriend(UserApp userApp){
        System.out.println("Szukanie znajomych");


        Set<UserApp> userAppSet = new HashSet<>();

//        Wiadomości wysłane przez użytkownika - szukamy odbiorcy
        System.out.println(userApp.getName() + userApp.getLastName());
        Set<Messages> sendedMessages = userApp.getSentMessagesList();
        System.out.println("wysłane wiadomosci " + sendedMessages.size());
        for (Messages message: sendedMessages
             ) {
            userAppSet.add(message.getRecipient());
        }
//        Wiadomości odebrane przez użytkownika - szukamy osoby wysyłającej
        Set<Messages> receivedMessages = userApp.getReceivedMessagesList();
        System.out.println("odebrane wiadomosci " + receivedMessages.size());
        for (Messages message: receivedMessages
        ) {
            userAppSet.add(message.getSender());;
        }
        return  userAppSet;
      }


      public boolean logIn(String email, String password){
          Optional<UserApp> userApp = Optional.ofNullable(userAppRepository.findUserAppByEmail(email));
          if (userApp!= null){
              if (passwordEncoder.matches(password, userApp.get().getPassword())){
                  return true;
              }
          }
          return false;

      }

}












