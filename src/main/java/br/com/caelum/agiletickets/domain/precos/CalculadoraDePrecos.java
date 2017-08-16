package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			preco = reajustaPrecoPorDisponibilidade(sessao, 0.05, 0.10);
			
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET)) {
			preco = reajustaPrecoPorDisponibilidade(sessao, 0.50, 0.20);			
			preco = reajustaPrecoPorDuracao(sessao, preco, 60,0.10);
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			preco = reajustaPrecoPorDisponibilidade(sessao, 0.50, 0.20);
			preco = reajustaPrecoPorDuracao(sessao, preco, 60,0.10);
		}  else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	
	private static double calculaPorcentagemDisponivel (Sessao sessao) {
		int ingressosDisponiveis = sessao.getTotalIngressos() - sessao.getIngressosReservados();
		return ingressosDisponiveis / sessao.getTotalIngressos().doubleValue();
	}
	
	private static BigDecimal calculaFatorReajuste (Sessao sessao, double reajuste) {
		return sessao.getPreco().multiply(BigDecimal.valueOf(reajuste));
	}
	
	private static BigDecimal reajustaPrecoPorDisponibilidade (Sessao sessao, double fatorVagas, double fatorReajuste) {
		BigDecimal precoReajustado;
		if(calculaPorcentagemDisponivel(sessao) <= fatorVagas) { 			
			precoReajustado = sessao.getPreco().add(calculaFatorReajuste(sessao, fatorReajuste));
			return precoReajustado;			
		} else {
			precoReajustado = sessao.getPreco();
			return precoReajustado;			
		}
		
	}
	
	private static BigDecimal reajustaPrecoPorDuracao (Sessao sessao, BigDecimal preco , int fatorDuracao, double fatorReajuste) {
		BigDecimal precoReajustado = preco;
		if(sessao.getDuracaoEmMinutos() > 60){
			precoReajustado = preco.add(calculaFatorReajuste(sessao, 0.10));
		}
		return precoReajustado;
	}

}	