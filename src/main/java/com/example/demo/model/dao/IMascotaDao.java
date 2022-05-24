package com.example.demo.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entity.Mascota;

public interface IMascotaDao extends CrudRepository<Mascota, Long>{


}
