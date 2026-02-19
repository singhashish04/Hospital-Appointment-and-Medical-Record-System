package jsp.spring_Boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jsp.spring_Boot.dao.DepartmentDao;
import jsp.spring_Boot.dto.ResponseStructure;
import jsp.spring_Boot.entity.Department;
import jsp.spring_Boot.exception.DuplicateRecordException;
import jsp.spring_Boot.exception.IdNotFoundException;
import jsp.spring_Boot.exception.NoRecordAvailableException;
import jsp.spring_Boot.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	//Insert a Record
	public ResponseEntity<ResponseStructure<Department>> saveDept(Department department){
		ResponseStructure<Department> response = new ResponseStructure<Department>();
		
		// check null
		if(department.getDepartmentName()==null || department.getDepartmentName().trim().isEmpty()) {
			throw new NoRecordAvailableException("Department cannot be Empty");
		}
		
		if(departmentRepository.existsByDepartmentName(department.getDepartmentName())) {
			throw new DuplicateRecordException("Department Already Exist in DB");
		}
		
		response.setData(departmentDao.saveDept(department));
		response.setMessage("Department saved successfully");
		response.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.CREATED);
		
	}
	
	//save all departments
	public ResponseEntity<ResponseStructure<List<Department>>> saveAllDept(List<Department> departments){
		ResponseStructure<List<Department>> response = new ResponseStructure<List<Department>>();
		response.setData(departmentDao.saveAllDept(departments));
		response.setMessage("All Department Saved sucessfully");
		response.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<List<Department>>>(response,HttpStatus.CREATED);
	}
	
	// get/fetch all dept
	public ResponseEntity<ResponseStructure<List<Department>>> getAllDept(){
		List<Department> list = departmentDao.getAllDept();
		
		ResponseStructure<List<Department>> response = new ResponseStructure<List<Department>>();
		
		if(list.isEmpty()) {
			throw new NoRecordAvailableException("No department available");
		}
		
		response.setData(list);
		response.setMessage("All Department fetched sucessfully");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Department>>>(response,HttpStatus.OK);
	}
	
	// fetch by id
	public ResponseEntity<ResponseStructure<Department>> getDeptByID(Integer id){
		Optional<Department> opt = departmentDao.getDeptByID(id);
		ResponseStructure<Department> response = new ResponseStructure<Department>();
		if(opt.isPresent()) {
			response.setData(opt.get());
			response.setMessage("Department Found");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Id Not Found");
		}
	}
	
	//update dept ->direct modify by giving id in json
//	public ResponseEntity<ResponseStructure<Department>> updateDept(Department department){
//		ResponseStructure<Department> response = new ResponseStructure<Department>();
//		
//		if(department.getDepartmentID()==null) {
//			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
//	        response.setMessage("Id must be provided");
//	        response.setData(null);
//	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		}
//		
//		Optional<Department> opt = departmentDao.getDeptByID(department.getDepartmentID());
//		
//		if(opt.isPresent()){
//	        Department updatedBook = departmentDao.updateDept(department);
//
//	        response.setStatusCode(HttpStatus.OK.value());
//	        response.setMessage("department updated successfully");
//	        response.setData(updatedBook);
//	        return new ResponseEntity<>(response, HttpStatus.OK);
//	    }
//		
//		response.setStatusCode(HttpStatus.NOT_FOUND.value());
//	    response.setMessage("ID not found in DB");
//	    response.setData(null);
//	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//	}
	
	//update dept fetch->modify->saved
	public ResponseEntity<ResponseStructure<Department>> updateDept(Integer id, Department department){

	    ResponseStructure<Department> response = new ResponseStructure<>();

	    Optional<Department> opt = departmentDao.getDeptByID(id);

	    // ID not found
	    if(opt.isEmpty()){
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
	        response.setMessage("Department not found");
	        response.setData(null);
	        return new ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.NOT_FOUND);
	    }

	    if(departmentRepository.existsByDepartmentName(department.getDepartmentName())) {
			throw new DuplicateRecordException("Department Already Exist in DB");
	    }
	    // Fetch existing record
	    Department dbDept = opt.get();

	    // Update only required field
	    dbDept.setDepartmentName(department.getDepartmentName());

	    Department updatedDept = departmentDao.saveDept(dbDept);

	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Department updated successfully");
	    response.setData(updatedDept);

	    return new ResponseEntity<ResponseStructure<Department>>(response, HttpStatus.OK);
	}

	// Delete dept by id
	public ResponseEntity<ResponseStructure<String>> deleteDept(Integer id){
		ResponseStructure<String> response = new ResponseStructure<String>();
		Optional<Department> opt= departmentDao.getDeptByID(id);
		
		if(opt.isPresent()) {
			departmentDao.deleteDept(id);
			
			response.setData("Deleted");
			response.setMessage("Record Deleted sucessfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
		}
		response.setData(null);
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Department ID not Found");
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	//fetch dept by dept name
	public ResponseEntity<ResponseStructure<List<Department>>> getDeptByName(String deptName){
		ResponseStructure<List<Department>> response = new ResponseStructure<List<Department>>();
		List<Department> dept = departmentDao.getDeptByName(deptName);
		
		if(!dept.isEmpty()) {
			response.setData(dept);
			response.setMessage("Department record with "+ deptName + " retrieved sucessufully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Department>>>(response,HttpStatus.OK);
		}else {
			throw new NoRecordAvailableException("Department not found in DB");
		}
	}
	
}
























