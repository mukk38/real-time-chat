//package tr.com.muskar.realTimeChat.config;
//
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.messaging.support.ChannelInterceptor;
//import org.springframework.messaging.support.MessageHeaderAccessor;
//import org.springframework.stereotype.Component;
//import tr.com.muskar.realTimeChat.util.JwtTokenUtil;
//
//import java.security.Principal;
//import java.util.List;
//
//@Component
//public class JwtChannelInterceptor implements ChannelInterceptor {
//
//    private final JwtTokenUtil jwtTokenUtil;
//
//    public JwtChannelInterceptor(JwtTokenUtil jwtTokenUtil) {
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//    @Override
//    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        StompHeaderAccessor accessor =
//                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//
//        if (accessor != null && accessor.getCommand() != null) {
//            List<String> authHeaders = accessor.getNativeHeader("Authorization");
//            if (authHeaders != null && !authHeaders.isEmpty()) {
//                String token = authHeaders.get(0).replace("Bearer ", "");
//
//                if (jwtTokenUtil.validateToken(token)) {
//                    String username = jwtTokenUtil.getUsernameFromToken(token);
//
//                    // Kullanıcıyı temsil eden Principal objesi ayarlanıyor
//                    accessor.setUser(new Principal() {
//                        @Override
//                        public String getName() {
//                            return username;
//                        }
//                    });
//                }
//            }
//        }
//
//        return message;
//    }
//}