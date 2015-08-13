package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	private static final int UMA_HORA = 60;

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco = sessao.getPreco();
		
		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			if(Sessao.calculaFatorDisponivelSobreTotal(sessao) <= 0.05) { 
				double fatorReajuste = 0.10;
				preco = Sessao.reajustarSessao(sessao, fatorReajuste);
			} 
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			if(Sessao.calculaFatorDisponivelSobreTotal(sessao) <= 0.50) { 
				double fatorReajuste = 0.20;
				preco = Sessao.reajustarSessao(sessao, fatorReajuste);
			} 
			
			if(sessao.getDuracaoEmMinutos() > UMA_HORA){
				double fatorReajuste = 0.10;
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(fatorReajuste)));
			}
		}  
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}