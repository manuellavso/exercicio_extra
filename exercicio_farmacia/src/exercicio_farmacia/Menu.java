package exercicio_farmacia;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import controller.ProdutoController;
import model.Cosmetico;
import model.Medicamento;
import model.Produto;

public class Menu {
	
	private static final Scanner leia = new Scanner(System.in);
	private static final ProdutoController  produtoController = new ProdutoController();
	
	public static void main(String[] args) {

		produtoTeste();
		int opcao;

		while (true) {
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ ");
			System.out.println("│	➤ F A R M Á C I A - Java   	 ");
			System.out.println("┝━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┨ ");
			System.out.println("│1 - Adicionar produto");
			System.out.println("│2 - Listar todos os produtos");
			System.out.println("│3 - Consultar produto por ID");
			System.out.println("│4 - Atualizar produto");
			System.out.println("│5 - Deletar produto");
			System.out.println("│0 - Sair");	
			System.out.println("┖━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ ");
			System.out.print(" ➤ Digite a opção desejada: ");
			try {
				opcao = leia.nextInt();
				leia.nextLine();
			}catch(InputMismatchException e) {
				opcao = -1;
				System.out.println("Digite um número inteiro entre 0 e 5.");
				leia.nextLine();
			}
		
			switch(opcao) {
			case 0: //FECHAR PROGRAMA
				encerrar();
				keyPress();
				break;
			
			case 1:
				System.out.println("➤ Adicionar produto");
				adicionarProduto();
				keyPress();
				break;
				
			case 2:
				System.out.println("➤ Listar todos os produtos");
				listarProduto();
				keyPress();
				break;
				
			case 3:
				System.out.println("➤ Consultar produto por ID");
				procurarProdutoPorID();
				keyPress();
				break;
				
			case 4:
				System.out.println("➤ Atualizar produto");
				atualizarProduto();
				keyPress();
				break;
				
			case 5:
				System.out.println("➤ Deletar produto");
				deletarProduto();
				keyPress();
				break;
				
			default:
				System.out.println("Opção inválida!");
				keyPress();
				break;
			}
		}
				
	}
	
	
	//MÉTODO KEYPRESS
		public static void keyPress() {
			System.out.println("Pressione Enter para continuar");
			leia.nextLine();
		}
	
		
	//0. MÉTODO ENCERRAR
	public static void encerrar() {
		System.out.println("➤ Obrigado pela preferência!");
		System.out.println("- Até mais!");
		System.exit(0);
	}
	
	
	
	//1. MÉTODO ADICIONAR
	public static void adicionarProduto() {
		System.out.print("Digite o nome do produto: ");
		String nome = leia.nextLine();

		System.out.println("Digite o tipo do produto:");
		System.out.print("1 - Farmáco | 2 - Cosmético): ");
		int tipo = leia.nextInt();

		System.out.print("Digite o preço do produto: R$");
		float preco = leia.nextFloat();
		
		switch(tipo) {
		case 1 -> {
			System.out.print("Digite o valor do desconto escrito na receita: R$");
			float receita = leia.nextFloat();
			leia.nextLine();
			
			produtoController.criarProduto(new Medicamento(nome, produtoController.gerariD(0), tipo, preco, receita));
		}
		case 2 -> {
			System.out.print("Digite o valor do desconto do seu cupom: R$");
			float cupom = leia.nextFloat();
			leia.nextLine();
			
			produtoController.criarProduto(new Cosmetico(nome, produtoController.gerariD(0), tipo, preco, cupom));
		}
		default -> System.out.println("Tipo de produto inválido.");
		}
	}
	
	
	//2. MÉTODO LISTAR
	public static void listarProduto() {
		produtoController.listarTodas();
	}
	
	
	//3. MÉTODO CONSULTAR 
	public static void procurarProdutoPorID() {
		System.out.print("Digite o ID do produto: ");
		int iD = leia.nextInt();
		leia.nextLine();
		
		produtoController.consultarID(iD);
	}
	
	
	//4. MÉTODO ATUALIZAR
	public static void atualizarProduto() {
		
		System.out.print("Digite o ID do produto: ");
		int iD = leia.nextInt();
		leia.nextLine();

		Optional<Produto> produto = produtoController.buscarNaCollection(iD);
		if(produto.isPresent()) {
			//DADOS DO PRODUTO
			String nome = produto.get().getNome();
			float preco = produto.get().getPreco();
			int tipo = produto.get().getTipo();

			//ATUALIZAR NOME
			System.out.printf("Nome do produto atual: %s"
					+ "%nDigite o novo nome do produto (Pressione ENTER para manter o nome atual)", nome);
			String entrada = leia.nextLine();
			
			nome = entrada.isEmpty() ? nome : entrada;
			
			//ATUALIZAR PRECO
			System.out.printf("Preço atual: %.2f"
					+ "%nDigite o novo preço do produto (Pressione ENTER para manter o preço atual)", preco);
			entrada = leia.nextLine();
			
			preco = entrada.isEmpty() ? preco : Float.parseFloat(entrada.replace(",","."));
		
			//ATUALIZAR RECEITA(DESCONTO) OU CUPOM
			switch(tipo) {
			//ATUALIZAR VALOR DO DESCONTO
			case 1 -> {
				Medicamento medicamento = (Medicamento) produto.get();
				float receita = medicamento.getReceita();
				
				System.out.printf("Desconto da receita atual: %s"
						+ "%nDigite o desconto da nova receita (Pressione ENTER para manter o nome atual)", receita);
				entrada = leia.nextLine();
				
				receita = entrada.isEmpty() ? receita : Float.parseFloat(entrada.replace(",","."));
				
				int id = produto.get().getID();
				produtoController.atualizar(new Medicamento(nome, iD, tipo, preco, receita));
			}
			
			//ATUALIZAR VALOR DO CUPOM
			case 2 -> {
				Cosmetico cosmetico = (Cosmetico) produto.get();
				float cupom = cosmetico.getCupom();
				
				System.out.printf("Desconto do cupom atual: %s"
						+ "%nDigite o desconto do novo cupom (Pressione ENTER para manter o nome atual)", cupom);
				entrada = leia.nextLine();
				
				cupom = entrada.isEmpty() ? cupom : Float.parseFloat(entrada.replace(",","."));
				
				int id = produto.get().getID();
				produtoController.atualizar(new Cosmetico(nome,iD, tipo, preco, cupom));
			}
			
			default -> System.out.println("Tipo de produto inválido.");
			}
			
		} else {
			System.out.printf("\nO produto não foi encontrado! ");
		}
	}
	
	
	//5. MÉTODO DELETAR
	public static void deletarProduto() {
		
		System.out.print("Digite o ID do produto: ");
		int iD = leia.nextInt();
		leia.nextLine();
		
		Optional<Produto> produto = produtoController.buscarNaCollection(iD);
		if(produto.isPresent()) {
			System.out.printf("\nTem certeza que você deseja excluir o produto com ID %d (S/N)?", iD);
			String confirmacao = leia.nextLine();

			if (confirmacao.equalsIgnoreCase("S"))
				produtoController.deletar(iD);
			else
				System.out.println("\nOperação cancelada!");
		}
		else {
			System.out.println("O produto com ID" +iD+ " não foi encontrado.");
		}
	}
	
	
	//MÉTODO TESTE
	public static void produtoTeste() {
		produtoController.criarProduto(new Medicamento("Cetoconazol", produtoController.gerariD(0), 1, 40, 10));
	
		produtoController.criarProduto(new Cosmetico("Creme Babaloo", produtoController.gerariD(0), 2, 90, 25));

	}
	
}

