package com.test.access_control_task.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.access_control_task.exception.ResourceNotFoundException;
import com.test.access_control_task.model.AccessEvent;
import com.test.access_control_task.model.Employee;
import com.test.access_control_task.model.Room;
import com.test.access_control_task.repository.AccessEventRepository;
import com.test.access_control_task.repository.EmployeeRepository;
import com.test.access_control_task.repository.RoomRepository;

@RestController
@RequestMapping("/api/v1/")
public class AccessController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private AccessEventRepository accessEventRepository;
	
	// get employees
	@PostMapping("/accessEvent/{roomId}")
	public String requestAccess(@PathVariable(value = "roomId") Long roomId, @RequestBody long employeeId) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id:: " + employeeId));
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found for this id:: " + roomId));
		if (employee.getAccessibleRooms().contains(room)) {
			AccessEvent accessEvent = new AccessEvent();
			accessEvent.setDateTime(LocalDateTime.now());
			accessEvent.setEmployeeId(employeeId);
			accessEvent.setRoomId(roomId);
			this.accessEventRepository.save(accessEvent);
			return "access granted";
		}
		return "access denied";
	}
	
	@GetMapping("/accessEvent/")
	public List<AccessEvent> getAllAccessEvents(){
		return this.accessEventRepository.findAll();
	}


}
