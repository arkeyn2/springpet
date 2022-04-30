package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.entity.Tipo;
import com.example.demo.models.services.ITipoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class TipoRestController {

	@Autowired
	private ITipoService tipoService;

	@GetMapping("/tipos")
	public List<Tipo> index() {
		return tipoService.findAll();
	}

	@GetMapping("/tipos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Tipo tipo = null;
		Map<String, Object> response = new HashMap<>();

		try {
			tipo = tipoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (tipo == null) {
			response.put("mensaje", "El tipo Id:".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Tipo>(tipo, HttpStatus.OK);

	}

	@PostMapping("/tipos")
	@ResponseStatus(HttpStatus.CREATED)
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> create(@RequestBody Tipo tipo) {

		Tipo tiponew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			tiponew = tipoService.save(tipo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El tipo ha sido creado con exito!");
		response.put("sala", tiponew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/tipos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody Tipo tipo, @PathVariable Long id) {

		Tipo tipoActual = tipoService.findById(id);

		Tipo tipoUpdate = null;

		Map<String, Object> response = new HashMap<>();

		if (tipo == null) {
			response.put("mensaje", "El tipo Id:".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			tipoActual.setNombre_tip(tipo.getNombre_tip());

			tipoUpdate = tipoService.save(tipoActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar actualizado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El tipo ha sido actualizado con exito!");
		response.put("tipo", tipoUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/tipos/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			tipoService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La sala eliminada con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
