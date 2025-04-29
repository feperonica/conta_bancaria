package conta_bancaria.model;

import java.text.NumberFormat;

public class Conta {

	// Atributos da Classe
	private int numero;
	private int agencia;
	private int tipo;
	private String titular;
	private float saldo;
	
	// M�todo Construtor
	public Conta(int numero, int agencia, int tipo, String titular, float saldo) {
		this.numero = numero;
		this.agencia = agencia;
		this.tipo = tipo;
		this.titular = titular;
		this.saldo = saldo;
	}
	
	public Conta() {}

	// M�todos Get e Set
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	// M�todos Banc�rios
	public boolean sacar(float valor) {
		if (this.saldo < valor) {
			System.out.println("\nSaldo � Insuficiente");
			return false;
		}
		//this.saldo -= valor;
		this.setSaldo(this.saldo - valor);
		return true;
	}
	
	public void depositar(float valor) {
		//this.saldo += valor;
		this.setSaldo(this.saldo + valor);

	}
	
	
	// M�todos para visualizar os dados da conta
	public void visualizar() {
		
		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
		
		String tipo = "";
		
		switch(this.tipo) {
			case 1 -> tipo = "Conta Corrente";
			case 2 -> tipo = "Conta Poupan�a";
			default -> tipo = "Invalido";
			
		}
		
		System.out.println("********************************");
		System.out.println("DADOS DA CONTA					");
		System.out.println("********************************");
		System.out.println("Titular da Conta: " + this.titular);
		System.out.println("Numero da Conta: " + this.numero);
		System.out.println("Numero da Agencia: " + this.agencia);
		System.out.println("Tipo da conta: " + tipo);
		System.out.println("Saldo da Conta: " + nfMoeda.format(this.saldo));
		
	}
}
