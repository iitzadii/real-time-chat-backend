package Chat.Application.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import Chat.Application.dto.MessageRequest;
import Chat.Application.service.MessageService;
import lombok.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/send")
    public void send(MessageRequest message,
                     Principal principal) {

        String sender = principal.getName();

        message.setSender(sender);

        messageService.send(message);

        messagingTemplate.convertAndSendToUser(
                message.getReceiver(),
                "/queue/messages",
                message
        );
    }
}
