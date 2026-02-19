package jsp.spring_Boot.exception;

public class IdNotFoundException extends RuntimeException{
	public IdNotFoundException(String message) {
		super(message);
	}
}
