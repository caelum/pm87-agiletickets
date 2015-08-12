package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	private static final int UMA_HORA = 60;

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		preco = sessao.getPreco();
		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			if(calculaFatorDisponivelSobreTotal(sessao) <= 0.05) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			} 
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			if(calculaFatorDisponivelSobreTotal(sessao) <= 0.50) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			} 
			
			if(sessao.getDuracaoEmMinutos() > UMA_HORA){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		}  
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static double calculaFatorDisponivelSobreTotal(Sessao sessao) {
		return quantidadeIngressosDisponiveis(sessao) / sessao.getTotalIngressos().doubleValue();
	}

	private static int quantidadeIngressosDisponiveis(Sessao sessao) {
		return sessao.getTotalIngressos() - sessao.getIngressosReservados();
	}

}