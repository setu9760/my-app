package spring.desai.webconsole.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import spring.desai.common.model.dto.ExceptionDTO;
import spring.desai.common.service.exception.ServiceException;

@ControllerAdvice
public class RestControllerExceptionHandler {

	@ExceptionHandler(value = ServiceException.class)
	public ResponseEntity<ExceptionDTO> serviceExceptionHandler(ServiceException e) {
		return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(HttpStatus.BAD_REQUEST.toString(), e.getLocalizedMessage(), e), HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(value = Exception.class)
//	public ResponseEntity<ExceptionDTO> allExceptionHandler(Exception e) {
//		return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(HttpStatus.BAD_REQUEST.toString(), e.getLocalizedMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
