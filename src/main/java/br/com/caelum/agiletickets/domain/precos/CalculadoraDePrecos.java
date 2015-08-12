package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calculaValorIngresso(Sessao sessao, Integer quantidadeIngressos) {
		BigDecimal preco;

		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			preco = calculaPrecoEspetaculo(sessao,0.05, 0.10);
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			preco = calculaPrecoEspetaculo(sessao,0.50, 0.20);			
			preco = calculaPrecoAdicional(sessao, preco);
		}  else {
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidadeIngressos));
	}

	private static BigDecimal calculaPrecoAdicional(Sessao sessao,
			BigDecimal preco) {
		if(sessao.getDuracaoEmMinutos() > 60){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

	private static BigDecimal calculaPrecoEspetaculo(Sessao sessao, Double percetualReservas, Double percentualDesconto) {
		if(sessao.percentualIngressoDisponivel() <= percetualReservas) { 
			return sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(percentualDesconto)));
		} else {
			return sessao.getPreco();
		}
		
	}

}