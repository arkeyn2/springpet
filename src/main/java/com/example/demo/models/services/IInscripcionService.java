package com.example.demo.models.services;

import java.util.Date;
import java.util.List;

import com.example.demo.models.entity.Inscripcion;
import com.example.demo.models.entity.Usuario;

public interface IInscripcionService {

	public List<Inscripcion> findAll();
	
	public Inscripcion findById(Long id);
	
	public Inscripcion save(Inscripcion reserva);
	
	public void delete(Long id);
	
	//public List<Reserva> findReservaMY(Long id,int m , int y);
	
	public List<Inscripcion> findReservaSobrescribir(Date dia,String id_hora , String id_sala);
	
	public List<Inscripcion> findReservaNoSobrescribir(Date dia,String id_hora , String id_sala);
	
	public List<Inscripcion> findReservaPorIdSala(Long id); 
	
	public List<Inscripcion> findReservahora(Long id_sala,String fecha_desde, String fecha_hasta);
	
	public List<Inscripcion> findReservaPorUsuario(String id); 
	
	public List<Object> eliminar_inscripcion(String id); 
	
	public List<Object> eliminar_fechastomadas(String id_res,String id_hrs,Date fd); 
	
	public List<Usuario> findTraeUsuario(String nombre);
	
	public List<Usuario> findTraenombreUsuario(String nombreusuario);
	
	public List<Object> eliminar_fecharemplazo(String id_hrs,Date fd); 
}


