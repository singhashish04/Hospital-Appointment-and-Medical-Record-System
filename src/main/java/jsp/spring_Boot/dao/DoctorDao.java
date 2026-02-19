package jsp.spring_Boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.spring_Boot.entity.Appointment;
import jsp.spring_Boot.entity.Doctor;
import jsp.spring_Boot.exception.IdNotFoundException;
import jsp.spring_Boot.repository.AppointmentRepository;
import jsp.spring_Boot.repository.DoctorRepository;
import jsp.spring_Boot.repository.PatientRepository;

@Repository
public class DoctorDao {
	
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired 
	private PatientRepository patientRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	//Add doctor
	public List<Doctor> saveAllDoctor(List<Doctor> doctors){
		return doctorRepository.saveAll(doctors);
	}
	
	//Fetch/get all
	public List<Doctor> getAllDoctor(){
		return doctorRepository.findAll();
	}
	
	//Fetch doctor by id
	public Optional<Doctor> getById(Integer id){
		return doctorRepository.findById(id);
	}
	
	//Fetch doctor by specialization
	public List<Doctor> getBySpecialization(String specialization){
		return doctorRepository.findBySpecialization(specialization);
	}
	
	//fetch doctor by Department
	public List<Doctor> getByDepartment(Integer departmentID){
		return doctorRepository.findByDepartmentDepartmentID(departmentID);
	}
	//Fetch doctor by Available Days
	public List<Doctor> getByAvailableDay(String day){
		return doctorRepository.findByDay(day);
	}
	//Fetch doctor by Patient
//	public List<Doctor> getByPatient(Integer id){
//		return doctorRepository.findByPatientPatientId(id);
//	}
	
	// Fetch Doctor by Appointment
	public Doctor getDoctorByAppointment(Integer appointmentId){
		Optional<Appointment> opt = appointmentRepository.findById(appointmentId);
		if(!opt.isEmpty()) {
			return opt.get().getDoctor();
		}else {
			throw new IdNotFoundException("Appointment not found with id: " + appointmentId);
		}
	}
	// Update doctor info
	public Doctor updateDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	//Delete Doctor by id
	public void deleteDoctor(Integer id) {
		doctorRepository.deleteById(id);
	}
}


























