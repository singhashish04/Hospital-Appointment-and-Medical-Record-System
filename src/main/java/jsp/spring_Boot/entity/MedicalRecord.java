package jsp.spring_Boot.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class MedicalRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recordID;
	@Column(nullable = false)
	private String diagonosis;
	@Column(nullable = false)
	private LocalDate visitDate;
	
	@ManyToOne
//	@JoinColumn(name="doctor_id")
	private Doctor doctor;

	@ManyToOne
//	@JoinColumn(name="patient_id")
	private Patient patient;

	
	@OneToOne(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
	@JsonIgnore
    private Prescription prescription;
	
	public MedicalRecord() {
		super();
	}
	
	

	public Integer getRecordID() {
		return recordID;
	}

	public void setRecordID(Integer recordID) {
		this.recordID = recordID;
	}

	public String getDiagonosis() {
		return diagonosis;
	}

	public void setDiagonosis(String diagonosis) {
		this.diagonosis = diagonosis;
	}

	public LocalDate getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDate visitDate) {
		this.visitDate = visitDate;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	
	
	
}
