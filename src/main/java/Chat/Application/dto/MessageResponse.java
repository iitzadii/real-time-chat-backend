package Chat.Application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MessageResponse {
    private String sender;
    private String receiver;
    private String content;
    private LocalDateTime timestamp;
}
