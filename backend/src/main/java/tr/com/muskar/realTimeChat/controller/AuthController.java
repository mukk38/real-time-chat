package tr.com.muskar.realTimeChat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.muskar.realTimeChat.model.UserEntity;
import tr.com.muskar.realTimeChat.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity userEntity) {
        if (userRepository.existsByUsername(userEntity.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        userRepository.save(userEntity);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity loginRequest) {
        return userRepository.findByUsername(loginRequest.getUsername())
                .filter(userEntity -> userEntity.getPassword().equals(loginRequest.getPassword()))
                .map(userEntity -> ResponseEntity.ok("Login successful.")) // Buraya JWT eklenecek
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials."));
    }

}
