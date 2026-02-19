package jsp.spring_Boot.dto;

public class PrescriptionDto {
	private String medicine;
	private String dosages;
	private String instruction;
	private Integer recordID;
	private Integer patientId;
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
	public Integer getRecordID() {
		return recordID;
	}
	public void setRecordID(Integer recordID) {
		this.recordID = recordID;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
}
