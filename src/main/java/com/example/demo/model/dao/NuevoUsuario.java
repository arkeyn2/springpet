package com.example.demo.model.dao;

import java.sql.Date;
import java.util.Set;
import javax.xml.crypto.Data;


public class NuevoUsuario {

    private String nombre;

    private String email;

    private String apellido;

    private String rut;

    private String nombreUsuario;

    private String password;

    private Set<String> roles;

    private String plan;

    
    private Data fecha_in;

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }


	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public Data getFecha_in() {
		return fecha_in;
	}

	public void setFecha_in(Data fecha_in) {
		this.fecha_in = fecha_in;
	}
	

}