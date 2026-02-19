package jsp.spring_Boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.spring_Boot.entity.Appointment;
import jsp.spring_Boot.entity.Patient;
import jsp.spring_Boot.repository.AppointmentRepository;
import jsp.spring_Boot.repository.PatientRepository;

@Repository
public class PatientDao {

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	// Register/add Patient
	public Patient registerPatient(Patient patient) {
		return patientRepository.save(patient);
	}
	//Fetch all patient
	public List<Patient> getAllPatient(){
		return patientRepository.findAll();
	}
	//Fetch Patient by ID
	public Optional<Patient> getByID(Integer id){
		return patientRepository.findById(id);
	}
	//Fetch Patient by Phone Number
	public Optional<Patient> getByNumber(Long number){
		return patientRepository.findByPhoneNumber(number);
	}
	//Fetch by age Greaterthan
	public List<Patient> getByAgeGreaterThan(Integer age){
		return patientRepository.findByAgeGreaterThan(age);
	}
	//fetch patient by Appointment
	public Optional<Patient> getPatientByAppointment(Integer appointmentId){
		return patientRepository.findPatientByAppointmentId(appointmentId);
	}
}








