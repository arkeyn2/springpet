package com.example.demo.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dao.ITipoDao;
import com.example.demo.models.entity.Tipo;

@Service
public class TipoServiceImpl implements ITipoService{

	@Autowired
	private ITipoDao tipoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Tipo> findAll() {
		return (List<Tipo>) tipoDao.findAll();
	}

	@Override
	public Tipo findById(Long id) {
		return tipoDao.findById(id).orElse(null);
	}

	@Override
	public Tipo save(Tipo tipo) {
		return tipoDao.save(tipo);
	}

	@Override
	public void delete(Long id) {
		tipoDao.deleteById(id);
	}

}
