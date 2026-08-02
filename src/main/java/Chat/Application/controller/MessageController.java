package Chat.Application.controller;


import Chat.Application.dto.ConversationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Chat.Application.dto.MessageRequest;
import Chat.Application.service.MessageService;
import java.security.Principal;
import java.util.List;

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
    @GetMapping("/conversation")
    public ResponseEntity<?> conversation(
            Principal principal,
            @RequestParam String user2){

        return ResponseEntity.ok(
                messageService.getConversation(
                        principal.getName(),
                        user2
                )
        );
    }
    @GetMapping("/conversations")
    public ResponseEntity<?> getConversations(
            Principal principal) {

        return ResponseEntity.ok(
                messageService.getConversations(principal.getName())
        );
    }
}
