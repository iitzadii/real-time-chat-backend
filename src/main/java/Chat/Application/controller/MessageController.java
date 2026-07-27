package Chat.Application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Chat.Application.dto.MessageRequest;
import Chat.Application.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;
    public MessageController(MessageService messageService){
        this.messageService=messageService;
    }
    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody MessageRequest message){
        try {
            messageService.send(message);
            return ResponseEntity.ok("Message Sent Successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
    @PostMapping("/conversation")
    public ResponseEntity<?> conversation(@RequestParam String user1, @RequestParam String user2){
        try {
            return ResponseEntity.ok(messageService.getConversation(user1, user2));
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
