package Chat.Application.repository;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Chat.Application.entity.Message;

public interface MessageRepository extends JpaRepository<Message,UUID>{
    Optional<Message> findByContent(String content);
    @Query("""
    SELECT m
    FROM Message m
    WHERE
    (m.sender = :user1 AND m.receiver = :user2)
    OR
    (m.sender = :user2 AND m.receiver = :user1)
    ORDER BY m.timestamp ASC
    """)
    List<Message> findConversation(@Param("user1") String user1, @Param("user2") String user2);

    @Query("""
    SELECT DISTINCT
    CASE
        WHEN m.sender = :username THEN m.receiver
        ELSE m.sender
    END
    FROM Message m
    WHERE m.sender = :username
       OR m.receiver = :username
    """)
    List<String> findConversationUsers(@Param("username") String username);
}
