package Chat.Application.service;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.stereotype.Service;
import Chat.Application.entity.*;
import Chat.Application.dto.MessageRequest;
import Chat.Application.dto.MessageResponse;
import Chat.Application.repository.MessageRepository;
import Chat.Application.repository.UserRepository;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    public MessageService(MessageRepository messageRepository,UserRepository userRepository){
        this.messageRepository=messageRepository;
        this.userRepository=userRepository;
    }
    
    public void send(MessageRequest req){
        if(!userRepository.existsByUsername(req.getSender()))
            throw new RuntimeException("Sender not found");
        if(!userRepository.existsByUsername(req.getReceiver()))
            throw new RuntimeException("Receiver not found");
        Message message=new Message();
        message.setSender(req.getSender());
        message.setReceiver(req.getReceiver());
        message.setContent(req.getContent());
        message.setDateTime(LocalDateTime.now());
        messageRepository.save(message);
    }
    public List<MessageResponse> getConversation(String user1, String user2) {
        List<Message> messages=messageRepository.findConversation(user1, user2);
        List<MessageResponse> res = new ArrayList<>();
        for (Message mes : messages) {
            res.add(new MessageResponse(
            mes.getSender(),
            mes.getReceiver(),
            mes.getContent(),
            mes.getDateTime()));
        }
        return res;
    }
}
