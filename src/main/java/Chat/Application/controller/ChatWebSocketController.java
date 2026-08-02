package Chat.Application.controller;

//import Chat.Application.service.TokenCacheService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import Chat.Application.dto.MessageRequest;
import Chat.Application.service.MessageService;
import Chat.Application.security.JwtService;
import lombok.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    @MessageMapping("/send")
    public void send(MessageRequest message, Principal principal) {
        System.out.println("Reached Controller");
        message.setSender(principal.getName());
        System.out.println(message);
        messageService.send(message);
        System.out.println("Saved to DB");
        messagingTemplate.convertAndSendToUser(
                message.getReceiver(),
                "/queue/messages",
                message
        );
        System.out.println("Sent to Broker");
    }
}
