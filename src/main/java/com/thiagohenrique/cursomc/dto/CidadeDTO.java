package com.thiagohenrique.cursomc.dto;

import java.io.Serializable;

import com.thiagohenrique.cursomc.domain.Cidade;

public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CidadeDTO() {
		
	}

	public CidadeDTO(Cidade cidade) {
		super();
		id = cidade.getId();
		nome = cidade.getNome();
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
