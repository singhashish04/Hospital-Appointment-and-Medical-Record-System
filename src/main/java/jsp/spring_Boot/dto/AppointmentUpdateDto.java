package jsp.spring_Boot.dto;

import jsp.spring_Boot.entity.AppointmentStatus;

public class AppointmentUpdateDto {
	private Integer appointmentId;
	private AppointmentStatus appointmentStatus;
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	
}
