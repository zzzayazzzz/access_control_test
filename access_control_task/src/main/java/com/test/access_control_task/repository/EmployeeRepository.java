package com.test.access_control_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.access_control_task.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
