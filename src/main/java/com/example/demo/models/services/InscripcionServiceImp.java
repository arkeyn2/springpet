package com.example.demo.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dao.IInscripcionDao;
import com.example.demo.models.entity.Inscripcion;
import com.example.demo.models.entity.Usuario;

@Service
public class InscripcionServiceImp implements IInscripcionService{

	@Autowired
	private IInscripcionDao reservaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Inscripcion> findAll() {
		return (List<Inscripcion>) reservaDao.findAll();
	}

	@Override
	public Inscripcion findById(Long id) {
		return reservaDao.findById(id).orElse(null);
	}

	@Override
	public Inscripcion save(Inscripcion reserva) {
		return reservaDao.save(reserva);
	}

	@Override
	public void delete(Long id) {
		reservaDao.deleteById(id);;
	}

	/*@Override
	public List<Reserva> findReservaMY(Long id, int m, int y) {
		return reservaDao.findReservaMY(id, m, y);
	}*/
/*
	@Override
	public List<Inscripcion> findReservaPorIdSala(Long id_sala) {
		return reservaDao.findReservaPorIdSala(id_sala);
	}
*/
	@Override
	public List<Inscripcion> findReservahora(Long id_sala, String fecha_desde, String fecha_hasta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Inscripcion> findReservaPorUsuario(String Nombre_usu) {
		return reservaDao.findReservaPorUsuario(Nombre_usu);
	}
	
	@Override
	public List<Object> eliminar_inscripcion(String id_inscripcion) {
		long id=Long.parseLong(id_inscripcion);
		//System.out.println(id_reserva);
		return reservaDao.eliminar_inscripcion(id);
	}
/*
	@Override
	public List<Object> eliminar_fechastomadas(String id_res,String id_hrs,Date fd) {
		long id=Long.parseLong(id_res);
		long id2=Long.parseLong(id_hrs);
		return reservaDao.eliminar_fechastomadas(id,id2,fd);
	}
	
	@Override
	public List<Usuario> findTraeUsuario(String nombre) {
		return reservaDao.buscarusuario(nombre);
	}
	
	@Override
	public List<Object> eliminar_fecharemplazo(String id_hrs,Date fd) {
		long id2=Long.parseLong(id_hrs);
		return reservaDao.eliminar_fecharemplazo(id2,fd);
	}
*/
	@Override
	public List<Usuario> findTraeUsuario(String nombre) {
		return reservaDao.findTraeUsuario(nombre);
	}
	
	@Override
	public List<Usuario> findTraenombreUsuario(String nombreusuario) {
		return reservaDao.findTraenombreUsuario(nombreusuario);
	}

	@Override
	public List<Inscripcion> findReservaSobrescribir(Date dia, String id_hora, String id_sala) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inscripcion> findReservaNoSobrescribir(Date dia, String id_hora, String id_sala) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inscripcion> findReservaPorIdSala(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Object> eliminar_fechastomadas(String id_res, String id_hrs, Date fd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> eliminar_fecharemplazo(String id_hrs, Date fd) {
		// TODO Auto-generated method stub
		return null;
	}
}
