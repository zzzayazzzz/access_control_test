package com.test.access_control_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.access_control_task.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

}
