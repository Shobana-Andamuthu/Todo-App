package project.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.practice.models.User;
import project.practice.repository.UserRepository;



@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user)
    {
        return userRepository.save(user);
    }

    public User GetUser(Long id)
    {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("user not Found"));
    }



}
