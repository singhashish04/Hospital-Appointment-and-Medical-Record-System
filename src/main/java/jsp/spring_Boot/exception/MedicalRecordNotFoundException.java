package jsp.spring_Boot.exception;

public class MedicalRecordNotFoundException extends RuntimeException{
	
	public MedicalRecordNotFoundException(String smg) {
		super(smg);
	}

}
