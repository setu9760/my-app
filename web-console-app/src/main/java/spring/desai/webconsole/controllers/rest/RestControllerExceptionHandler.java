package spring.desai.webconsole.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import spring.desai.common.model.dto.ExceptionDTO;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.service.exception.ServiceException;

@ControllerAdvice
public class RestControllerExceptionHandler {

	@ExceptionHandler(value = RepositoryDataAccessException.class)
	public ResponseEntity<ExceptionDTO> exceptionHandler(RepositoryDataAccessException e) {
		ResponseEntity<ExceptionDTO> re = new ResponseEntity<>(new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getLocalizedMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
		return re;
	}
	
	@ExceptionHandler(value = ServiceException.class)
	public ResponseEntity<ExceptionDTO> exceptionHandler(ServiceException e) {
		ResponseEntity<ExceptionDTO> re = new ResponseEntity<>(new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getLocalizedMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
		return re;
	}
	
}
