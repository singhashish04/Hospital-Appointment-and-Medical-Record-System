package jsp.spring_Boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.spring_Boot.dao.DepartmentDao;
import jsp.spring_Boot.dao.DoctorDao;
import jsp.spring_Boot.dao.PatientDao;
import jsp.spring_Boot.dto.ResponseStructure;
import jsp.spring_Boot.entity.Department;
import jsp.spring_Boot.entity.Doctor;
import jsp.spring_Boot.exception.IdNotFoundException;
import jsp.spring_Boot.exception.NoRecordAvailableException;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private PatientDao patientDao;
	
	//Add doctor
//	public ResponseEntity<ResponseStructure<List<Doctor>>> saveAllDoctor(List<Doctor> doctors){
//		ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
//		response.setData(doctorDao.saveAllDoctor(doctors));
//		response.setMessage("All Doctors Record Saved Sucessfully");
//		response.setStatusCode(HttpStatus.CREATED.value());
//		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response,HttpStatus.CREATED);
//	}
	
	//Add doctor
	public ResponseEntity<ResponseStructure<List<Doctor>>> saveAllDoctor(List<Doctor> doctors){

	    List<Doctor> savedDoctors = new ArrayList<>();

	    for(Doctor doctor : doctors){

	        // 1️ Validate available days
	        if(doctor.getAvailableDays() == null || doctor.getAvailableDays().isEmpty()){
	            throw new NoRecordAvailableException("Doctor must have at least one available day");
	        }

	        // 2️ Validate Department
	        if(doctor.getDepartment() == null || doctor.getDepartment().getDepartmentID() == null){
	            throw new IdNotFoundException("Department id is required");
	        }

	        // 3️ Fetch Managed Department Entity
	        Integer deptId = doctor.getDepartment().getDepartmentID();

	        Department dbDepartment = departmentDao.getDeptByID(deptId)
	                .orElseThrow(() -> new IdNotFoundException("Department not found with id : " + deptId));

	        // 4️ Attach Managed Entity
	        doctor.setDepartment(dbDepartment);

	        savedDoctors.add(doctor);
	    }

	    // 5️ Save after hydration
	    List<Doctor> result = doctorDao.saveAllDoctor(savedDoctors);

	    ResponseStructure<List<Doctor>> response = new ResponseStructure<>();
	    response.setData(result);
	    response.setMessage("All Doctors Record Saved Successfully");
	    response.setStatusCode(HttpStatus.CREATED.value());

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	
	//Fetch/get doctor
	public ResponseEntity<ResponseStructure<List<Doctor>>> getAllDoctor(){
		List<Doctor> list = doctorDao.getAllDoctor();
		if(list.isEmpty()) {
			throw new NoRecordAvailableException("No Doctor found");
		}
		ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
		response.setData(list);
		response.setMessage("All Doctor Record Fetched Sucessfully");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response,HttpStatus.OK);
	}
	
	//Fetch by Id
	public ResponseEntity<ResponseStructure<Doctor>> getById(Integer id){
		Optional<Doctor> opt = doctorDao.getById(id);
		ResponseStructure<Doctor> response = new ResponseStructure<Doctor>();
		if(opt.isPresent()) {
			response.setData(opt.get());
			response.setMessage("Doctor fetched by ID");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Doctor>>(response,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id not found in DB");
		}
	}
	
	//Fetch doctor by specialization
	public ResponseEntity<ResponseStructure<List<Doctor>>> getBySpecialization(String specialization){
		
		List<Doctor> list = doctorDao.getBySpecialization(specialization);
		
		if(list.isEmpty()) {
			throw new NoRecordAvailableException("No doctor with specialization "+ specialization);
		}
		
		ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
		response.setData(doctorDao.getBySpecialization(specialization));
		response.setMessage("Specialization Doctors Fetched Sucessfully");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response,HttpStatus.OK);
	}
	
	//fetch doctor by Department(id)
	public ResponseEntity<ResponseStructure<List<Doctor>>> getByDepartment(Integer id){
		ResponseStructure<List<Doctor>> response= new ResponseStructure<>();
		List<Doctor> list = doctorDao.getByDepartment(id);
		if(!list.isEmpty()) {
			response.setData(list);
			response.setMessage("Doctor fetched by Department id Sucessfully!");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Department not find by this ID = "+id);
		}
	}
	
	//Fetch doctor by Available Days
	public ResponseEntity<ResponseStructure<List<Doctor>>> getDoctorByAvailableDay(String day){
		ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
		response.setData(doctorDao.getByAvailableDay(day));
		response.setMessage("Doctors fetched by available day");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response,HttpStatus.OK);
	}
	
	//fetch doctor by Patient(id)
//	public ResponseEntity<ResponseStructure<List<Doctor>>> getByPatient(Integer id){
//		ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
//		List<Doctor> list = doctorDao.getByPatient(id);
//		if(!list.isEmpty()) {
//			response.setData(list);
//			response.setMessage("Doctor Fetched By Patient ID Sucessfully");
//			response.setStatusCode(HttpStatus.OK.value());
//			return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
//		}else {
//			throw new NoRecordAvailableException("No Doctor is Assigned to this Patient "+id);
//		}
//	}
	
	// Fetch Doctor by Appointment
	public ResponseEntity<ResponseStructure<Doctor>> getDoctorByAppointment(Integer appointmentId){
	    ResponseStructure<Doctor> response = new ResponseStructure<>();
	    Doctor doctor = doctorDao.getDoctorByAppointment(appointmentId);
	    response.setData(doctor);
	    response.setMessage("Doctor fetched successfully by Appointment ID");
	    response.setStatusCode(HttpStatus.OK.value());
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	// Update doctor info
	public ResponseEntity<ResponseStructure<Doctor>> updateDoctor(Doctor doctor){
		ResponseStructure<Doctor> response = new ResponseStructure<Doctor>();
		// ID missing
		if(doctor.getDoctorID()==null) {
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
	        response.setMessage("Id must be provided");
	        response.setData(null);
	        return new ResponseEntity<ResponseStructure<Doctor>>(response, HttpStatus.BAD_REQUEST);
		}
		Optional<Doctor> opt = doctorDao.getById(doctor.getDoctorID());

	    // Case 1 → Record exists
	    if(opt.isPresent()){
	        Doctor updatedDoctor = doctorDao.updateDoctor(doctor);

	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Doctor updated successfully");
	        response.setData(updatedDoctor);
	        return new ResponseEntity<ResponseStructure<Doctor>>(response, HttpStatus.OK);
	    }
	    // Case 2 → ID not found
	    response.setStatusCode(HttpStatus.NOT_FOUND.value());
	    response.setMessage("ID not found in DB");
	    response.setData(null);
	    return new ResponseEntity<ResponseStructure<Doctor>>(response, HttpStatus.NOT_FOUND);
	}
	//Delete Doctor by id
	public ResponseEntity<ResponseStructure<String>> deleteDoctor(Integer id){
		ResponseStructure<String> response = new ResponseStructure<String>();
		Optional<Doctor> opt = doctorDao.getById(id);
		if(opt.isPresent()) {
			doctorDao.deleteDoctor(id);
			response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Doctor deleted successfully");
	        response.setData("Deleted");
	        return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
		}else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
		    response.setMessage("Doctor ID not found in DB");
		    response.setData(null);
		    return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
		}
	}
}






















