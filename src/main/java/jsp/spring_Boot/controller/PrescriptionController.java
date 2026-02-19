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

import jsp.spring_Boot.dto.PrescriptionDto;
import jsp.spring_Boot.dto.ResponseStructure;
import jsp.spring_Boot.entity.Prescription;
import jsp.spring_Boot.service.PrescriptionService;

@RestController
@RequestMapping("/hospital/prescription")
public class PrescriptionController {
	@Autowired
	private PrescriptionService prescriptionService;
	
	// Save 
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Prescription>> savePrescription(@RequestBody PrescriptionDto dto){
		return prescriptionService.savePrescription(dto);
	}
	// Fetch all
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Prescription>>> getAll(){
		return prescriptionService.getAll();
	}
	//Fetch by id
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Prescription>> getById(@PathVariable Integer id){
		return prescriptionService.getById(id);
	}
	//Fetch by Medical Record
	@GetMapping("/medical/{id}")
	public ResponseEntity<ResponseStructure<List<Prescription>>> getByMedical(@PathVariable Integer id){
		return prescriptionService.getByMedicalRecord(id);
	}
}










