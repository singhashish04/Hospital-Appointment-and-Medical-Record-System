package jsp.spring_Boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Prescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prescriptionID;
	@Column(nullable = false)
	private String medicine;
	@Column(nullable = false)
	private String dosages;
	@Column(nullable = false) 
	private String instruction;
	
	@OneToOne
//	@JoinColumn(name="record_id")
	private MedicalRecord medicalRecord;
	
	public Prescription() {
		super();
	}

	public Integer getPrescriptionID() {
		return prescriptionID;
	}

	public void setPrescriptionID(Integer prescriptionID) {
		this.prescriptionID = prescriptionID;
	}

	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getDosages() {
		return dosages;
	}

	public void setDosages(String dosages) {
		this.dosages = dosages;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
}
