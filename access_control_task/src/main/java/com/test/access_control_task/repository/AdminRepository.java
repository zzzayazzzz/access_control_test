package com.test.access_control_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.access_control_task.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	public Admin findByUsername(String username);
}
