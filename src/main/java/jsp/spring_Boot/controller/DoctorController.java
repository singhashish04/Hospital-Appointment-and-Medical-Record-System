package jsp.spring_Boot.controller;

import java.util.List;
import java.util.Optional;

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
import jsp.spring_Boot.entity.Doctor;
import jsp.spring_Boot.service.DoctorService;

@RestController
@RequestMapping("/hospital/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	//Add/save doctor
	@PostMapping("/addAll")
	public ResponseEntity<ResponseStructure<List<Doctor>>> saveAllDoctor(@RequestBody List<Doctor> doctors){
		return doctorService.saveAllDoctor(doctors);
	}
	//Fetch/get all doctor record
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Doctor>>> getAllDoctor(){
		return doctorService.getAllDoctor();
	}
	//Fetch by Id
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Doctor>> getById(@PathVariable Integer id){
		return doctorService.getById(id);
	}
	//Fetch doctor by specialization
	@GetMapping("/specialization/{specialization}")
	public ResponseEntity<ResponseStructure<List<Doctor>>> getBySpecialization(@PathVariable String specialization){
		return doctorService.getBySpecialization(specialization);
	}
	//fetch doctor by Department
	@GetMapping("/department/{id}")
	public ResponseEntity<ResponseStructure<List<Doctor>>> getByDepartmentId(@PathVariable Integer id){
		return doctorService.getByDepartment(id);
	}
	//Fetch doctor by Available Days
	@GetMapping("/available/{day}")
	public ResponseEntity<ResponseStructure<List<Doctor>>> getByAvailableDay(@PathVariable String day){
		return doctorService.getDoctorByAvailableDay(day);
	}
	//fetch doctor by Patient
//	@GetMapping("/patient/{id}")
//	public ResponseEntity<ResponseStructure<List<Doctor>>> getPatient(@PathVariable Integer id){
//		return doctorService.getByPatient(id);
//	}
	
	// Fetch Doctor by Appointment
	@GetMapping("/appointment/{appointmentId}")
	public ResponseEntity<ResponseStructure<Doctor>> getDoctorByAppointment(@PathVariable Integer appointmentId){
	    return doctorService.getDoctorByAppointment(appointmentId);
	}
	// Update doctor info
	@PutMapping
	public ResponseEntity<ResponseStructure<Doctor>> updateDoctor(@RequestBody Doctor doctor){
		return doctorService.updateDoctor(doctor);
	}
	//Delete Doctor by  id
	@DeleteMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteDoctor(@PathVariable Integer id){
		return doctorService.deleteDoctor(id);
	}
}































