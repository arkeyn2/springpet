package com.example.demo.models.services;

import java.util.List;

import com.example.demo.models.entity.Tipo;

public interface ITipoService {

	public List<Tipo> findAll();
	
	public Tipo findById(Long id);
	
	public Tipo save(Tipo tipo);
	
	public void delete(Long id);
}