package biblioteca.ui;

import java.util.List;

import biblioteca.dao.AutorDao;
import biblioteca.dao.LivroDao;
import biblioteca.modelo.Autor;
import biblioteca.modelo.Livro;

public class InterfaceLivroTexto extends InterfaceModeloTexto {

	private LivroDao dao;
	private AutorDao autorDao;
	
	public InterfaceLivroTexto() {
		super();
		dao = new LivroDao();
		autorDao = new AutorDao();
	}
	
	@Override
	public void adicionar() {
		System.out.println("Adicionar livro");
		System.out.println();
		
		Livro novaConta = obtemDadosConta(null);	
		dao.insert(novaConta);
	}

	private Livro obtemDadosConta(Livro livro) {
		System.out.print("Insira o título do livro: ");
		String titulo = entrada.nextLine();
		
		System.out.print("Insira o ano de publicação: ");
		int anoPublicacao = entrada.nextInt();
		entrada.nextLine();
		
		System.out.print("Insira a editora: ");
		String editora = entrada.nextLine();
		
		System.out.print("Insira o ID do autor: ");
		int idAutor = entrada.nextInt();
		
		Autor autor = autorDao.getByKey(idAutor);
		
		return new Livro(0, titulo, anoPublicacao, editora, autor);
	}

	@Override
	public void listarTodos() {
		List<Livro> livros = dao.getAll();
		
		System.out.println("Lista de livros");
		System.out.println();
		
		System.out.println("id\tTítulo\tAnoPublicacao\tEditora\tNome do Autor");
		
		for (Livro livro : livros) {
			imprimeItem(livro);
		}
	}

	@Override
	public void editar() {
		listarTodos();
		
		System.out.println("Editar livro");
		System.out.println();
		
		System.out.print("Entre o id da livro: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		Livro contaAModificar = dao.getByKey(id);
		
		Livro novaConta = obtemDadosConta(contaAModificar);
		
		novaConta.setId(id);
		
		dao.update(novaConta);
	}

	@Override
	public void excluir() {
		listarTodos();
		
		System.out.println("Excluir livro");
		System.out.println();
		
		System.out.print("Entre o id da livro: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		dao.delete(id);
	}

}
