package br.com.vx.loja.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.vx.loja.dao.ProdutoDao;
import br.com.vx.loja.modelo.Categoria;
import br.com.vx.loja.modelo.Produto;
import br.com.vx.loja.util.JPAUtil;

public class CadastroDeProdutos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Produto celular = new Produto("Iphone xr", "Lindo e veloz", 2000.20, Categoria.CELULARES);


		/*
		 * Na JPA, não criamos manualmente o `EntityManager`. NA JPA, o padrão de
		 * projeto utilizado é o *factory*. Assim, existe
		 * uma *factory* de `EntityManager`. Para criar o `EntityManager`, precisamos
		 * do `EntityManagerFactory`, ele tem o método que faz a construção
		 * do `EntityManager`. Então, antes de criar o `EntityManager`, precisamos criar
		 * outro objeto, que é o `EntityManagerFactory`. Nos padrões de projeto,
		 * "*design patterns*", existe esse padrão de projeto chamado *factory*, e, há
		 * uma *factory* para isolar a criação do `EntityManager`.
		 * 
		 */

		/*
		 * Então, precisamos criar o EntityManagerFactory. Nós temos uma
		 * variável EntityManagerFactory e a chamamos de factory. Em teoria,
		 * continuaríamos fazendo new EntityManagerFactory, mas não é assim. Outra
		 * classe foi criada na JPA e se chama  Persistence, e ela tem um método
		 * estático chamado CreateEntityManagerFactory. Então, basta chamar 
		 * Persistence.createEntityManagerFactory()
		 */
		// ele tem um método que faz a construção do entityMANAGER
		
	//	EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");

		/*
		 * Agora precisamos descobrir como pegar o objeto `celular` e fazer
		 * o *insert* na tabela de "produtos". Como isso funcionará na JPA? No JDBC,
		 * toda a integração com o banco de dados era feita com uma classe
		 * chamada *connection*, nós precisávamos abrir uma conexão e, a partir dela,
		 * fazer todo o trabalho para acessar o banco de dados.
		 * 
		 * Na JPA, tem algo parecido, que não é bem uma conexão, mas uma interface que
		 * faz a ligação do Java com o banco de dados, que é uma interface
		 * chamada `EntityManager`. Essa classe funciona como se fosse o gerente, o
		 * "*manager*" das entidades, ou ainda, o gestor das entidades.
		 * 
		 * Toda vez que desejarmos acessar o banco de dados, seja para salvar, excluir,
		 * atualizar, carregar, fazer um *select*, ou qualquer outra operação que
		 * quisermos fazer no banco de dados com a JPA, nós utilizaremos a
		 * interface `EntityManager`.
		 */

	//	EntityManager em = factory.createEntityManager();

		/*
		 * precisaremos de um ProdutoDao dao = new ProdutoDao(em). Quando instanciamos
		 * um ProdutoDao, nós temos que passar um em (EntityManager), e ele foi criado
		 * em:
		 * 
		 * E toda a parte de transação é feita na classe, em vez de ficar na classe DAO.
		 * Seguindo, em.persist(celular); virará dao.cadastrar(celular); (estamos
		 * passando, portanto, o produto celular).
		 */
		
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao dao = new ProdutoDao(em);

		/*
		 * No `persistence.xml`, na tag `persistence-unit`, além do `name`, nós temos
		 * o `transaction-type`. Nós até comentamos anteriormente que temos dois
		 * valores `"RESOURCE_LOCAL"` ou `"JTA"`. O `"JTA"` é indicado para se
		 * estivermos em um servidor de aplicação, que controla a transação.
		 * 
		 * Mas, esse não é o nosso caso, estamos como `"RESOURCE_LOCAL"`, ou seja, não
		 * temos o controle de transação automático, por isso, ele não fez o *insert*,
		 * porque não delimitamos uma transação. Portanto, ele não começou uma transação
		 * e não dispará um insert no banco de dados. Antes de fazer o `pesist`, temos
		 * que chamar `em.getTransaction().begin();`.
		 */
		em.getTransaction().begin();

		/*
		 * O que queremos fazer é pegar o objeto Produto, que está na variável celular,
		 * e fazer um insert no banco de dados, ou seja, queremos inserir um novo
		 * registro no banco de dados. Para isso, no objeto EntityManager() existe um
		 * método chamado persist().
		 */

		 dao.cadastrar(celular); //isso vai virar o método da minha classe DAO em.persist(celular);

		/*
		 * É como se disséssemos ao JPA e ao EntityManager que pegassem a
		 * transação begin() e a iniciasse. Dentro dela, rodaremos quais são as
		 * operações . No nosso caso, é apenas uma, o persist(). Terminado, temos que
		 * commitar essa transação no banco de dados, em.getTransaction().commit();.
		 */
		em.getTransaction().commit();

		em.close();

	}

}
