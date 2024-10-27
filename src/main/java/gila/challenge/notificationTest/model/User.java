package gila.challenge.notificationTest.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String userName;

    @Column(name = "email")//TODO: add pattern validation
    private String email;

    @Column(name = "phone_number") //TODO: add pattern validation
    private String phoneNumber;
/*
    @ManyToMany(mappedBy = "users")
    private List<Category> categories;

    @ManyToMany(mappedBy = "users")
    private List<Channel> channels;
*/
}
