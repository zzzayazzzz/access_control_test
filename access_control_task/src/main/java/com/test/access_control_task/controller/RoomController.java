package com.test.access_control_task.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.access_control_task.exception.ResourceNotFoundException;
import com.test.access_control_task.model.Room;
import com.test.access_control_task.repository.RoomRepository;

@RestController
@RequestMapping("/api/v1/")
public class RoomController {
	
	@Autowired
	private RoomRepository roomRepository;
	
	// get room
	@GetMapping("/rooms")
	public List<Room> getAllRooms(){
		return this.roomRepository.findAll();
	}
	
	// get room by id
	@GetMapping("/rooms/{id}")
	public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") Long roomId) 
			throws ResourceNotFoundException{
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found for this id:: " + roomId));
		return ResponseEntity.ok().body(room);
	}
	
	// save room
	@PostMapping("/rooms")
	public Room createRoom(@RequestBody Room room) {
		return this.roomRepository.save(room);
	}
	
	// update room
	@PutMapping("/rooms/{id}")
	public ResponseEntity<Room> updateRoom(@PathVariable(value = "id") Long roomId,
			@Valid @RequestBody Room roomDetails) throws ResourceNotFoundException{
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found for this id:: " + roomId));
		room.setName(roomDetails.getName());
		
		return ResponseEntity.ok(this.roomRepository.save(room));
		
	}
	
	// delete room
	@DeleteMapping("/rooms/{id}")
	public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId) throws ResourceNotFoundException{
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found for this id:: " + roomId));
		
		this.roomRepository.delete(room);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
		
	}

}
