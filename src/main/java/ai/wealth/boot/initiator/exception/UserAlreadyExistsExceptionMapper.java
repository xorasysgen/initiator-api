package ai.wealth.boot.initiator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ai.wealth.boot.initiator.exception.model.ErrorMessage;

@ControllerAdvice
public class UserAlreadyExistsExceptionMapper {
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ErrorMessage UserAlreadyExistsExceptionMapperImpl(UserAlreadyExistsException message) {
		
		ErrorMessage errorMessage=new ErrorMessage();
		errorMessage.setErrorMessage(message.getMessage());
		errorMessage.setErrorCode(4001);
		return errorMessage;
		
	}

}
