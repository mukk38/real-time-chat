package tr.com.muskar.realTimeChat.model;

import lombok.Data;

@Data
public class MessageDto {

    private String from;
    private String content;
    private String group;

    public MessageDto() {}

    public MessageDto(String from, String content) {
        this.from = from;
        this.content = content;
    }

    public MessageDto(String from, String content, String group) {
        this.from = from;
        this.content = content;
        this.group = group;
    }
}
