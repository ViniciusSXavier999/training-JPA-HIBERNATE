package br.com.vx.loja.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Na JPA, precisamos lembrar que a ideia não é que ela seja uma especificação para um ORM. Com a ORM nós fazemos o mapeamento 
 * objeto-relacional. Nós precisamos desse mapeamento, dessa ligação entre o lado da orientação objetos com o lado do mundo relacional 
 * do banco de dados. Isso é feito na classe `public class Produto {`, e é ela que está representando a tabela de produtos, portanto, 
 * é assim que a indicaremos para a JPA.
 * 
 * A partir da versão 2.0 da JPA, podemos fazer tudo via anotações. Então, em cima da classe, podemos colocar uma anotação da JPA que 
 * é o `@Entity`. Assim, é como se disséssemos: JPA, está vendo essa classe `Produto`? Ela é uma entidade, ou seja, existe uma tabela 
 * no banco de dados que está mapeando, e que é o espelho dessa classe. Então, é para isso que serve essa anotação `@Entity`.
 * 
 * */

/*
 *  Então, em cima da classe, podemos colocar uma anotação da JPA que é o @Entity. Assim, é como se disséssemos: JPA, está vendo 
 *  essa classe Produto? Ela é uma entidade, ou seja, existe uma tabela no banco de dados que está mapeando, e que é o espelho 
 *  dessa classe. Então, é para isso que serve essa anotação @Entity.
 * */
@Entity

/*
 * Eventualmente, se o nome da tabela não for o mesmo da entidade, teremos que
 * ensinar isso para a JPA, porque, por padrão, ela considera que o nome da
 * tabela é o mesmo nome da entidade (no nosso caso, não é). Para fazer essa
 * configuração, adicionaremos mais uma anotação em cima da classe que é
 * o `@Table`. Apertaremos "Ctrl + Shift + O" para importar e, de novo,
 * selecionaremos `javax.persistence.Table`.
 * 
 * Na anotação `@Table`, abriremos parênteses, selecionaremos o
 * atributo `name:String - Table` com a qual passaremos o nome da tabela que
 * é `name = "produtos"`.
 */
@Table(name = "tb_produtos")
public class Produto {

	/*
	 * Só temos mais um detalhe importante para a JPA. No banco de dados, a coluna
	 * "id" é a chave primária. Nós precisamos informar qual é a "*primary key*", a
	 * chave primária da tabela no mundo relacional. Também precisamos informar para
	 * a JPA que, dos quatro atributos, o primeiro, que se chama `id`, é a chave
	 * primária, já que ele não associa automaticamente.
	 * 
	 * Em cima do atributo `id`, colocaremos uma notação chamada `@Id` e apertamos
	 * "Ctrl + Shift + O" para importar. No nosso caso, ele importou diretamente
	 * do `javax.persistence.Id`. Como, geralmente, quem cuida do `id`, da chave
	 * primária é o banco de dados e não a aplicação, também precisamos ensinar para
	 * a JPA que quem gerará o identificador não é a aplicação e, sim, o banco de
	 * dados.
	 * 
	 * Quando formos salvar um produto, o `id` estará nulo. Não tem problema, porque
	 * é o banco de dados que vai gerar o próximo `id`. Podemos configurar isso com
	 * outra notação, que colocamos em cima do atributo `id`, que é
	 * o `@GeneratedValue`, isto é, para dizer como o valor da chave primária é
	 * gerado.
	 * 
	 * Existe um parâmetro que precisamos passar que é a estratégia, strategy, isto
	 * é, qual é a estratégia de geração da chave primária. Isso dependerá do banco
	 * de dados, alguns usam SEQUENCE, outros não. Então, nas estratégias, temos
	 * três opções: IDENTITY; SEQUENCE; e TABLE. Geralmente, utilizamos a IDENTITY,
	 * quando não tem SEQUENCE no banco de dados, ou SEQUENCE, quando tem. No nosso
	 * caso, será IDENTITY, já que não temos SEQUENCE no H2.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	/*
	 * Uma curiosidade é que o nome dos atributos é exatamente igual ao nome das
	 * colunas no banco de dados. Logo, isso é algo que não precisaremos ensinar
	 * para a JPA, ela já assume que o nome da coluna é o mesmo do atributo dentro
	 * da entidade. Se fosse diferente, isto é, se o nome da coluna "descricao"
	 * fosse "desc", por exemplo, como ensinaríamos para a JPA caso não quiséssemos
	 * chamar o atributo de desc e, sim, de "descricao"?
	 * 
	 * Neste caso, nós colocaríamos, em cima do atributo, uma anotação
	 * chamada `@Column` (e apertaríamos "Ctrl + Shift + O" para importar). Da mesma
	 * maneira, existe uma atributo chamado `name`, seguido dele, passaríamos o nome
	 * da coluna no banco de dados `"desc"`. Ou seja, `"Column(name = "desc")`. É
	 * como se disséssemos para a JPA: o nome do atributo é `descricao`, mas o nome
	 * da coluna, `@Column`, é `desc`.
	 * 
	 * Desta maneira, é possível ensinar a JPA quando o nome da coluna for diferente
	 * do nome do atributo. No nosso caso, vamos apagar essa anotação, porque o nome
	 * da coluna é exatamente igual ao nome do atributo.
	 */
	@Column(name = "desc")
	private String descricao;
	private Double preco;
	private LocalDate dataCadastro = LocalDate.now();
	
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	
	
	public Produto(String nome, String descricao, Double preco, Categoria categoria) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	

}
