package Chat.Application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    private String token;
    private String sender;
    private String receiver;
    private String content;
}
