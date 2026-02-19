package jsp.spring_Boot.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.spring_Boot.dto.AppointmentDto;
import jsp.spring_Boot.dto.AppointmentUpdateDto;
import jsp.spring_Boot.entity.Appointment;
import jsp.spring_Boot.entity.AppointmentStatus;
import jsp.spring_Boot.entity.Doctor;
import jsp.spring_Boot.entity.Patient;
import jsp.spring_Boot.exception.IdNotFoundException;
import jsp.spring_Boot.exception.IllegalArgumentValidException;
import jsp.spring_Boot.exception.NoRecordAvailableException;
import jsp.spring_Boot.repository.AppointmentRepository;
import jsp.spring_Boot.repository.DoctorRepository;
import jsp.spring_Boot.repository.PatientRepository;

@Repository
public class AppointmentDao {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private PatientRepository patientRepository;
	
	//Book Appointment 
	public Appointment saveAppoitment(AppointmentDto appointment) {
		if(appointment.getDoctorID() == null) {
			throw new IdNotFoundException("Doctor ID must for the Appointment");
		}else if(appointment.getPatientId() == null) {
			throw new IdNotFoundException("Patient ID must for the Appointment");
		}
		Optional<Doctor> opt = doctorRepository.findById(appointment.getDoctorID());
		Optional<Patient> opt1 = patientRepository.findById(appointment.getPatientId());
		
		// patient- One Appointment per day
		LocalDate date = appointment.getAppointmentDateTime().toLocalDate();
		LocalDateTime startDay = date.atStartOfDay(); // 00.00.00
		LocalDateTime endDay = date.atTime(23,59,59); // 23:59:59
		
		//Doctor no same time appointment 
		List<Appointment> exitingDoctorAppointment = appointmentRepository.findDoctorAppointmentAtSameTime(appointment.getDoctorID(), appointment.getAppointmentDateTime());
		List<Appointment> existingPatientAppointment = appointmentRepository.findAppointmentsForPatientOnDate(appointment.getPatientId(), startDay, endDay);

		// if on the given Date Patient can not have appointment
		if(existingPatientAppointment.isEmpty()) {
			if(exitingDoctorAppointment.isEmpty()) {
				if(opt.isPresent()) {
					if(opt1.isPresent()) {
						Doctor doctor = opt.get();
						Patient patient = opt1.get();
						Appointment appointment1 = new Appointment();
						appointment1.setStatus(appointment.getStatus());
						appointment1.setAppointmentDateTime(appointment.getAppointmentDateTime());
						appointment1.setDoctor(doctor);
						appointment1.setPatient(patient);
						
						Appointment saved = appointmentRepository.save(appointment1);
						return saved;
					}
					// patient id not found in DB
					else {
						throw new NoRecordAvailableException("Patient with DeptName");
					}
				}
				// Doctor id not found DB
				else {
					throw new NoRecordAvailableException("Doctor with DeptName");
				}
			}
			// if at given time Doctor already have
			else {
				throw new NoRecordAvailableException("doctor can't have more than 1 appointment");
			}
		}
		else {
			throw new NoRecordAvailableException("patient can't have more than 1 appointment");
		}
	}
	//Fetch All Appointment
	public List<Appointment> getAll(){
		return appointmentRepository.findAll();
	}
	//Fetch appointment by Id
	public Optional<Appointment> getById(Integer id){
		return appointmentRepository.findById(id);
	}
	
	//Fetch Appointment by Date
	public List<Appointment> getByDate(LocalDate date){
		LocalDateTime start = date.atStartOfDay(); //00:00:00
		LocalDateTime end = date.atTime(23, 59, 59); //23:59:59
		return appointmentRepository.findAppointmentForDate(start,end);
	}
	//Fetch Appointment By Doctor
	public List<Appointment> getByDoctor(Integer doctorID){
		List<Appointment> list = appointmentRepository.findByDoctorDoctorID(doctorID);
		if(list.isEmpty()) {
			throw new NoRecordAvailableException("No appointment found for this doctor");
		}
		return list;
	}
	//Fetch appointment by patient id
	public List<Appointment> getByPatient(Integer patientId){
		List<Appointment> list = appointmentRepository.findByPatientPatientId(patientId);
		if(list.isEmpty()) {
			throw new NoRecordAvailableException("No appointment found for this patient");
		}
		return list;
	}
	//Fetch appointment by status
	public List<Appointment> getByStatus(AppointmentStatus status){
		List<Appointment> list = appointmentRepository.findByStatus(status);
		if(list.isEmpty()) {
			throw new NoRecordAvailableException("No Record found from this "+status+" in DB");
		}
		return list;
	}
	
	//Cancel Appointment
	public Appointment cancelAppointment(Integer id){

        Optional<Appointment> opt = appointmentRepository.findById(id);

        if(opt.isPresent()){

            Appointment appointment = opt.get();

            // only booked can cancel
            if(appointment.getStatus() == AppointmentStatus.BOOKED){

                appointment.setStatus(AppointmentStatus.CANCELED);
                return appointmentRepository.save(appointment);

            }else{
                throw new IllegalArgumentValidException("Only BOOKED appointment can be canceled");
            }

        }else{
            throw new IdNotFoundException("Appointment not found");
        }
    }
	//Update Appointment
	public Appointment updateStatus(Integer id, AppointmentStatus newStatus){

	    Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Appointment not found"));

	    // already completed or cancelled
	    if(appointment.getStatus() != AppointmentStatus.BOOKED){
	        throw new NoRecordAvailableException("Appointment already "+appointment.getStatus());
	    }

	    appointment.setStatus(newStatus);
	    return appointmentRepository.save(appointment);
	}
	
	//Get doctor by Patient 
	public List<Doctor> getDoctorByPatient(Integer patientId){
	    return appointmentRepository.findDoctorByPatientId(patientId);
	}

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	