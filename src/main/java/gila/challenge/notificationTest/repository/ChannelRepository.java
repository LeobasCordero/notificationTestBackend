package gila.challenge.notificationTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gila.challenge.notificationTest.model.Channel;


@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer> {
}
