package com.example.OnlineStore.controller;

import com.example.OnlineStore.Util.JWTUtil;
import com.example.OnlineStore.authanticationRequest.AuthenticationRequest;
import com.example.OnlineStore.authanticationRequest.AuthenticationResponse;
import com.example.OnlineStore.dto.UserDto;
import com.example.OnlineStore.entity.Users;
import com.example.OnlineStore.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/products")
    public String getProductName() {
        return "Main  PAGE";
    }

    @PostMapping("/user/registration")
    public Long registrationUser(@RequestBody UserDto users) {
        return usersService.createUser(users);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect user or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/login/oauth2")
    public String redirectToAuthorizationEndpoint() {
        return "redirect:/login/oauth2/code/github";
    }

    @GetMapping("/login/oauth21")
    public String redirectToAuthorizationEndpoint1() {
        return "redirect:/login/oauth2/code/google";
    }

    @GetMapping("/login/oauth2/code/github")
    public ResponseEntity<?> handleGitHubCallback(@RequestParam("code") String code, OAuth2AuthenticationToken authenticationToken) {
        DefaultOAuth2User oauth2User = (DefaultOAuth2User) authenticationToken.getPrincipal();

        // Get the user's name attribute, if available
        String userName = oauth2User.getAttribute("name");
        System.out.println();
        if (userName == null) {
            // Handle the case when the name attribute is null
            // You can choose to set a default name or return an error response
            return ResponseEntity.badRequest().body("User name attribute is missing");
        }

        // Perform necessary actions with the user's name
        return ResponseEntity.ok("GitHub OAuth2 Login successful for user: " + userName);
    }

//        @GetMapping("/login/oauth2/code/google")
//        public ResponseEntity<?> handleGitLabCallback1(@RequestParam("code") String code, OAuth2AuthenticationToken authenticationToken) {
//            DefaultOAuth2User oauth2User = (DefaultOAuth2User) authenticationToken.getPrincipal();
//
//            // Get the user's name attribute, if available
//            String userName = oauth2User.getAttribute("name");
//            System.out.println();
//            if (userName == null) {
//                // Handle the case when the name attribute is null
//                // You can choose to set a default name or return an error response
//                return ResponseEntity.badRequest().body("User name attribute is missing");
//            }
//
//            // Perform necessary actions with the user's name
//            return ResponseEntity.ok("GitLab OAuth2 Login successful for user: " + userName);
//        }
}

