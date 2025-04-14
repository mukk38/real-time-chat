package tr.com.muskar.realTimeChat.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import tr.com.muskar.realTimeChat.model.MessageDto;
import tr.com.muskar.realTimeChat.model.PrivateMessage;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public MessageDto sendMessage(MessageDto message) {
        return new MessageDto(message.getFrom(), message.getContent());
    }

    @MessageMapping("/private")
    public void sendPrivateMessage(PrivateMessage message, Principal principal) {
        messagingTemplate.convertAndSendToUser(
                message.getTo(), "/queue/messages",
                new MessageDto(principal.getName(), message.getContent())
        );
    }

    @MessageMapping("/group")
    public void sendGroupMessage(MessageDto message, Principal principal) {
        MessageDto groupMessage = new MessageDto(principal.getName(), message.getContent(), message.getGroup());
        messagingTemplate.convertAndSend(
                "/topic/group." + message.getGroup(), groupMessage
        );
    }
}