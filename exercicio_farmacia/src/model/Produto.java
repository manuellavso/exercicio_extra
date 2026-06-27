package model;

public abstract class Produto {
	
	//ATRIBUTOS DE PRODUTO
	private String nome;
	private int iD;
	private int tipo;
	private float preco;
	
	//MÉTODOS CONSTRUTORES
	public Produto(String nome, int iD, int tipo, float preco) {
		super();
		this.nome = nome;
		this.iD = iD;
		this.tipo = tipo;
		this.preco = preco;
	}

	//GET AND SET
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getID() {
		return iD;
	}
	public void setID(int iD) {
		this.iD = iD;
	}

	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}

	
	//MÉTODO VISUALIZAR
	public void visualizar() {
		String tipo = "";
		
		switch(this.tipo) {
		case 1 -> tipo = "Medicamentos";
		case 2 -> tipo = "Cosméticos";
		}
		
		System.out.println(" ➤ Produto:   " +this.iD);
		System.out.printf("ID do produto: %d", this.iD);
		System.out.printf("%nNome do produto: %s", this.nome);
		System.out.printf("%nTipo de produto: %s", tipo);
		System.out.printf("%nPreço do produto: R$%.2f", this.preco);
		
	}
	
	
}
