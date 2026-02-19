package jsp.spring_Boot.dto;

import java.time.LocalDate;

public class MedicalRecordDto {

	    private String diagonosis;
	    private LocalDate visitDate;
	    private Integer doctorId;
	    private Integer patientId;
	    
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
		public Integer getDoctorId() {
			return doctorId;
		}
		public void setDoctorId(Integer doctorId) {
			this.doctorId = doctorId;
		}
		public Integer getPatientId() {
			return patientId;
		}
		public void setPatientId(Integer patientId) {
			this.patientId = patientId;
		}


}
