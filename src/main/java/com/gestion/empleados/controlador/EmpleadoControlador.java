package com.gestion.empleados.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.empleados.excepciones.ResourceNotFoundException;
import com.gestion.empleados.modelo.Empleado;
import com.gestion.empleados.repositorio.EmpleadoRepositorio;

@RestController
@RequestMapping("/api/v1/") //Aqui pongo la ruta del fronted, el cors origin. Esta es la URL para acceder a los datos del controlador
@CrossOrigin(origins = "http://localhost:4200") // Si le pones barra puede dar error
public class EmpleadoControlador {
	
	@Autowired
	private EmpleadoRepositorio repositorio;
	
	//Este metodo sirve para listar todos los empleados
	
	@GetMapping("/empleados") //Introducir la URL empleados, porque el metodo esta mapeado con empleados
	public List<Empleado> listarTodosLosEmpleados(){
		return repositorio.findAll();
	}
	
	//Método para guardar un empleado
	
	@PostMapping("/empleados")
	public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
		return repositorio.save(empleado);
	}
	
	//Este metodo sirve para buscar un empleado por ID
	@GetMapping("/empleados/{id}")
	public ResponseEntity<Empleado>obtenerEmpleadoPorId(@PathVariable Long id){
		Empleado empleado = repositorio.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException(("No existe el empleado con el ID: " + id)));
		return ResponseEntity.ok(empleado);
	}
	
	// Este metodo permite modificar un empleado
	
	@PutMapping("/empleados/{id}")
	public ResponseEntity<Empleado>actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado detallesEmpleado){
		Empleado empleado = repositorio.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException(("No existe el empleado con el ID: " + id)));
		empleado.setNombre(detallesEmpleado.getNombre());
		empleado.setApellidos(detallesEmpleado.getApellidos());
		empleado.setEmail(detallesEmpleado.getEmail());
		Empleado empleadoActualizado = repositorio.save(empleado);
		return ResponseEntity.ok(empleadoActualizado);
	}
	
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable Long id){
        Empleado empleado = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));

        repositorio.delete(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
	
	
	
	

}
