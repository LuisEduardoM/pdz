package com.br.dto;

import com.br.entity.Categoria;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoriaRequestDTO {

	@NotBlank(message = "Nome")
	@Size(min = 3, max = 50, message = "Nome")
	private String nome;
	
	public Categoria converterParaEntidade() {
		return new Categoria(nome);
	}
	
	public Categoria converterParaEntidade(Long codigo) {
		return new Categoria(codigo, nome);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
