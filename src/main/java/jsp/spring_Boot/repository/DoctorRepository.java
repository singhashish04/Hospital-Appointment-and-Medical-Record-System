package jsp.spring_Boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jsp.spring_Boot.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	
	//Fetch doctor by specialization
	List<Doctor> findBySpecialization(String specialization);
	
	//fetch doctor by department
	List<Doctor> findByDepartmentDepartmentID(Integer departmentId);
	
	//Fetch doctor by Patient(id)
//	List<Doctor> findByPatientPatientId(Integer patientId);
	
	//Fetch doctor by Available Days
//	List<Doctor> findByAvailableDays(String day);
	@Query("select d from Doctor d where :day MEMBER OF d.availableDays")
	List<Doctor> findByDay( String day);

}
