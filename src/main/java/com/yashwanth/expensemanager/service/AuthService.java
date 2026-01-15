package com.yashwanth.expensemanager.service;

import com.yashwanth.expensemanager.dto.auth.LoginRequest;
import com.yashwanth.expensemanager.dto.auth.LoginResponse;
import com.yashwanth.expensemanager.entity.User;
import com.yashwanth.expensemanager.repository.UserRepository;
import com.yashwanth.expensemanager.security.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            JwtUtil jwtUtil,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * TEMPORARY DEV BOOTSTRAP USER
     * ----------------------------
     * This method runs once when the application starts.
     * It ensures at least one user exists for login testing.
     *
     * REMOVE THIS METHOD once signup is implemented.
     *
    @PostConstruct
    public void createDefaultUser() {

        if (userRepository.findByEmail("test@test.com").isEmpty()) {

            User user = new User();
            user.setEmail("test@test.com");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole("ROLE_USER");

            userRepository.save(user);
        }
    }

    /**
     * LOGIN
     */
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}


