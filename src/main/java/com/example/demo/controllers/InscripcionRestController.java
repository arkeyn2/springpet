package com.example.demo.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import com.example.demo.model.dao.IInscripcionDao;
import com.example.demo.models.entity.Inscripcion;
import com.example.demo.models.entity.Usuario;
import com.example.demo.models.services.IInscripcionService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class InscripcionRestController {

	@Autowired
	private IInscripcionService reservaService;
	@Autowired
	private IInscripcionDao ireservadao;

	@GetMapping("/reservas")
	public List<Inscripcion> index() {
		return reservaService.findAll();
	}

	@GetMapping("/reservas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Inscripcion reserva = null;
		Map<String, Object> response = new HashMap<>();

		try {
			reserva = reservaService.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (reserva == null) {
			response.put("mensaje", "El usuario Id:".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Inscripcion>(reserva, HttpStatus.OK);
	}

	@PostMapping("/reservas")
	@ResponseStatus(HttpStatus.CREATED)
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> create(@RequestBody Inscripcion reserva) {
		System.out.println(reserva);
		Inscripcion reservanew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			reservanew = reservaService.save(reserva);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la reserva");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La reserva ha sido creada con exito!");
		response.put("reserva", reservanew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/reservas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody Inscripcion reserva, @PathVariable Long id) {

		Inscripcion reservaActual = reservaService.findById(id);

		Inscripcion reservaUpdate = null;

		Map<String, Object> response = new HashMap<>();
		if (reserva == null) {
			response.put("mensaje", "La reserva Id:".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			//sreservaActual.setTitulo(reserva.getTitulo());
			//reservaActual.setSala(reserva.getSala());


			reservaUpdate = reservaService.save(reservaActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar actualizado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La reserva ha sido actualizado con exito!");
		response.put("reserva", reservaUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/reservas/reserva/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			reservaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La reserva eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	/*@GetMapping("/reservas/my/{id_sala}/{id_hora}/{fecha_desde}/{fecha_hasta}")
	public ResponseEntity<?> findReservahora(@PathVariable Long id_sala, @PathVariable Date fecha_desde,@PathVariable Date fecha_hasta) {
		List<Reserva> reserva = null;
		Map<String, Object> response = new HashMap<>();

		try {
			reserva = reservaService.findReservahora(id_sala,fecha_desde,fecha_hasta);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Reserva>>(reserva, HttpStatus.OK);
	}*/
	
	@GetMapping("/reservas/sala/{id}")
	public ResponseEntity<?> findReservaPorIdSala(@PathVariable Long id) {

		List<Inscripcion> reserva = null;
		Map<String, Object> response = new HashMap<>();

		try {
			reserva = reservaService.findReservaPorIdSala(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (reserva == null) {
			response.put("mensaje", "La reserva Id:".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Inscripcion>>(reserva, HttpStatus.OK);
	}
	

	@GetMapping("/reservas/usuario/{id}")
	public ResponseEntity<?> findReservaPorUsuario(@PathVariable String id) {

		List<Inscripcion> reserva = null;
		Map<String, Object> response = new HashMap<>();
		System.out.println(id);
		try {
			reserva = reservaService.findReservaPorUsuario(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (reserva == null) {
			response.put("mensaje", "La reserva Id:".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Inscripcion>>(reserva, HttpStatus.OK);
	}
	
	@GetMapping("/reservas/reserva/{id}")
	public ResponseEntity<?> eliminarreserva(@PathVariable String id) {

		List<Object> reserva = null;
		Map<String, Object> response = new HashMap<>();

		try {
			reserva = reservaService.eliminarreserva(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al ejecutar procedimiento almacenado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity <List<Object>>(reserva, HttpStatus.OK);
	}
	
	@GetMapping("/reservahoras/reservahoradia/{id_res}/{id_hrs}/{fd}")
	public ResponseEntity<?> eliminar_fechastomadas(@PathVariable String id_res,@PathVariable String id_hrs,@PathVariable String fd) throws ParseException {
		System.out.print(id_res+" id res\n" );
		System.out.print(id_hrs+" id hora\n");
		System.out.print("fd");

		
		Map<String, Object> response = new HashMap<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = simpleDateFormat.parse(fd);
		
		List<Object> reservahora = null;

		try {
			reservahora = reservaService.eliminar_fechastomadas(id_res,id_hrs,date);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al ejecutar procedimiento almacenado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity <List<Object>>(reservahora, HttpStatus.OK);
	}
	
	@GetMapping("/reservas/buscausuario/{nombre}")
	public ResponseEntity<?> findTraeUsuario(@PathVariable String nombre) {

		List<Usuario> reserva = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			reserva = reservaService.findTraeUsuario(nombre);
			System.out.print(reserva+ " fd");
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (reserva == null) {
			response.put("mensaje", "La reserva Id:".concat(nombre.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Usuario>>(reserva, HttpStatus.OK);
	}
	
	@GetMapping("/reservahoras/fecharemplazo/{id_hrs}/{fd}")
	public ResponseEntity<?> eliminar_fecharemplazo(@PathVariable String id_hrs,@PathVariable String fd) throws ParseException {
		
		Map<String, Object> response = new HashMap<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = simpleDateFormat.parse(fd);
		
		List<Object> reservahora = null;

		try {
			reservahora = reservaService.eliminar_fecharemplazo(id_hrs,date);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al ejecutar procedimiento almacenado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity <List<Object>>(reservahora, HttpStatus.OK);
	}

}
