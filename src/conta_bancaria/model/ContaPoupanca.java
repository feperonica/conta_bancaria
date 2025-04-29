package conta_bancaria.model;

public class ContaPoupanca extends Conta {

	private int aniversario;

	// Construtor da classe ContaPoupanca
	public ContaPoupanca(int numero, int agencia, int tipo, String titular, float saldo, int aniversario) {
		super(numero, agencia, tipo, titular, saldo);
		this.aniversario = aniversario;
	}

	// M�todo getter para acessar o valor da data de anivers�rio
	public int getAniversario() {
		return aniversario;
	}

	// M�todo setter para alterar a data de anivers�rio
	public void setAniversario(int aniversario) {
		this.aniversario = aniversario;
	}

	// Sobrescreve o m�todo visualizar da classe Conta para adicionar informa��es da
	// conta poupan�a
	@Override
	public void visualizar() {
		super.visualizar();
		System.out.println("Data de anivers�rio da poupan�a: " + this.aniversario);
	}
}
