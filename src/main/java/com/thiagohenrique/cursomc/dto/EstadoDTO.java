package com.thiagohenrique.cursomc.dto;

import java.io.Serializable;

import com.thiagohenrique.cursomc.domain.Estado;

public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public EstadoDTO() {
		
	}

	public EstadoDTO(Estado estado) {
		super();
		id = estado.getId();
		nome = estado.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
