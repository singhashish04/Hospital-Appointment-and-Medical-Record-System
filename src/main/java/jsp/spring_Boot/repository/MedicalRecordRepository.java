package jsp.spring_Boot.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.spring_Boot.entity.Appointment;
import jsp.spring_Boot.entity.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer>{
	
	List<MedicalRecord> findByPatientPatientId(Integer patientId);
	
	List<MedicalRecord> findByDoctorDoctorID(Integer doctorID); 
	
	List<MedicalRecord> findByVisitDate(LocalDate visitDate);
}
