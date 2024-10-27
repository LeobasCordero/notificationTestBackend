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
    private Integer userId;

    @Column(name = "name", nullable = false, length = 100)
    private String userName;

    @Column(name = "email", length = 50) //TODO: add pattern validation
    private String email;

    @Column(name = "phone_number", length = 20) //TODO: add pattern validation
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "user_category",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "user_channel",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    private List<Channel> channels;

}
