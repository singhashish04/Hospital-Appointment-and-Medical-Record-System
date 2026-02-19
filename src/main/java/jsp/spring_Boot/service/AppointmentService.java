package jsp.spring_Boot.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.spring_Boot.dao.AppointmentDao;
import jsp.spring_Boot.dto.AppointmentDto;
import jsp.spring_Boot.dto.ResponseStructure;
import jsp.spring_Boot.entity.Appointment;
import jsp.spring_Boot.entity.AppointmentStatus;
import jsp.spring_Boot.entity.Doctor;
import jsp.spring_Boot.exception.IdNotFoundException;
import jsp.spring_Boot.exception.IllegalArgumentValidException;
import jsp.spring_Boot.exception.NoRecordAvailableException;

@Service
public class AppointmentService {
	@Autowired
	private AppointmentDao appointmentDao;
	
	//Book Appointment
	public ResponseEntity<ResponseStructure<Appointment>> saveAppointment(AppointmentDto appointmentDto){
		ResponseStructure<Appointment> response = new ResponseStructure<Appointment>();
		response.setData(appointmentDao.saveAppoitment(appointmentDto));
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Patient Data save in DB");
		return new ResponseEntity<ResponseStructure<Appointment>>(response,HttpStatus.CREATED);
	}
	//Fetch All Appointment
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAll(){
		ResponseStructure<List<Appointment>> response = new ResponseStructure<List<Appointment>>();
		response.setData(appointmentDao.getAll());
		response.setMessage("All Appointment Data Fetched Successfully!");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Appointment>>>(response, HttpStatus.OK);
	}
	//Fetch Appointment by Id
	public ResponseEntity<ResponseStructure<Appointment>> getById(Integer id){
		Optional<Appointment> opt = appointmentDao.getById(id);
		ResponseStructure<Appointment> response = new ResponseStructure<Appointment>();
		if(opt.isPresent()) {
			response.setData(opt.get());
			response.setMessage("Fetch Appointment by Id Successfully!");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Appointment>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Appointment Id not Found in DB");
		}
	}
	
	//Fetch Appointment by Date
	public ResponseEntity<ResponseStructure<List<Appointment>>> getByDate(LocalDate date){
		List<Appointment> list = appointmentDao.getByDate(date);
		ResponseStructure<List<Appointment>> response = new ResponseStructure<List<Appointment>>();
		if(list.isEmpty()) {
			throw new NoRecordAvailableException("No appointment found on this date");
		}
		response.setData(list);
		response.setMessage("Appointments fetched successfully");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Appointment>>>(response,HttpStatus.OK);
	}
	//Fetch Appointment by Doctor id
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByDoctor(Integer doctorID){

	    List<Appointment> list = appointmentDao.getByDoctor(doctorID);

	    ResponseStructure<List<Appointment>> response = new ResponseStructure<>();
	    response.setData(list);
	    response.setMessage("Doctor appointments fetched successfully");
	    response.setStatusCode(HttpStatus.OK.value());

	    return new ResponseEntity<>(response,HttpStatus.OK);
	}
	//Fetch appointment by patient id
	public ResponseEntity<ResponseStructure<List<Appointment>>> getByPatient(Integer patientId){
		List<Appointment> list = appointmentDao.getByPatient(patientId);
		ResponseStructure<List<Appointment>> response = new ResponseStructure<>();
		response.setData(list);
		response.setMessage("Patient appointments fetched successfully");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Appointment>>>(response,HttpStatus.OK);
	}
	//Fetch by status
	public ResponseEntity<ResponseStructure<List<Appointment>>> getByStatus(AppointmentStatus status){
		ResponseStructure<List<Appointment>> response = new ResponseStructure<List<Appointment>>();
		response.setData(appointmentDao.getByStatus(status));
		response.setMessage("Status appointments fetched successfully");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Appointment>>>(response,HttpStatus.OK);
	}
	
	//Cancel Appointment
	public ResponseEntity<ResponseStructure<Appointment>> cancelAppointment(Integer id){
		Appointment appointment = appointmentDao.cancelAppointment(id);
		ResponseStructure<Appointment> response = new  ResponseStructure<Appointment>();
		response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Appointment canceled successfully");
        response.setData(appointment);
        return new ResponseEntity<ResponseStructure<Appointment>>(response,HttpStatus.OK);
	}
	//Update Appointment
	public ResponseEntity<ResponseStructure<Appointment>> updateStatus(Integer id, String status){
	    AppointmentStatus st;
	    try{
	        st = AppointmentStatus.valueOf(status.toUpperCase());
	    }catch(Exception e){
	        throw new NoRecordAvailableException("Invalid status");
	    }
	    Appointment updated = appointmentDao.updateStatus(id, st);
	    ResponseStructure<Appointment> response = new ResponseStructure<>();
	    response.setData(updated);
	    response.setMessage("Appointment status updated to "+st);
	    response.setStatusCode(HttpStatus.OK.value());
	    return new ResponseEntity<ResponseStructure<Appointment>>(response,HttpStatus.OK);
	}
	//Get Doctor by patient
	public ResponseEntity<ResponseStructure<List<Doctor>>> getDoctorByPatient(Integer id){
	    List<Doctor> list = appointmentDao.getDoctorByPatient(id);
	    if(list.isEmpty()){
	        throw new NoRecordAvailableException("No doctor found for this patient id " + id);
	    }
	    ResponseStructure<List<Doctor>> response = new ResponseStructure<>();
	    response.setData(list);
	    response.setMessage("Doctors fetched successfully by patient id");
	    response.setStatusCode(HttpStatus.OK.value());
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

}






















