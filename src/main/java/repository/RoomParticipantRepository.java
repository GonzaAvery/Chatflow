package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.RoomParticipant;

public interface RoomParticipantRepository extends JpaRepository<RoomParticipant, Long> {

}
