package jsp.spring_Boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.spring_Boot.dao.PrescriptionDao;
import jsp.spring_Boot.dto.PrescriptionDto;
import jsp.spring_Boot.dto.ResponseStructure;
import jsp.spring_Boot.entity.Prescription;
import jsp.spring_Boot.exception.IdNotFoundException;

@Service
public class PrescriptionService {
	@Autowired
	private PrescriptionDao prescriptionDao;
	
	//Save prescription
	public ResponseEntity<ResponseStructure<Prescription>> savePrescription(PrescriptionDto dto) {
	    Prescription savedPrescription = prescriptionDao.savePrescription(dto);
	    ResponseStructure<Prescription> response = new ResponseStructure<>();
	    response.setData(savedPrescription);
	    response.setMessage("Prescription Saved Successfully!");
	    response.setStatusCode(HttpStatus.CREATED.value());
	    return new ResponseEntity<ResponseStructure<Prescription>>(response, HttpStatus.CREATED);
	}
	// Fetch all
	public ResponseEntity<ResponseStructure<List<Prescription>>> getAll(){
		ResponseStructure<List<Prescription>> response = new ResponseStructure<>();
		response.setData(prescriptionDao.getAll());
		response.setMessage("All Record Fetched Successfully");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Prescription>>>(response, HttpStatus.OK);
	}
	// Fetch by Id
	public ResponseEntity<ResponseStructure<Prescription>> getById(Integer id){
		Optional<Prescription> opt = prescriptionDao.getById(id);
		ResponseStructure<Prescription> response = new ResponseStructure<Prescription>();
		if(opt.isPresent()) {
			response.setData(opt.get());
			response.setMessage("Fetch by Id Successfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Prescription>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Id not found in DB");
		}
	}
	//Fetch by Medical Record
	public ResponseEntity<ResponseStructure<List<Prescription>>> getByMedicalRecord(Integer id){
		ResponseStructure<List<Prescription>> response = new ResponseStructure<List<Prescription>>();
		response.setData(prescriptionDao.getByMedicalRecord(id));
		response.setMessage("Prescription Fetched by Medical Record");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Prescription>>>(response, HttpStatus.OK);
	}
}















