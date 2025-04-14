package tr.com.muskar.realTimeChat.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

}
