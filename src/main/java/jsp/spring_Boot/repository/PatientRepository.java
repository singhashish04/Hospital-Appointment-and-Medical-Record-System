package jsp.spring_Boot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jsp.spring_Boot.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{
	
	//Fetch by Number
	Optional<Patient> findByPhoneNumber(Long phoneNumber);

	//Fetch age Greaterthan value=?
	List<Patient> findByAgeGreaterThan(Integer age);
	
	//Fetch patient by appointment
	@Query("SELECT a.patient FROM Appointment a WHERE a.appointmentID = :appointmentId")
	Optional<Patient> findPatientByAppointmentId(Integer appointmentId);

}
