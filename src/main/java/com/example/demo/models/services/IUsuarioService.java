package com.example.demo.models.services;

import java.util.List;

import com.example.demo.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public Usuario findById(Long id );
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);

	public List<Usuario> findByRolId(Long roles_id);

}
