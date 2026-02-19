package jsp.spring_Boot.exception;

public class DuplicateRecordException extends RuntimeException{
	
	public DuplicateRecordException(String message) {
		super(message);
	}

}
