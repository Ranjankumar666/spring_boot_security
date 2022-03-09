package com.example.security_spring.Home;

import com.example.security_spring.model.AuthenticationRes;
import com.example.security_spring.model.AutheticationReq;

// import java.util.List;

import com.example.security_spring.util.Jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private UserDetailsService uds;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private Jwt jwt;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody AutheticationReq body) throws Exception {

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword()));

        } catch (AuthenticationException e) {
            throw new Exception("Invalid Credential");
        }

        UserDetails user = uds.loadUserByUsername(body.getUsername());
        String token = jwt.generateToken(user);

        return ResponseEntity.ok(new AuthenticationRes(token));
    }

    @GetMapping("/")
    public String home() {

        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return "Hello user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello admin";
    }
}
