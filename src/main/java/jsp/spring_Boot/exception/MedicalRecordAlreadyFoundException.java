package jsp.spring_Boot.exception;

public class MedicalRecordAlreadyFoundException extends RuntimeException{
	public MedicalRecordAlreadyFoundException(String msg) {
		super(msg);
	}
}
