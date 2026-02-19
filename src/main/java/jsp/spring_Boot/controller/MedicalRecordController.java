package jsp.spring_Boot.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.spring_Boot.dto.MedicalRecordDto;
import jsp.spring_Boot.dto.ResponseStructure;
import jsp.spring_Boot.entity.Doctor;
import jsp.spring_Boot.entity.MedicalRecord;
import jsp.spring_Boot.service.MedicalRecordService;

@RestController
@RequestMapping("/hospital/medical")
public class MedicalRecordController {
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	// Create record
	@PostMapping("/add")
	public ResponseEntity<ResponseStructure<MedicalRecord>> createRecord(@RequestBody MedicalRecordDto dto){
		return medicalRecordService.saveRecord(dto);
	}
	//Fetch all Record
	@GetMapping
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getAllRecord(){
		return medicalRecordService.getAll();
	}
	//Fetch record by id
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<MedicalRecord>> getById(@PathVariable Integer id){
		return medicalRecordService.getById(id);
	}
//	//Fetch by patient 
//	@GetMapping("/patient/id/{id}")
//	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getByPatient(@PathVariable Integer id){
//		return medicalRecordService.getByPatient(id);
//	}
	//Fetch by patient 
	@GetMapping("/patient/id/{id}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getByPatient(@PathVariable Integer id){
	    return medicalRecordService.getByPatient(id);
	}
	// Fetch by Doctor
	@GetMapping("/doctor/id/{id}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getByDoctor(@PathVariable Integer id){
		return medicalRecordService.getByDoctor(id);
	}
	//Fetch by Visit date
	@GetMapping("/visitdate/{date}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getByVisitDate(@PathVariable LocalDate date){
		return medicalRecordService.getByVisitDate(date);
	}
}










