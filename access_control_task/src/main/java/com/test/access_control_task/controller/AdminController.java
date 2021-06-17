package com.test.access_control_task.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.access_control_task.exception.ResourceNotFoundException;
import com.test.access_control_task.model.Admin;
import com.test.access_control_task.repository.AdminRepository;

@RestController
@RequestMapping("/api/v1/")
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	// get admins
	@GetMapping("/admins")
	public List<Admin> getAllAdmins(){
		return this.adminRepository.findAll();
	}
	
	// get admin by id
	@GetMapping("/admins/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable(value = "id") Long adminId) 
			throws ResourceNotFoundException{
		Admin admin = adminRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found for this id:: " + adminId));
		return ResponseEntity.ok().body(admin);
	}
	
	// save admin
	@PostMapping("/admins")
	public Admin createAdmin(@RequestBody Admin admin) {
		
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
	    String encryptedPwd = bcryptPasswordEncoder.encode(admin.getPassword());
		admin.setPassword(encryptedPwd);
		return this.adminRepository.save(admin);
	}
	
	// update admin
	@PutMapping("/admins/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable(value = "id") Long adminId,
			@Valid @RequestBody Admin adminDetails) throws ResourceNotFoundException{
		Admin admin = adminRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found for this id:: " + adminId));
		admin.setUsername(adminDetails.getUsername());
		
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
	    String encryptedPwd = bcryptPasswordEncoder.encode(adminDetails.getPassword());
		admin.setPassword(encryptedPwd);
//		admin.setPassword(adminDetails.getPassword());
		return ResponseEntity.ok(this.adminRepository.save(admin));
		
	}
	
	// delete admin
	@DeleteMapping("/admin/{id}")
	public Map<String, Boolean> deleteAdmin(@PathVariable(value = "id") Long adminId) throws ResourceNotFoundException{
		Admin admin = adminRepository.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found for this id:: " + adminId));
		
		this.adminRepository.delete(admin);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
		
	}

}
