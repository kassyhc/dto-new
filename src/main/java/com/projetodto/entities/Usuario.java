package com.projetodto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //Comando lombok que remove os get e setters //
@Entity // transforma a tabela em entidade // 
@NoArgsConstructor //Remove o metódo construtor //
@AllArgsConstructor //Adiciona o metódo construtor de maneira oculta //
@Table(name = "usuario") // nome da tabela //

public class Usuario { // declara que a class é publica //

	@Id	// indica que é um id //
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement //
	private Long id; // declara a variavel//
	
	@NotNull // não pemite que o valor seja vazio //
	@NotBlank // não pemite que o valor seja branco //
	private String nome; // declara a variavel//
	
	@NotNull // não pemite que o valor seja vazio //
	@NotBlank // não pemite que o valor seja vazio //
	private String senha; // declara a variavel//
	
	private String permissao; // declara a variavel//
	
	public Usuario(String nome, String senha) { // declara valores que serão recebidos pelo usuário //
		this.nome = nome; //indica o valor nome //
		this.senha = senha; // indica o valor senha //
	}
	
	
}
