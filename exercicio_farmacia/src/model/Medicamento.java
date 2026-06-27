package model;

public class Medicamento extends Produto{
	
	//ATRIBUTO DE MEDICAMENTO
	float receita;
	
	//MÉTODOS CONSTRUTORES
	public Medicamento(String nome, int iD, int tipo, float preco, float receita) {
		super(nome, iD, tipo, preco);
		this.receita = receita;
	}

	//GET AND SET
	public float getReceita() {
		return receita;
	}
	public void setReceita(float receita) {
		this.receita = receita;
	}
	
	//SOBRESCREVER VISUALIZAR
	@Override
	public void visualizar() {
		super.visualizar();
		System.out.println("\nDesconto escrito na receita: R$" +this.receita);
		System.out.println();
	}
	

}
