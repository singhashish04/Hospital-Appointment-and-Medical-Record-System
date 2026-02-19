package jsp.spring_Boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.spring_Boot.dao.PatientDao;
import jsp.spring_Boot.dto.ResponseStructure;
import jsp.spring_Boot.entity.Patient;
import jsp.spring_Boot.exception.IdNotFoundException;
import jsp.spring_Boot.exception.NoRecordAvailableException;

@Service
public class PatientService {
	
	@Autowired
	private PatientDao patientDao;
	
	// Register/add Patient
	public ResponseEntity<ResponseStructure<Patient>> registerPatient(Patient patient) {
		
		ResponseStructure<Patient> response = new ResponseStructure<Patient>();
		response.setData(patientDao.registerPatient(patient));
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Patient Register Sucessfully!!");
		return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.CREATED);
	}
	// Fetch All Patient
	public ResponseEntity<ResponseStructure<List<Patient>>> getAllPatient(){
		ResponseStructure<List<Patient>> response = new ResponseStructure<>();
		List<Patient> list = patientDao.getAllPatient();
		if(!list.isEmpty()) {
			response.setData(patientDao.getAllPatient());
			response.setMessage("All Patient Fetched Sucessfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Patient>>>(response,HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("Patient Record not found in DB.");
		}
	}
	// Fetch by ID
	public ResponseEntity<ResponseStructure<Patient>> getByID(Integer id){
		ResponseStructure<Patient> response = new ResponseStructure<Patient>();
		Optional<Patient> opt = patientDao.getByID(id);
		if(opt.isPresent()) {
			response.setData(opt.get());
			response.setMessage("Patient Fetched By ID Sucessfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.OK);
		}
		else{
			throw new IdNotFoundException("Id not Found in DB.");
		}
	}
	//Fetch by Phone Number
	public ResponseEntity<ResponseStructure<Patient>> getByNumber(Long number){
		Optional<Patient> opt = patientDao.getByNumber(number);
		ResponseStructure<Patient> response = new ResponseStructure<Patient>();
		if(opt.isPresent()) {
			response.setData(opt.get());
			response.setMessage("Patient By Fetched by Phone Number Sucesscufully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Patient not Found with this = "+ number);
		}
	}
	//Fetch Patient by Age Greater Than
	public ResponseEntity<ResponseStructure<List<Patient>>> getbyAgeGreatherThan(Integer age){
		ResponseStructure<List<Patient>> response = new ResponseStructure<List<Patient>>();
		List<Patient> list = patientDao.getByAgeGreaterThan(age);
		if(!list.isEmpty()) {
			response.setData(list);
			response.setMessage("Patient Age Greater than "+age+" fetched sucessfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Patient>>>(response,HttpStatus.OK);
		}else {
			throw new NoRecordAvailableException("Patient with age "+age+" Greater Than not Exists.");
		}
	}
	//fetch patient by Appointment
	public ResponseEntity<ResponseStructure<Patient>> getPatientByAppointment(Integer appointmentId){
	    ResponseStructure<Patient> response = new ResponseStructure<>();
	    Optional<Patient> opt = patientDao.getPatientByAppointment(appointmentId);
	    if(opt.isPresent()){
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Patient fetched by appointment successfully");
	        response.setData(opt.get());
	        return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.OK);
	    }
	    else{
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
	        response.setMessage("No patient found for this appointment");
	        response.setData(null);
	        return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.NOT_FOUND);
	    }
	}

}



































