package com.aiinterview.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiinterview.backend.model.User;
import com.aiinterview.backend.repository.UserRepository;

@Service //Ye class business logic ke liye hai
public class AuthService {

    @Autowired //Repository ko service ke andar inject karta hai
    private UserRepository userRepository;

    // REGISTER USER
    public String register(User user) {

        // 1️⃣ Check: email already exist?
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }

        // 2️⃣ Save user
        userRepository.save(user);

        // 3️⃣ Success message
        return "User registered successfully";
    }
    
    // LOGIN USER
public String login(User user) {

    // 1️⃣ Check: email exist karta hai ya nahi
    return userRepository.findByEmail(user.getEmail())
            .map(dbUser -> {

                // 2️⃣ Password match?
                if (dbUser.getPassword().equals(user.getPassword())) {
                    return "Login successful";
                } else {
                    return "Invalid password";
                }

            })
            .orElse("User not found");
}

}
