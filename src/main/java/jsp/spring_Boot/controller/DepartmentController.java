package jsp.spring_Boot.controller;

import java.util.List;

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

import jsp.spring_Boot.dto.ResponseStructure;
import jsp.spring_Boot.entity.Department;
import jsp.spring_Boot.service.DepartmentService;

@RestController
@RequestMapping("/hospital/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	
	//Insert a Record
	@PostMapping
	public ResponseEntity<ResponseStructure<Department>> saveDept(@RequestBody Department department){
		return departmentService.saveDept(department);
	}
	
	 // Insert multiple
    @PostMapping("/all")
    public ResponseEntity<ResponseStructure<List<Department>>> saveAllDept(@RequestBody List<Department> departments){
        return departmentService.saveAllDept(departments);
    }
    
    //fetch all
    @GetMapping("/getAll")
    public ResponseEntity<ResponseStructure<List<Department>>> getAllDept(){
    	return departmentService.getAllDept();
    }
    
    //fetch by id
    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseStructure<Department>> getDeptById(@PathVariable Integer id){
    	return departmentService.getDeptByID(id);
    }
    
    //update dept by providing id in url not in json body
    @PutMapping("/id/{id}")
    public ResponseEntity<ResponseStructure<Department>> updateDept(@PathVariable Integer id, @RequestBody Department department){
        return departmentService.updateDept(id, department);
    }
    
    //Delete 
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteBook(@PathVariable Integer id){
    	return departmentService.deleteDept(id);
    }
    
    //Fetch by dept name
    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseStructure<List<Department>>> getDeptByName(@PathVariable String name){
    	return departmentService.getDeptByName(name);
    }
}





















