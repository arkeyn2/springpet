package com.example.demo.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entity.Inscripcion;
import com.example.demo.models.entity.Usuario;


public interface IInscripcionDao extends CrudRepository<Inscripcion, Long>{
	
	
	/*@Query("select re.id, re.sala.id, re.usuario.id, "
			+ "us.id, us.nombre, us.apellido, us.rut, "
			+ "ro.id, ro.nombre_rol, "
			+ "sa.id, sa.nombre_sala "
			+ "from Reserva re "
			+ "inner join Usuario us on us.id=re.usuario.id "
			+ "inner join Role ro on ro.id=us.roles.id "
			+ "inner join Sala sa on sa.id=re.sala.id "
			+ "GROUP BY re.sala.id, re.id, us.id, ro.id, sa.id "
			+ "ORDER BY re.id, us.id, ro.id, sa.id")
	public List<Reserva> findReservaMY(Long usuario_id, int m, int y);
	*/
	/*
	@Query("select re from Reserva re where re.sala.id=?1")
	public List<Inscripcion> findReservaPorIdSala(Long id);
	*/
	/*Query para traer usuario*/
	@Query("select usu from Usuario usu where usu.nombre=?1")
	public List<Usuario> findTraeUsuario(String nombre);
	
	@Query("select usu from Usuario usu where usu.nombreUsuario=?1")
	public List<Usuario> findTraenombreUsuario(String nombreusuario);

	
	@Query("select ins from Inscripcion ins inner join Usuario usu on usu.id=ins.usuario.id where usu.nombre=?1")
	public List<Inscripcion> findReservaPorUsuario(String nombre);
	
	/*procedimiento eliminacion reserva
	@Procedure("eliminar_reserva(?1)")
	public List<Object> eliminarreserva(String id);*/
	/*
	@Modifying
	@Query(value= "select cast(eliminar_reserva(?1) as text)",nativeQuery = true  )
	public List<Object> eliminarreserva(Long id);
	
	@Modifying
	@Query(value= "select cast(eliminar_fechastomadas(?1,?2,?3) as text)",nativeQuery = true  )
	public List<Object> eliminar_fechastomadas(Long id_res,Long id_hrs,Date fd);
	
	@Query("select usu from Usuario usu where usu.nombre=?1")
	public List<Usuario> buscarusuario(String nombre);
	
	@Modifying
	@Query(value= "select cast(eliminar_fechasremplazo(?1,?2) as text)",nativeQuery = true  )
	public List<Object> eliminar_fecharemplazo(Long id_hrs,Date fd);
	*/
}



