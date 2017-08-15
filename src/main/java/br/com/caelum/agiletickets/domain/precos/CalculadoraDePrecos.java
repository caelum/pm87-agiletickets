package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			if(calculaPorcentagemDisponivel(sessao) <= 0.05) { 
				preco = sessao.getPreco().add(calculaFatorReajuste(sessao, 0.10));
			} else {
				preco = sessao.getPreco();
			}
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET)) {
			if(calculaPorcentagemDisponivel(sessao) <= 0.50) { 
				preco = sessao.getPreco().add(calculaFatorReajuste(sessao, 0.20));
			} else {
				preco = sessao.getPreco();
			}
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = sessao.getPreco().add(calculaFatorReajuste(sessao, 0.10));
			}
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			if(calculaPorcentagemDisponivel(sessao) <= 0.50) { 
				preco = sessao.getPreco().add(calculaFatorReajuste(sessao, 0.20));
			} else {
				preco = sessao.getPreco();
			}

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(calculaFatorReajuste(sessao, 0.10));
			}
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

}	