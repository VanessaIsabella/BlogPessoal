package com.generation.blogpessoal.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//indica ao spring que o objeto abaixo vai ser uma tabela no banco de dados
@Entity

//dá um nome para a tabela a ser criada. sem ela a tabela é criada com o nome do objeto
@Table(name="tb_postagem")

public class PostagemModel {
	
	//indica que o id da tabela será uma chave primária
	@Id
	
	//indica que a chave primária será auto increment
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	//@NotBlank não aceita dado nulo ou vazio, ou com espaço
	//@NotNull não aceita dado nulo. obrigatório dado
	@NotBlank(message="O atributo título é obrigatório e não pode utilizar espaços em branco.")
	@Size(min=5, max=100, message="O campo precisa ter no mínimo 4 dígitos")
	private String título;
	
	@NotNull(message="O atributo texto é obrigatório e não pode utilizar espaços em branco.")
	@Size(min=5, max=100, message="O campo precisa ter no mínimo 4 dígitos")
	private String texto;
	
	@UpdateTimestamp
	private LocalDateTime date;
	
	
/*RECURSIVIDADE = A api não tem um limite de requisicoes= looping infinito*/ 
/*para evitar isso temos @ignoreproperties */
/*para travar a aplicacao e evitar o looping infinito*/
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private TemaModel tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	public TemaModel getTema() {
		return tema;
	}

	public void setTema(TemaModel tema) {
		this.tema = tema;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTítulo() {
		return título;
	}

	public void setTítulo(String título) {
		this.título = título;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}




