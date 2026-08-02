package Chat.Application.service;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.stereotype.Service;
import Chat.Application.entity.User;
import Chat.Application.dto.LoginRequest;
import Chat.Application.dto.RegisterRequest;
import Chat.Application.dto.UserSearchResponse;
import Chat.Application.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository UserRepository){
        this.userRepository=UserRepository;
    }

    public void register(RegisterRequest req){
        if(userRepository.existsByUsername(req.getUsername())||userRepository.existsByEmail(req.getEmail())){
            throw new RuntimeException("Username or Email already exists");
        }
        User user=new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword((req.getPassword()));
        user.setDisplayName(req.getDisplayName());
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public User login(LoginRequest req) {
        Optional<User> user = userRepository.findByUsernameOrEmail(
                req.getIdentifier(),
                req.getIdentifier()
        );
        if (user.isEmpty()) {
            throw new RuntimeException("User doesn't exist");
        }

        if (!user.get().getPassword().equals(req.getPassword())) {
            throw new RuntimeException("Wrong Password!");
        }

        return user.get();
    }

    public List<UserSearchResponse> searchUsers(String username){
        List<User> users=userRepository.findByUsernameContainingIgnoreCase(username);
        List<UserSearchResponse> response = new ArrayList<>();
        for(User user : users){
            String userName=user.getUsername();
            String displayName=user.getDisplayName();
            response.add(new UserSearchResponse(userName,displayName));
        }

        return response;
    }
}