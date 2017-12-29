package spring.desai.webconsole.controllers.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import spring.desai.common.model.Response;
import spring.desai.common.model.dto.DTO;
import spring.desai.common.model.dto.DTOFactory;
import spring.desai.common.service.ReadOnlyService;
import spring.desai.common.service.SchoolService;

public abstract class BaseRestController<T extends DTO> {

	@Autowired
	protected ReadOnlyService readOnlyService;

	@Autowired
	protected SchoolService schoolService;

	protected DTOFactory dtoFactory = DTOFactory.getInstance();
	
	@GetMapping(value="/all")
	public ResponseEntity<Response<Collection<T>>> retrieveAll() {
		return prepareResponse(new Response<>("Operation Ok !", retrieveAllImpl()));
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Response<T>> retrieveById(@PathVariable String id){
		return prepareResponse(new Response<>("Operation Ok !", retrieveByIdImpl(id)));
	}
	
	@PreAuthorize("#id == #dto.getId()")
	@PostMapping(value="/{id}/create")
	public ResponseEntity<Response<T>> create(@PathVariable String id, @RequestBody T dto){
		return prepareResponse(new Response<>("Operation Ok !", createImpl(id, dto)));
	}
	
	@PreAuthorize("#id == #dto.getId()")
	@PutMapping(value="/{id}/update")
	public ResponseEntity<Response<T>> update(@PathVariable String id, @RequestBody T dto) {
		return prepareResponse(new Response<>("Operation Ok !", updateImpl(id, dto)));
	}
	
	@DeleteMapping(value="/{id}/delete")
	protected ResponseEntity<Response<Void>> delete(String id){
		return prepareResponse(new Response<>("Operation Ok !", deleteImpl(id)));
	}

	protected abstract Collection<T> retrieveAllImpl();
	protected abstract T retrieveByIdImpl(String id);
	protected abstract T createImpl(String id, T dto);
	protected abstract T updateImpl(String id, T dto);
	protected abstract Void deleteImpl(String id);
	
	private <T2> ResponseEntity<T2> prepareResponse(T2 obj) {
		if (obj != null)
			return new ResponseEntity<>(obj, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
