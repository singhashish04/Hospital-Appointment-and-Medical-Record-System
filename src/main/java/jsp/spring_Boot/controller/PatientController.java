package jsp.spring_Boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.spring_Boot.dto.ResponseStructure;
import jsp.spring_Boot.entity.Patient;
import jsp.spring_Boot.service.PatientService;

@RestController
@RequestMapping("/hospital/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	// Register/add patient
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<Patient>> registerPatient(@RequestBody Patient patient){
		return patientService.registerPatient(patient);
	}
	// Fetch All Patient
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Patient>>> getAllPatient(){
		return patientService.getAllPatient();
	}
	//Fetch by ID
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Patient>> getByID(@PathVariable Integer id){
		return patientService.getByID(id);
	}
	//Fetch Patient by Phone Number
	@GetMapping("/number/{number}")
	public ResponseEntity<ResponseStructure<Patient>> getByNumber(@PathVariable Long number){
		return patientService.getByNumber(number);
	}
	//Fetch Patient by Age Greater Than
	@GetMapping("/age/{age}")
	public ResponseEntity<ResponseStructure<List<Patient>>> getAgeGreaterThan(@PathVariable Integer age){
		return patientService.getbyAgeGreatherThan(age);
	}
	// Fetch Patient by Appointment
	@GetMapping("/appointment/{appointmentId}")
	public ResponseEntity<ResponseStructure<Patient>> getPatientByAppointment(@PathVariable Integer appointmentId){
	    return patientService.getPatientByAppointment(appointmentId);
	}
	
}


























