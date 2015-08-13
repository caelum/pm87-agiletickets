package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calculaValorFinal(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		TipoDeEspetaculo tipoDeEspetaculo = sessao.getEspetaculo().getTipo();

		if (tipoDeEspetaculo.equals(TipoDeEspetaculo.CINEMA) || tipoDeEspetaculo.equals(TipoDeEspetaculo.SHOW)) {

			preco = aplicaReajuste(sessao, 0.05, 0.10);

		} else if (tipoDeEspetaculo.equals(TipoDeEspetaculo.BALLET)) {

			preco = aplicaReajuste(sessao, 0.50, 0.20);
			if (sessao.getDuracaoEmMinutos() > 60) {
				preco = aplicaReajuste(sessao, 1.00, 0.10);
			}

		} else if (tipoDeEspetaculo.equals(TipoDeEspetaculo.ORQUESTRA)) {

			preco = aplicaReajuste(sessao, 0.50, 0.20);

			if (sessao.getDuracaoEmMinutos() > 60) {
				preco = aplicaReajuste(sessao, 1, 0.10);
			}

		} else {
			preco = sessao.getPreco();
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal aplicaReajuste(Sessao sessao, double percentualIngressosDisponiveis, double percentualAcrescimo) {
		BigDecimal preco;
		if ((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= percentualIngressosDisponiveis) {
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(percentualAcrescimo)));
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}

}