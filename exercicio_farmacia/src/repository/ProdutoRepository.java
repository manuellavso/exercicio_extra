package repository;

import model.Produto;

public interface ProdutoRepository {
	
	//MÉTODOS CRUD - CREATE, READ, UPDATE AND DELETE
	public void criarProduto(Produto produto);
	public void listarTodas();
	public void consultarID(int iD);
	public void atualizar(Produto produto);
	public void deletar(int iD);

}
