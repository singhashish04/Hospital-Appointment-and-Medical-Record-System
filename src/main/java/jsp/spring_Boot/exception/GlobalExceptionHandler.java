package jsp.spring_Boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jsp.spring_Boot.dto.ResponseStructure;
import jsp.spring_Boot.entity.Department;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException exception){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure");
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoRecordAvailableException.class)
	public ResponseEntity<ResponseStructure<Department>> handleNoRecordAvailableException(NoRecordAvailableException exception){
		ResponseStructure<Department> response = new ResponseStructure<Department>();
		response.setData(null);
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(exception.getMessage());
		return new ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateRecordException.class)
	public ResponseEntity<ResponseStructure<String>> handleDuplicateRecordException(DuplicateRecordException exception){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setMessage(exception.getMessage());
		response.setData("Department Already Exist");
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.BAD_REQUEST);
	}
}























