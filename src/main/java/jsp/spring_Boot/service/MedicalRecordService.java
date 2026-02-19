package jsp.spring_Boot.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.spring_Boot.dao.MedicalRecordDao;
import jsp.spring_Boot.dto.MedicalRecordDto;
import jsp.spring_Boot.dto.ResponseStructure;
import jsp.spring_Boot.entity.MedicalRecord;
import jsp.spring_Boot.entity.Patient;
import jsp.spring_Boot.exception.IdNotFoundException;
import jsp.spring_Boot.exception.NoRecordAvailableException;

@Service
public class MedicalRecordService { 
	
	@Autowired
	private MedicalRecordDao medicalRecordDao;
	
	// Create record
	public ResponseEntity<ResponseStructure<MedicalRecord>> saveRecord(MedicalRecordDto dto){
		MedicalRecord savedRecord = medicalRecordDao.saveRecord(dto);
        ResponseStructure<MedicalRecord> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Medical Record Saved Successfully!!");
        response.setData(savedRecord);
        return new ResponseEntity<ResponseStructure<MedicalRecord>>(response, HttpStatus.CREATED);
	}
	//Fetch all Record
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getAll(){
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<List<MedicalRecord>>();
		response.setData(medicalRecordDao.getAll());
		response.setMessage("All record fetched successfully");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response, HttpStatus.OK);
	}
	//Fetch record by id
	public ResponseEntity<ResponseStructure<MedicalRecord>> getById(Integer id){
		ResponseStructure<MedicalRecord> response = new ResponseStructure<MedicalRecord>();
		Optional<MedicalRecord> opt = medicalRecordDao.getById(id);
		if(opt.isPresent()) {
			response.setData(opt.get());
			response.setMessage("Medical Record fetched by Id");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<MedicalRecord>>(response, HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Id not found in DB");
		}
	}
	//Fetch By Patient Id
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getByPatient(Integer id){
	    ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<>();
	    List<MedicalRecord> list = medicalRecordDao.getByPatient(id);
	    response.setData(list);
	    response.setMessage("Medical Records fetched by Patient successfully");
	    response.setStatusCode(HttpStatus.OK.value());
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	//fetch by doctor id
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getByDoctor(Integer id){
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<List<MedicalRecord>>();
		List<MedicalRecord> list = medicalRecordDao.getByDoctor(id);
		response.setData(list);
		response.setMessage("Medical Records fetched by Doctor successfully");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response, HttpStatus.OK);
	}
	// fetch by visitdate
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getByVisitDate(LocalDate visitDate){
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<List<MedicalRecord>>();
		List<MedicalRecord> list = medicalRecordDao.getByVisitDate(visitDate);
		if(!list.isEmpty()) {
			response.setData(list);
			response.setMessage("Fetched by Visit-Date Successfully");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response,HttpStatus.OK);
		}else {
			throw new NoRecordAvailableException("No visit date found in DB");
		}
	}
}












