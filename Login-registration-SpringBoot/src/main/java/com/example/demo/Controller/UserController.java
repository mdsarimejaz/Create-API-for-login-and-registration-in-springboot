package com.example.demo.Controller;

import com.example.demo.Dto.UserDTO;
import com.example.demo.Dto.LoginDTO;
import com.example.demo.Service.UserService;
import com.example.demo.payload.response.LoginMesage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // Optional: You can specify exact frontend domain
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        String username = userService.addUser(userDTO);
        return ResponseEntity.ok("User registered successfully: " + username);
    }

   
    @PostMapping("/login")
    public ResponseEntity<LoginMesage> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginMesage loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
}
