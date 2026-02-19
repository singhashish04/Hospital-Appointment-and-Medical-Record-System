package jsp.spring_Boot.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import jsp.spring_Boot.entity.Appointment;
import jsp.spring_Boot.entity.AppointmentStatus;
import jsp.spring_Boot.entity.Doctor;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	// fetch doctor by patient id
	@Query("SELECT a.doctor FROM Appointment a WHERE a.patient.patientId = :patientId")
	List<Doctor> findDoctorByPatientId(Integer patientId);
	
	//Fetch Appointment by Doctor
	List<Appointment> findByDoctorDoctorID(Integer doctorID); 
    
	//Fetch appointment by patient id
	List<Appointment> findByPatientPatientId(Integer patientId);
	
    @Query("SELECT a FROM Appointment a WHERE a.doctor.doctorID = :doctorID AND a.appointmentDateTime = :dateTime")
    List<Appointment> findDoctorAppointmentAtSameTime(Integer doctorID,LocalDateTime dateTime);
    
    @Query("SELECT a FROM Appointment a WHERE a.patient.patientId = :patientId AND a.appointmentDateTime BETWEEN :start AND :end")
    List<Appointment> findAppointmentsForPatientOnDate(Integer patientId,LocalDateTime start,LocalDateTime end);
    
    @Query("SELECT a FROM Appointment a WHERE a.appointmentDateTime BETWEEN :start AND :end")
    List<Appointment> findAppointmentForDate(LocalDateTime start, LocalDateTime end);
    
    List<Appointment> findByStatus(AppointmentStatus status);
    
  //Update AppointmentStatus
  	    @Modifying //Directly update only AppointmentStatus in DB
  	    @Transactional
  	    @Query("UPDATE Appointment a SET a.status = :status WHERE a.appointmentID = :appointmentID")
  	 int updateAppointmentStatus(Integer appointmentID,AppointmentStatus status);
  	 
  	@Query("SELECT a FROM Appointment a WHERE a.patient.patientId = :patientId AND a.doctor.doctorID = :doctorID AND a.appointmentDateTime BETWEEN :start AND :end")
  	Optional<Appointment> findCompleteAppointmentForVisit(Integer patientId, Integer doctorID, LocalDateTime start, LocalDateTime end);

  	Optional<Appointment> findByDoctorDoctorIDAndPatientPatientIdAndStatus(
  	        Integer doctorID,
  	        Integer patientId,
  	        AppointmentStatus status  );

}






