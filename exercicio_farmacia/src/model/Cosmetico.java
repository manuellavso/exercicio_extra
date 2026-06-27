package model;

public class Cosmetico extends Produto {
	
	//ATRIBUTO DE COSMÉTICOS
	float cupom;

	//MÉTODOS CONSTRUTORES
	public Cosmetico(String nome, int iD, int tipo, float preco, float cupom) {
		super(nome, iD, tipo, preco);
		this.cupom = cupom;
	}
	//GET AND SET
	public float getCupom() {
		return cupom;
	}
	public void setCupom(float cupom) {
		this.cupom = cupom;
	}

	//SOBRESCREVER VISUALIZAR
	@Override
	public void visualizar() {
		super.visualizar();
		System.out.println("\nDesconto do cosmético: R$" +this.cupom);
		System.out.println();
	}
	
	
}
