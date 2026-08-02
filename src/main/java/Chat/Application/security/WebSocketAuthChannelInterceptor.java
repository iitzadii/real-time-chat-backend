package Chat.Application.security;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketAuthChannelInterceptor implements ChannelInterceptor {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (accessor == null) {
            return message;
        }

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {

            String authHeader = accessor.getFirstNativeHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new AccessDeniedException("JWT token is missing.");
            }

            String token = authHeader.substring(7);

            String username;
            try {
                username = jwtService.extractUsername(token);
            } catch (Exception e) {
                throw new AccessDeniedException("Invalid JWT token.");
            }

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(username);

            if (!jwtService.isTokenValid(token, userDetails)) {
                throw new AccessDeniedException("JWT token is invalid or expired.");
            }

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());

            accessor.setLeaveMutable(true);
            accessor.setUser(authentication);

            return MessageBuilder.createMessage(
                    message.getPayload(),
                    accessor.getMessageHeaders()
            );
        }

        return message;
    }
}