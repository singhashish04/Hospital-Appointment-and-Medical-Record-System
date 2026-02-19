package jsp.spring_Boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.spring_Boot.entity.Department;
import jsp.spring_Boot.repository.DepartmentRepository;

@Repository
public class DepartmentDao {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	//Insert a Record
	public Department saveDept(Department department) {
		return departmentRepository.save(department);
	}
	
	// save/insert all dept
	public List<Department>  saveAllDept(List<Department> departments){
		return departmentRepository.saveAll(departments);
	}
	
	// fetch all dept
	public List<Department> getAllDept(){
		return departmentRepository.findAll();
	}
	//fetch dept by id
	public Optional<Department> getDeptByID(Integer id){
		return departmentRepository.findById(id);
	}
	//Update department
	public Department updateDept(Department department) {
		return departmentRepository.save(department);
	}
	//Delete Dept by id 
	public void deleteDept(Integer id ) {
		 departmentRepository.deleteById(id);
	}
	//Fetch department by dept name
	public List<Department> getDeptByName(String deptName){
		return departmentRepository.findByDepartmentName(deptName);
	}
}













