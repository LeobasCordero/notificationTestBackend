package gila.challenge.notificationTest.repository;

import gila.challenge.notificationTest.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer> {

    Optional<Channel> findByName(String name);
}
