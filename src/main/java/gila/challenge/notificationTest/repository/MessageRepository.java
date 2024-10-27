package gila.challenge.notificationTest.repository;

import gila.challenge.notificationTest.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("SELECT m FROM Message m " +
            "LEFT JOIN FETCH m.user " +
            "LEFT JOIN FETCH m.category " +
            "LEFT JOIN FETCH m.channel " +
            "ORDER BY m.sentAt DESC")
    List<Message> findAllMessagesOrderByDateDesc();
}
