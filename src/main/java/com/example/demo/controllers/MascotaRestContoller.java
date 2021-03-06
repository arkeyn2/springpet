package com.example.demo.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.models.entity.Mascota;
import com.example.demo.models.services.IInscripcionService;
import com.example.demo.models.services.IMascotaService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class MascotaRestContoller {

	@Autowired
	private IMascotaService mascotaService;
	private IInscripcionService reservaService;
	private final Logger log = LoggerFactory.getLogger(MascotaRestContoller.class);
	
	@GetMapping("/mascotas")
	public List<Mascota> index() {
		return mascotaService.findAll();
	}

	@GetMapping("/mascotas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Mascota mascota = null;
		Map<String, Object> response = new HashMap<>();

		try {
			mascota = mascotaService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (mascota == null) {
			response.put("mensaje", "La mascota Id:".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Mascota>(mascota, HttpStatus.OK);
	}

	@PostMapping("/mascotas")
	@ResponseStatus(HttpStatus.CREATED)
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> create(@RequestBody Mascota mascota) {

		Mascota mascotanew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			mascotanew = mascotaService.save(mascota);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el guardado de la mascota");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La hora ha sido creada con exito!");
		response.put("hora", mascotanew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/mascotas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody Mascota mascota, @PathVariable Long id) {

		Mascota mascotaActual = mascotaService.findById(id);

		Mascota mascotaUpdate = null;

		Map<String, Object> response = new HashMap<>();

		if (mascota == null) {
			response.put("mensaje", "La mascota Id:".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			mascotaActual.setNombre_mas(mascota.getNombre_mas());
			mascotaActual.setDetalle_mas(mascota.getDetalle_mas());
			mascotaActual.setImag_mas(mascota.getImag_mas());
			mascotaActual.setProvincia(mascota.getProvincia());
			mascotaActual.setRegion(mascota.getRegion());
			mascotaActual.setNumero(mascota.getNumero());
			mascotaActual.setEstado(mascota.getEstado());
			mascotaActual.setReg_mas(mascota.getReg_mas());

			mascotaUpdate = mascotaService.save(mascotaActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar actualizado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La mascota ha sido actualizado con exito!");
		response.put("hora", mascotaUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/mascotas/{id}")
	
	public ResponseEntity<?> delete(@PathVariable String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			reservaService.eliminar_inscripcion(id);
			//mascotaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La hora eliminado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@PostMapping("/mascotas/upload")
	public ResponseEntity<?>upload(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id") long id){
		Map<String, Object> response = new HashMap<>();
		
		Mascota mascota =mascotaService.findById(id);
		
		if(!archivo.isEmpty()) {
			String nombreArchivo=UUID.randomUUID().toString() + "_" +archivo.getOriginalFilename().replace(" ","");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior =mascota.getFoto();
			
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior =Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
			mascota.setFoto(nombreArchivo);
			mascotaService.save(mascota);
			response.put("mascota", mascota);
			response.put("mensahe", "Has subido correctamente la imagen:" );
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@GetMapping("/uploads/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		log.info(rutaArchivo.toString());
		
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto);
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

	

}

