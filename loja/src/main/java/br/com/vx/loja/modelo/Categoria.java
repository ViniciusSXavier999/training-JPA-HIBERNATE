package br.com.vx.loja.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *O Java enum é um tipo de dado utilizado para armazenar um conjunto de valores constantes, ou seja, são valores fixos, que 
 *não podem ser modificados. Na prática, o enum é um tipo especial de classe que utiliza a palavra-chave enum ao ser declarado. 
 * */

@Entity
@Table(name = "tb_categorias")
public class Categoria {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	public Categoria() {
		
	}
	
	
	public Categoria(String nome) {
		this.nome = nome;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	

}
