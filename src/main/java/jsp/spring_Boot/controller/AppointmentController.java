package jsp.spring_Boot.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jsp.spring_Boot.dto.AppointmentDto;
import jsp.spring_Boot.dto.ResponseStructure;
import jsp.spring_Boot.entity.Appointment;
import jsp.spring_Boot.entity.AppointmentStatus;
import jsp.spring_Boot.entity.Doctor;
import jsp.spring_Boot.repository.AppointmentRepository;
import jsp.spring_Boot.service.AppointmentService;

@RestController
@RequestMapping("/hospital/appointment")
public class AppointmentController {
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private AppointmentService appointmentService;
	
	//Book Appointment
	@PostMapping("/book")
	public ResponseEntity<ResponseStructure<Appointment>> saveAppointment(@RequestBody AppointmentDto appointmentDto){
		return appointmentService.saveAppointment(appointmentDto);
	}
	//Fetch All Appointment
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAll(){
		return appointmentService.getAll();
	}
	//Fetch Appointment by Id
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Appointment>> getById(@PathVariable Integer id){
		return appointmentService.getById(id);
	}
	//Fetch Appointment by Date
	@GetMapping("/date/{date}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> getByDate(@PathVariable LocalDate date){  //Localdate->string
//		LocalDate localdate = LocalDate.parse(date);
		return appointmentService.getByDate(date);
	}
	//fetch appointment by doctor id
	@GetMapping("/doctor/{doctorID}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByDoctor(@PathVariable Integer doctorID){
	    return appointmentService.getAppointmentByDoctor(doctorID);
	}
	//Fetch appointment by patient id
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByPatient(@PathVariable Integer patientId){
		return appointmentService.getByPatient(patientId);
	}
	//Fetch by status
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> getStatus(@PathVariable String status){
		return appointmentService.getByStatus(AppointmentStatus.valueOf(status));
	}
	//Cancel Appointment
	@PutMapping("/cancel/{id}")
	public ResponseEntity<ResponseStructure<Appointment>> cancelAppointment(@PathVariable Integer id){
		return appointmentService.cancelAppointment(id);
	}
	//Update Status
	@PatchMapping("/updatestatus/{id}/{status}")
	public ResponseEntity<ResponseStructure<Appointment>> updateStatus(@PathVariable Integer id, @PathVariable String status){
	    return appointmentService.updateStatus(id,status);
	}
	
	//Get doctor by patient
	@GetMapping("/doctor/patient/{id}")
	public ResponseEntity<ResponseStructure<List<Doctor>>> getDoctorByPatient(@PathVariable Integer id){
	    return appointmentService.getDoctorByPatient(id);
	}

}

















