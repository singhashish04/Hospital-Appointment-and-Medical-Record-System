package jsp.spring_Boot.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.spring_Boot.dto.MedicalRecordDto;
import jsp.spring_Boot.entity.Appointment;
import jsp.spring_Boot.entity.AppointmentStatus;
import jsp.spring_Boot.entity.Doctor;
import jsp.spring_Boot.entity.MedicalRecord;
import jsp.spring_Boot.entity.Patient;
import jsp.spring_Boot.exception.IdNotFoundException;
import jsp.spring_Boot.exception.IllegalArgumentValidException;
import jsp.spring_Boot.exception.NoRecordAvailableException;
import jsp.spring_Boot.repository.AppointmentRepository;
import jsp.spring_Boot.repository.DoctorRepository;
import jsp.spring_Boot.repository.MedicalRecordRepository;
import jsp.spring_Boot.repository.PatientRepository;

@Repository
public class MedicalRecordDao {

	@Autowired 
	private MedicalRecordRepository medicalRecordRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	// Create record
//	public MedicalRecord saveRecord(MedicalRecordDto medicalRecordDto) {
//		Optional<Doctor> doctor = doctorRepository.findById(medicalRecordDto.getDoctorId());
//		Optional<Patient> patient = patientRepository.findById(medicalRecordDto.getPatientId());
//		if(doctor.isPresent() && patient.isPresent()) {
//			MedicalRecord record = new MedicalRecord();
//			record.setDiagonosis(medicalRecordDto.getDiagonosis());
//			record.setVisitDate(medicalRecordDto.getVisitDate());
//			record.setDoctor(doctor.get());
//			record.setPatient(patient.get());
//			return medicalRecordRepository.save(record);
//		}
//		 else{
//		        throw new IdNotFoundException("Doctor or Patient not found");
//		    }
//	}
	//Save Medical record 
	public MedicalRecord saveRecord(MedicalRecordDto dto){

	    Doctor doctor = doctorRepository.findById(dto.getDoctorId())
	            .orElseThrow(() -> new IdNotFoundException("Doctor not found"));

	    Patient patient = patientRepository.findById(dto.getPatientId())
	            .orElseThrow(() -> new IdNotFoundException("Patient not found"));
	    // CHECK COMPLETED APPOINTMENT
	    Optional<Appointment> appointment = appointmentRepository.findByDoctorDoctorIDAndPatientPatientIdAndStatus(dto.getDoctorId(),dto.getPatientId(),AppointmentStatus.COMPLETED);
	    if(appointment.isEmpty()){
	        throw new IllegalArgumentValidException("Medical Record cannot be created. Appointment not completed yet.");
	    }
	    // SAVE RECORD
	    MedicalRecord record = new MedicalRecord();
	    record.setDiagonosis(dto.getDiagonosis());
	    record.setVisitDate(dto.getVisitDate());
	    record.setDoctor(doctor);
	    record.setPatient(patient);
	    return medicalRecordRepository.save(record);
	}
	//Fetch all Record
	public List<MedicalRecord> getAll(){
		return medicalRecordRepository.findAll();
	}
	//Fetch record by id
	public Optional<MedicalRecord> getById(Integer id) {
		return medicalRecordRepository.findById(id);
	}
	//Fetch by patient 
	public List<MedicalRecord> getByPatient(Integer patientID) {
	    List<MedicalRecord> list = medicalRecordRepository.findByPatientPatientId(patientID);
	    if(list.isEmpty()) {
	        throw new NoRecordAvailableException("No Medical Record found for Patient ID : " + patientID);
	    }
	    return list;
	}
	//Fetch by Doctor
	public List<MedicalRecord> getByDoctor(Integer doctorID){
		List<MedicalRecord> list = medicalRecordRepository.findByDoctorDoctorID(doctorID);
		if(list.isEmpty()) {
			throw new NoRecordAvailableException("No Doctor Record Found for the Doctor ID : "+ doctorID);
		}
		return list;
	}
	//Fetch by Appointment id
	
	//Fetch by visit date
	public List<MedicalRecord> getByVisitDate(LocalDate visitDate){
		return medicalRecordRepository.findByVisitDate(visitDate);
	}
}




















