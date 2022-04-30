package com.example.demo.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dao.IMascotaDao;
import com.example.demo.models.entity.Mascota;

@Service
public class MascotaServiceImpl implements IMascotaService{

	@Autowired
	private IMascotaDao mascotaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Mascota> findAll() {
		return (List<Mascota>) mascotaDao.findAll();
	}

	@Override
	public Mascota findById(Long id) {
		return mascotaDao.findById(id).orElse(null);
	}

	@Override
	public Mascota save(Mascota mascota) {
		return mascotaDao.save(mascota);
	}

	@Override
	public void delete(Long id) {
		mascotaDao.deleteById(id);
	}

}
