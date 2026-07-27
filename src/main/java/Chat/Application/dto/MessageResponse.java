package Chat.Application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MessageResponse {
    private String sender;
    private String receiver;
    private String content;
    private LocalDateTime dateTime;

}
