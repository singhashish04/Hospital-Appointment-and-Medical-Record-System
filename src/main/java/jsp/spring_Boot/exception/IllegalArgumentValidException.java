package jsp.spring_Boot.exception;

public class IllegalArgumentValidException extends RuntimeException{
	public IllegalArgumentValidException(String msg) {
		super(msg);
	}
}
