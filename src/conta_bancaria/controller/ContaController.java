package conta_bancaria.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Optional;

import conta_bancaria.model.Conta;
import conta_bancaria.repository.ContaRepository;

public class ContaController implements ContaRepository {

	// Criar a Collection ArrayList
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();

	// Variavel para controlar os numeros das contas
	int numero = 0;

	@Override
	public void procurarPorNumero(int numero) {
		Optional<Conta> conta = buscarNaCollection(numero);

		if (conta.isPresent())
			conta.get().visualizar();
		else
			System.out.printf("\nA Conta %d não foi encontrada", numero);

	}

	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}

	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("A conta foi criado com sucesso!");

	}

	@Override
	public void atualizar(Conta conta) {
		Optional<Conta> attConta = buscarNaCollection(conta.getNumero());

		if (attConta.isPresent()) {
			listaContas.set(listaContas.indexOf(attConta.get()), conta);
			System.out.println("Dados Anteriores");
			attConta.get().visualizar();
			System.out.println("Dados Anteriores");
			procurarPorNumero(attConta.get().getNumero());
			System.out.printf("\nA Conta numero %d foi atualizada com sucesso!", conta.getNumero());
		} else
			System.out.printf("\nA Conta %d não foi encontrada", numero);

	}

	@Override
	public void deletar(int numero) {
		Optional<Conta> conta = buscarNaCollection(numero);

		if (conta.isPresent()) {
			if (listaContas.remove(conta.get()) == true)
				System.out.printf("\nA Conta numero %d foi excluída com sucesso!", numero);
		} else
			System.out.printf("\nA Conta %d não foi encontrada", numero);

	}

	@Override
	public void sacar(int numero, float valor) {

		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
		Optional<Conta> conta = buscarNaCollection(numero);

		if (conta.isPresent()) {
			if (conta.get().sacar(valor) == true)
				System.out.printf("\nO Saque no valor de %s, foi efetuado com sucesso na Conta número %d",nfMoeda.format(valor), numero);

		} else
			System.out.printf("\nA Conta %d não foi encontrada", numero);
	}

	@Override
	public void depositar(int numero, float valor) {

		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
		Optional<Conta> conta = buscarNaCollection(numero);

		if (conta.isPresent()) {
			conta.get().depositar(valor);
			System.out.printf("\nO Deposito no valor de %s, foi efetuado com sucesso na Conta número %d",nfMoeda.format(valor), numero);
		} else
			System.out.printf("\nA Conta %d não foi encontrada", numero);

	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {

		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
		
		Optional<Conta> contaOrigem = buscarNaCollection(numeroOrigem);
		Optional<Conta> contaDestino = buscarNaCollection(numeroDestino);

	    if (contaOrigem.isPresent() && contaDestino.isPresent()) {
	        if (contaOrigem.get().sacar(valor)) {
	            contaDestino.get().depositar(valor);

	            System.out.printf("\nA Transferência no valor de %s, da Conta número %d para a Conta %d foi efetuada com sucesso!",
	                nfMoeda.format(valor), numeroOrigem, numeroDestino);
	        }
	    } else {
	        System.out.printf("\nA Conta de origem ou destino não foi encontrada.");
	    }
	}

	// Métodos Auxiliares

	public int gerarNumero() {
		return ++numero;
	}

	public Optional<Conta> buscarNaCollection(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero)
				return Optional.of(conta);
		}

		return Optional.empty();
	}

}
