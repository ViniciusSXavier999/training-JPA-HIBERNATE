package br.com.vx.loja.dao;



import javax.persistence.EntityManager;

import br.com.vx.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager em;

	/*
	 * Vamos usar a ideia de injeção de dependências para não deixar a classe DAO
	 * ser responsável por criar e gerenciar o EntityManager, então, criaremos um
	 * construtor apertando o botão direito e selelcionando
	 * "Source > Generate Cronstuctor using Fields" e, na próxima tela, marcaremos o
	 * "em" (EntityManager) e apertaremos "Generate".
	 */

	/*
	 * Quem for instanciar a classe ProdutoDao terá que passar o EntityManager.
	 * Portanto, a classe DAO não é responsável por criar e nem gerenciar
	 * o EntityManager, ela simplesmente o recebe pronto para ser utilizado.
	 */
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	/*
	 * Agora criaremos o método `public void cadastrar()` que receberá, como
	 * parâmetro, um produto, isto é, `public void cadastrar(Produto produto)`.
	 * 
	 * Este é o método onde cadastraremos um produto no banco de dados utilizando a
	 * JPA. Teremos, basicamente, uma única linha `this.em.persist(produto);`(this,
	 * ponto, entity manager, e passamos o produto).
	 */
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
		    }


}
