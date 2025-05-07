package conta_bancaria.model;

public class ContaPoupanca extends Conta {

	private int aniversario;

	// Construtor da classe ContaPoupanca
	public ContaPoupanca(int numero, int agencia, int tipo, String titular, float saldo, int aniversario) {
		super(numero, agencia, tipo, titular, saldo);
		this.aniversario = aniversario;
	}

	// Método getter para acessar o valor da data de aniversário
	public int getAniversario() {
		return aniversario;
	}

	// Método setter para alterar a data de aniversário
	public void setAniversario(int aniversario) {
		this.aniversario = aniversario;
	}

	// Sobrescreve o método visualizar da classe Conta para adicionar informações da
	// conta poupança
	@Override
	public void visualizar() {
		super.visualizar();
		System.out.println("Data de aniversário da poupança: " + this.aniversario);
	}
}
