package conta_bancaria;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import conta_bancaria.controller.ContaController;
import conta_bancaria.model.Conta;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;
import conta_bancaria.util.Cores;

public class Menu {

	public static void main(String[] args) {

		Scanner leia = new Scanner(System.in);

		ContaController contas = new ContaController();

		int opcao, numero, agencia, tipo, aniversario;
		String titular;
		float saldo, limite;

		// Dados para teste

		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000.00f, 100.00f);
		contas.cadastrar(cc1);

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 123, 2, "Maria da Silva", 1000.00f, 12);
		contas.cadastrar(cp1);

		while (true) {

			System.out.println(Cores.ANSI_YELLOW_BACKGROUND + Cores.TEXT_BLUE_BOLD
					+ "█▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀█");
			System.out.println("█                                                   █");
			System.out.println("█                BANCO DO BRAZIL COM Z              █");
			System.out.println("█                                                   █");
			System.out.println("█▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀█");
			System.out.println("█                                                   █");
			System.out.println("█           1 - Criar Conta                         █");
			System.out.println("█           2 - Listar todas as Contas              █");
			System.out.println("█           3 - Buscar Conta por Numero             █");
			System.out.println("█           4 - Atualizar Dados da Conta            █");
			System.out.println("█           5 - Apagar Conta                        █");
			System.out.println("█           6 - Sacar                               █");
			System.out.println("█           7 - Depositar                           █");
			System.out.println("█           8 - Transferir valores entre Contas     █");
			System.out.println("█           9 - Sair                                █");
			System.out.println("█                                                   █");
			System.out.println("█▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀█");
			System.out.println("█Entre com a opção desejada:                        █");
			System.out.println("█                                                   █" + Cores.TEXT_RESET);

			opcao = leia.nextInt();

			if (opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println("Criar Conta\n\n");

				System.out.println("Digite o número da Agencia:");
				agencia = leia.nextInt();
				System.out.println("Digite o nome do Titular:");
				leia.skip("\\R");
				titular = leia.nextLine();
				System.out.println("Digite tipo da conta (1 - CC | 2 - CP:");
				tipo = leia.nextInt();
				System.out.println("Digite o Saldo inicial da conta:");
				saldo = leia.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o limite da conta:");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do aniversario da conta:");
					aniversario = leia.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}

				}

				keyPress();
				break;
			case 2:
				System.out.println("Listar todas as Contas\n\n");
				contas.listarTodas();

				keyPress();
				break;
			case 3:
				System.out.println("Consultar dados da Conta - por número\n\n");

				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();

				contas.procurarPorNumero(numero);

				keyPress();
				break;
			case 4:
				System.out.println("Atualizar dados da Conta\n\n");
				
				// Informe o numero da conta
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();

				Optional<Conta> conta = contas.buscarNaCollection(numero);
				
				// Existe?
				if (conta.isPresent()) {

					// Atualizar dados
					System.out.println("Digite o número da Agencia:");
					agencia = leia.nextInt();
					
					System.out.println("Digite o nome do Titular:");
					leia.skip("\\R");
					titular = leia.nextLine();
					
					// Recuperar o tipo da conta
					tipo = conta.get().getTipo();

					System.out.println("Digite o Saldo inicial da conta:");
					saldo = leia.nextFloat();
					
					// Identificar o tipo
					switch (tipo) {
						case 1 -> { // Se for conta Corrente
							System.out.println("Digite o limite da conta:");
							limite = leia.nextFloat();
							contas.cadastrar(
									new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
						}
						case 2 -> { // Se for Conta Poupança
							System.out.println("Digite o dia do aniversario da conta:");
							aniversario = leia.nextInt();
							contas.cadastrar(
									new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
						}

					}

				} else // Caso não exista a conta
					System.out.printf("\nA conta número %d não existe!", numero);

				keyPress();
				break;
			case 5:
				System.out.println("Apagar a Conta\n\n");

				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();

				contas.deletar(numero);

				keyPress();
				break;
			case 6:
				System.out.println("Saque\n\n");

				keyPress();
				break;
			case 7:
				System.out.println("Depósito\n\n");

				keyPress();
				break;
			case 8:
				System.out.println("Transferência entre Contas\n\n");

				keyPress();
				break;
			default:
				System.out.println("\nOpção Inválida!\n");
				break;
			}
		}
	}

	public static void sobre() {
		System.out.println("\n***********************************************************");
		System.out.println("Projeto Desenvolvido por:Felipe da Silva Peronica Ferreira   ");
		System.out.println("Felipe Peronica - felipeperonica@gmail.com                   ");
		System.out.println("https://github.com/feperonica/                               ");
		System.out.println("***********************************************************");
	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.err.println("Ocorreu um erro ao tentar ler o teclado");

		}
	}

}
