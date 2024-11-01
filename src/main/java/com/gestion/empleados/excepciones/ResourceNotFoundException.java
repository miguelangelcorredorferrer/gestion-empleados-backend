package com.gestion.empleados.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Excepciones personalizadas

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionID = 1L; // Es como id para la clase
	public ResourceNotFoundException(String mensaje) {
		super(mensaje);
	}
	
}
