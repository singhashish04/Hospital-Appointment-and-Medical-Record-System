package jsp.spring_Boot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer doctorID;
	@Column(nullable = false)
	private String doctorName;
	@Column(nullable = false)
	private String specialization;
	
//	@Column(nullable = false)
//	private List<String> availableDays;
	
	@ElementCollection
	@CollectionTable(name="doctor_available_days", joinColumns= @JoinColumn(name="doctor_id"))
	@Column(name="day")
	private List<String> availableDays;

	
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Appointment> appointment;

	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<MedicalRecord> medicalRecords;
	
	
	public Integer getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(Integer doctorID) {
		this.doctorID = doctorID;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public List<String> getAvailableDays() {
		return availableDays;
	}
	public void setAvailableDays(List<String> availableDays) {
		this.availableDays = availableDays;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	
}
