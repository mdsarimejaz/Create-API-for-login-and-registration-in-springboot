package com.example.demo.Service.Impl;

import com.example.demo.Dto.UserDTO;
import com.example.demo.Dto.LoginDTO;
import com.example.demo.Entity.User;
import com.example.demo.Dao.UserDao;
import com.example.demo.Service.UserService;
import com.example.demo.payload.response.LoginMesage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getUserid(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );

        userDao.save(user);
        return user.getUsername();
    }

    @Override
    public LoginMesage loginUser(LoginDTO loginDTO) {
        User user = userDao.findByEmail(loginDTO.getEmail());

        if (user != null) {
            boolean isPasswordMatch = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());

            if (isPasswordMatch) {
                return new LoginMesage("Login Success", true);
            } else {
                return new LoginMesage("Password does not match", false);
            }
        } else {
            return new LoginMesage("Email does not exist", false);
        }
    }
}
