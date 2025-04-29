package conta_bancaria.model;

import java.text.NumberFormat;

public class ContaCorrente extends Conta {
	
	private float limite;
	
	// Construtor da classe ContaCorrente
	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) {
		super(numero, agencia, tipo, titular, saldo);
		this.limite = limite;
	}
	// Método get para acessar o valor do limite
	public float getLimite() {
		return limite;
	}
    // Método set para alterar o valor do limite
	public void setLimite(float limite) {
		this.limite = limite;
	}
	
	// Sobrescreve o método visualizar da classe Conta para adicionar informações conta corrente
	@Override
	public boolean sacar(float valor) {
		if ((this.getSaldo() + this.limite) < valor) {
			System.out.println("\nSaldo é Insuficiente");
			return false;
		}
		//this.saldo -= valor;
		this.setSaldo(this.getSaldo() - valor);
		return true;
	}
	
	@Override
	public void visualizar() {
	    // Define o formato que será usado para exibir o limite
		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
		super.visualizar();
		// Exibe o valor do limite formatado ex:R$ 1.000,00
		System.out.println("Limite da conta: " + nfMoeda.format(this.limite));
	}
	
}
