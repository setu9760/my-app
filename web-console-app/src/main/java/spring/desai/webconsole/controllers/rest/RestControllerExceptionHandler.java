package spring.desai.webconsole.controllers.rest;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import spring.desai.common.model.dto.ExceptionDTO;
import spring.desai.common.service.exception.ServiceException;

@ControllerAdvice
public class RestControllerExceptionHandler {

	private final Logger log = Logger.getLogger(getClass());
	
	@ExceptionHandler(value = {ServiceException.class, IllegalArgumentException.class, HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
	public ResponseEntity<ExceptionDTO> badRequestExceptionHandler(Exception e) {
		log.error(ExceptionUtils.getRootCauseMessage(e));
		StringBuilder sb = new StringBuilder();
		if (e instanceof MethodArgumentNotValidException) 
			for(ObjectError error : ((MethodArgumentNotValidException)e).getBindingResult().getAllErrors()) 
				sb.append(error.getDefaultMessage()).append(System.lineSeparator());
		return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(HttpStatus.BAD_REQUEST.name(), sb.toString().length() > 1 ? sb.toString() : ExceptionUtils.getRootCauseMessage(e), e), HttpStatus.BAD_REQUEST);
	}
		
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ExceptionDTO> allExceptionHandler(Exception e) {
		log.error(e.getMessage());
		return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getLocalizedMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
