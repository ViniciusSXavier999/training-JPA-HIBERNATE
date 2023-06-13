package br.com.vx.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// O objetivo da classe JPAUtil é isolar a criação do EntityManager e esconder também o EntityManagerFactory().

public class JPAUtil {

	/*
	 * Na `JPAUtil.java`, criaremos um método que será responsável por criar
	 * o `EntityManager` e que fará a utilização da `factory`. Mas, não desejamos
	 * precisar, toda vez que criarmos o `EntityManager`, criar uma nova `factory`.
	 * Para garantir que a `factory` está sendo criada uma única vez na aplicação,
	 * vamos transformá-la em um atributo estático da classe.
	 * 
	 * Então, faremos `private static final EntityManagerFactory FACTORY =` (podemos
	 * colocar como "final", porque se trata de uma constante. E "FACTORY" está em
	 * maiúsculo, porque é um nome de constante). Agora vamos trazer o
	 * código `Pesistence.createEntityManagerFactory("loja")` para a `JPAUtil.java`.
	 */
	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("loja");

	/*
	 * Agora, vamos criar um método que devolve um EntityManager, nós podemos chamar
	 * de getEntityManager(). Esse é o método que vai criar um EntityManager. Quando
	 * precisarmos, em qualquer lugar no projeto, nós chamamos este método.
	 * Continuaremos fazendo return FACTORY.createEntityManager();
	 */
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}

}
