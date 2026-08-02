//package Chat.Application.service;
//import Chat.Application.dto.AuthCache;
//import Chat.Application.entity.User;
//import Chat.Application.repository.UserRepository;
//import Chat.Application.security.JwtService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import java.time.Instant;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Service
//@RequiredArgsConstructor
//public class TokenCacheService {
//
//    private final JwtService jwtService;
//    private final UserRepository userRepository;
//
//    private final Map<String, AuthCache> cache = new ConcurrentHashMap<>();
//
//    public String authenticate(String token) {
//
//        AuthCache authCache = cache.get(token);
//
//        if (authCache != null) {
//            if (authCache.getExpiry().isAfter(Instant.now())) {
//                return authCache.getUsername();
//            }
//            cache.remove(token);
//        }
//
//        String username = jwtService.extractUsername(token);
//
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!jwtService.isTokenValid(token, user)) {
//            throw new RuntimeException("Invalid Token");
//        }
//
//        cache.put(
//                token,
//                new AuthCache(username,jwtService.extractExpiration(token).toInstant())
//        );
//
//        return username;
//    }
//}