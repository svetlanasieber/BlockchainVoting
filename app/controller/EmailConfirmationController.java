package com.example.blockchainvoting.controller; 

import com.example.blockchainvoting.model.User;
import com.example.blockchainvoting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailConfirmationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/confirm-email")
    public String confirmEmail(@RequestParam String token) {
        User user = userRepository.findByConfirmationToken(token);
        if (user != null) {
            user.getRoles().add("ROLE_CONFIRMED");
            user.setConfirmationToken(null); 
            userRepository.save(user);
            return "email_confirmation_success";
        } else {
            return "email_confirmation_failure";
        }
    }
}
