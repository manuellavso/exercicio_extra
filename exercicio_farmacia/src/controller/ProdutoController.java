package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Produto;
import repository.ProdutoRepository;

public class ProdutoController implements ProdutoRepository{
	
	//AUTO NUMERAÇÃO DE ID
	private List<Produto> listaProduto = new ArrayList<Produto>();
	int iD = 0;

	
	@Override
	public void criarProduto(Produto produto) {
		listaProduto.add(produto);
		System.out.println("O produto que possui ID " +produto.getID()+ " foi cadastrado com sucesso!");
		
	}

	
	@Override
	public void listarTodas() {
		for(var produto : listaProduto) {
			produto.visualizar();
		}
	}

	
	@Override
	public void consultarID(int iD) {
		
		Optional<Produto> produto = buscarNaCollection(iD);
		if(produto.isPresent())
			produto.get().visualizar();
		else
			System.out.println("O produto com o ID " +iD+ " não foi encontrado.");
	}

	
	@Override
	public void atualizar(Produto produto) {
		Optional<Produto> buscaProduto = buscarNaCollection(produto.getID());
		if(buscaProduto.isPresent()) {
			listaProduto.set(listaProduto.indexOf(buscaProduto.get()), produto);
			System.out.println("O produto com ID " +produto.getID()+ " foi atualizado com sucesso!");
		} 
		else
			System.out.println("O produto com ID " +produto.getID()+ " não foi encontrado.");
	}

	
	@Override
	public void deletar(int iD) {
		Optional<Produto> produto = buscarNaCollection(iD);
		if(produto.isPresent()) {
			if(listaProduto.remove(produto.get()));
			System.out.println("O produto com ID " +iD+ " foi excluído com sucesso!");
		}
		else
			System.out.println("O produto com ID " +iD+ " não foi encontrado.");
		
	}
	
	
	
	//MÉTODO AUXILIAR AUTO NUMERAÇÃO ID
	public int gerariD(int valor) {
		return ++iD;
	}
	
	
	//MÉTODO ENCONTRAR PRODUTO POR ID
	public Optional<Produto> buscarNaCollection(int iD){
		for(var produto : listaProduto) {
			if(produto.getID() == iD)
				return Optional.of(produto);
		}
		return Optional.empty();
	}

	
}
