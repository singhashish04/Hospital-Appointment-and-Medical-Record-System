package jsp.spring_Boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.spring_Boot.entity.MedicalRecord;
import jsp.spring_Boot.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer>{
	
	List<Prescription> findByMedicalRecordRecordID(Integer recordID);
}
