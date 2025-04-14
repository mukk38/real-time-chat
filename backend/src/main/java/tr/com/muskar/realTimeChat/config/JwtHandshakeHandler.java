package tr.com.muskar.realTimeChat.config;

import io.jsonwebtoken.Claims;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import tr.com.muskar.realTimeChat.util.JwtTokenUtil;

import java.security.Principal;
import java.util.Map;
@Component
public class JwtHandshakeHandler extends DefaultHandshakeHandler {

    private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        String token = getTokenFromRequest(request);
        if (token != null && jwtTokenUtil.validateToken(token)) {
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(token);
            return () -> claims.getSubject();
        }
        return null;
    }

    private String getTokenFromRequest(ServerHttpRequest request) {
        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
