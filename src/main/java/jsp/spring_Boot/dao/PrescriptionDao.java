package jsp.spring_Boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.spring_Boot.dto.PrescriptionDto;
import jsp.spring_Boot.entity.MedicalRecord;
import jsp.spring_Boot.entity.Patient;
import jsp.spring_Boot.entity.Prescription;
import jsp.spring_Boot.exception.IdNotFoundException;
import jsp.spring_Boot.exception.IllegalArgumentValidException;
import jsp.spring_Boot.repository.MedicalRecordRepository;
import jsp.spring_Boot.repository.PatientRepository;
import jsp.spring_Boot.repository.PrescriptionRepository;

@Repository
public class PrescriptionDao {
	@Autowired
	private PrescriptionRepository prescriptionRepository;
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	@Autowired
	private PatientRepository patientRepository;
	
	// Save prescription 
	public Prescription savePrescription(PrescriptionDto dto) {
	    if(dto.getRecordID() == null) {
	        throw new IdNotFoundException("Medical Record ID required");
	    }	
	    // check medical record exists
	    Optional<MedicalRecord> optionalRecord = medicalRecordRepository.findById(dto.getRecordID());

	    if(optionalRecord.isEmpty()) {
	        throw new IdNotFoundException("Medical Record not found. Cannot create prescription.");
	    }
	    MedicalRecord record = optionalRecord.get();
	    // create prescription
	    Prescription prescription = new Prescription();
	    prescription.setMedicine(dto.getMedicine());
	    prescription.setDosages(dto.getDosages());
	    prescription.setInstruction(dto.getInstruction());
	    prescription.setMedicalRecord(record);
	    return prescriptionRepository.save(prescription);
	}
	// Fetch all prescription
	public List<Prescription> getAll(){
		return prescriptionRepository.findAll();
	}
	//Fetch by id
	public Optional<Prescription> getById(Integer id) {
		return prescriptionRepository.findById(id);
	}
	//fetch by medical record
	public List<Prescription> getByMedicalRecord(Integer id){
		List<Prescription> list = prescriptionRepository.findByMedicalRecordRecordID(id);
		if(list.isEmpty()) {
			throw new IdNotFoundException("Id not Found in DB");
		}
		return list;
	}
}









