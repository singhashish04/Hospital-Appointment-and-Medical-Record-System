package jsp.spring_Boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.spring_Boot.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{ 

	//fetch department by dept name
	List<Department> findByDepartmentName(String departmentName);
	
	// Check existing department
	Boolean existsByDepartmentName(String departmentName);
}
