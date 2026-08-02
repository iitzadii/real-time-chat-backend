package Chat.Application.service;

import java.time.LocalDateTime;
import java.util.*;

import Chat.Application.dto.ConversationResponse;
import Chat.Application.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Chat.Application.entity.*;
import Chat.Application.dto.MessageRequest;
import Chat.Application.dto.MessageResponse;
import Chat.Application.repository.MessageRepository;
import Chat.Application.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    
    public void send(MessageRequest req){
        if(!userRepository.existsByUsername(req.getSender()))
            throw new RuntimeException("Sender not found");
        if(!userRepository.existsByUsername(req.getReceiver()))
            throw new RuntimeException("Receiver not found");
        Message message=new Message();
        message.setSender(req.getSender());
        message.setReceiver(req.getReceiver());
        message.setContent(req.getContent());
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message);
    }
    public List<MessageResponse> getConversation(String user1, String user2) {
        System.out.println(user1);
        System.out.println(user2);
        List<Message> messages=messageRepository.findConversation(user1, user2);
        List<MessageResponse> res = new ArrayList<>();
        for (Message mes : messages) {
            res.add(new MessageResponse(
            mes.getSender(),
            mes.getReceiver(),
            mes.getContent(),
            mes.getTimestamp()));
        }
        return res;
    }

    public List<ConversationResponse> getConversations(String username) {

        List<String> usernames =
                messageRepository.findConversationUsers(username);

        List<User> users =
                userRepository.findByUsernameIn(usernames);
        List<ConversationResponse> response = new ArrayList<>();
        for (User user : users) {
            response.add(new ConversationResponse(
                    user.getUsername(),
                    user.getDisplayName()
            ));
        }

        return response;
    }
}
