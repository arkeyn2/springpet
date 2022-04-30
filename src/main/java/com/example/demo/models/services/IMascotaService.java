package com.example.demo.models.services;

import java.util.List;

import com.example.demo.models.entity.Mascota;

public interface IMascotaService {
	
	public List<Mascota> findAll();
	
	public Mascota findById(Long id);
	
	public Mascota save(Mascota mascota);
	
	public void delete(Long id);

}
