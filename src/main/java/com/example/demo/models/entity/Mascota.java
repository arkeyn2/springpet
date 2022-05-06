package com.example.demo.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "mascotas")
public class Mascota implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre_mas;
	@Column(nullable = false)
	private String detalle_mas;
	
	private String imag_mas;
	private String region;
	private String provincia;
	private String numero;
	private String estado;
	private String reg_mas;
	private String foto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNombre_mas() {
		return nombre_mas;
	}

	public void setNombre_mas(String nombre_mas) {
		this.nombre_mas = nombre_mas;
	}

	public String getDetalle_mas() {
		return detalle_mas;
	}

	public void setDetalle_mas(String detalle_mas) {
		this.detalle_mas = detalle_mas;
	}

	public String getImag_mas() {
		return imag_mas;
	}

	public void setImag_mas(String imag_mas) {
		this.imag_mas = imag_mas;
	}


	public String getReg_mas() {
		return reg_mas;
	}

	public void setReg_mas(String reg_mas) {
		this.reg_mas = reg_mas;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}


	private static final long serialVersionUID = 1L;
}
