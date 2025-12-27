package project.practice.controller;



import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.practice.models.User;
import project.practice.repository.UserRepository;
import project.practice.service.UserService;
import project.practice.utils.jwtUtil;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")

public class AuthController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final jwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body)
    {
        String email = body.get("email");
        String password = passwordEncoder.encode(body.get("password"));

        if(userRepository.findByEmail(email).isPresent())
        {
            return  new ResponseEntity<>("Email is already existing", HttpStatus.CONFLICT);
        }
        userService.createUser(User.builder().email(email).password(password).build());
        return new ResponseEntity<>("Successfully registered",HttpStatus.CREATED);
    }
    @PostMapping("/login")

    public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body)
    {
        String email= body.get("email");
        String password = body.get("password");

        var userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty())
        {
            return new ResponseEntity<>("Email not registered", HttpStatus.UNAUTHORIZED);
        }

        User user = userOptional.get();
        if(!passwordEncoder.matches(password,user.getPassword()))
        {
            return new ResponseEntity<>("Invalid user",HttpStatus.UNAUTHORIZED);
        }
        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token",token));

    }
}
