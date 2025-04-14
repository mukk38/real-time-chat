package tr.com.muskar.realTimeChat.model;

import lombok.Data;

@Data
public class PrivateMessage {
    private String to;
    private String content;
}
