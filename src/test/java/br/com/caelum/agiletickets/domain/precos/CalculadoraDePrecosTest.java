package br.com.caelum.agiletickets.domain.precos;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecosTest {

	@Test
	public void deveConsiderarQuantidadeAoCalcularPrecoTotal(){
		Sessao sessao =	SessaoTestDataBuilder
			.umaSessao()
			.deUmEspetaculoDoTipo(TipoDeEspetaculo.TEATRO)
			.comOPreco(10.0)
			.build();
		
		BigDecimal precoTotal = CalculadoraDePrecos.calculaValorIngresso(sessao, 9);
		
		assertEquals(0, BigDecimal.valueOf(90.0).compareTo(precoTotal));
	}
	
	@Test
	public void deveAplicar10PorCentoAMaisNosUltimosIngressosQuandoForCinema(){
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(TipoDeEspetaculo.CINEMA)
				.comTotalIngressos(100)
				.comIngressoReservados(95)
				.comOPreco(20.0)
				.build();

		BigDecimal precoTotal = CalculadoraDePrecos.calculaValorIngresso(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(22.0).compareTo(precoTotal));
	}

	@Test
	public void naoDeveAplicarAcrescimoNosPrimeirosIngressosQuandoForCinema(){
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(TipoDeEspetaculo.CINEMA)
				.comTotalIngressos(100)
				.comIngressoReservados(10)
				.comOPreco(20.0)
				.build();

		BigDecimal precoTotal = CalculadoraDePrecos.calculaValorIngresso(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(20.0).compareTo(precoTotal));
	}
	
	@Test
	public void deveAplicar10PorCentoAMaisNosUltimosIngressosQuandoForShow(){
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(TipoDeEspetaculo.SHOW)
				.comTotalIngressos(200)
				.comIngressoReservados(195)
				.comOPreco(100.0)
				.build();

		BigDecimal precoTotal = CalculadoraDePrecos.calculaValorIngresso(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(110.0).compareTo(precoTotal));
	}

	@Test
	public void naoDeveAplicarAcrescimoNosPrimeirosIngressosQuandoForShow(){
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(TipoDeEspetaculo.SHOW)
				.comTotalIngressos(200)
				.comIngressoReservados(15)
				.comOPreco(100.0)
				.build();

		BigDecimal precoTotal = CalculadoraDePrecos.calculaValorIngresso(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(100.0).compareTo(precoTotal));
	}
	
	@Test
	public void deveAplicar20PorCentoAMaisNosUltimosIngressosQuandoForBallet(){
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(TipoDeEspetaculo.BALLET)
				.comTotalIngressos(50)
				.comIngressoReservados(25)
				.comOPreco(500.0)
				.comDuracaoEmMinutos(50)
				.build();
		
		BigDecimal precoTotal = CalculadoraDePrecos.calculaValorIngresso(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(600.0).compareTo(precoTotal));
	}

	@Test
	public void naoDeveAplicarAcrescimoNosPrimeirosIngressosQuandoForBallet(){
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(TipoDeEspetaculo.BALLET)
				.comTotalIngressos(50)
				.comIngressoReservados(5)
				.comOPreco(500.0)
				.comDuracaoEmMinutos(50)
				.build();

		BigDecimal precoTotal = CalculadoraDePrecos.calculaValorIngresso(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(500.0).compareTo(precoTotal));
	}

	@Test
	public void deveAplicar10AMaisSeDurarMaisDeUmaHoraQuandoForBallet(){
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(TipoDeEspetaculo.BALLET)
				.comTotalIngressos(50)
				.comIngressoReservados(5)
				.comOPreco(500.0)
				.comDuracaoEmMinutos(100)
				.build();

		BigDecimal precoTotal = CalculadoraDePrecos.calculaValorIngresso(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(550.0).compareTo(precoTotal));
	}
	
	@Test
	public void deveAplicar20PorCentoAMaisNosUltimosIngressosQuandoForOrquestra(){
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(TipoDeEspetaculo.ORQUESTRA)
				.comTotalIngressos(70)
				.comIngressoReservados(40)
				.comOPreco(1000.0)
				.comDuracaoEmMinutos(60)
				.build();
		
		BigDecimal precoTotal = CalculadoraDePrecos.calculaValorIngresso(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(1200.0).compareTo(precoTotal));
	}

	@Test
	public void naoDeveAplicarAcrescimoNosPrimeirosIngressosQuandoForOrquestra(){
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(TipoDeEspetaculo.ORQUESTRA)
				.comTotalIngressos(70)
				.comIngressoReservados(10)
				.comOPreco(1000.0)
				.comDuracaoEmMinutos(60)
				.build();

		BigDecimal precoTotal = CalculadoraDePrecos.calculaValorIngresso(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(1000.0).compareTo(precoTotal));
	}

	@Test
	public void deveAplicar10AMaisSeDurarMaisDeUmaHoraQuandoForOrquestra(){
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(TipoDeEspetaculo.ORQUESTRA)
				.comTotalIngressos(70)
				.comIngressoReservados(65)
				.comOPreco(1000.0)
				.comDuracaoEmMinutos(120)
				.build();

		BigDecimal precoTotal = CalculadoraDePrecos.calculaValorIngresso(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(1300.0).compareTo(precoTotal));
	}
	
	@Test
	public void naoDeveAplicarAcrescimoQuandoForTeatro(){
		Sessao sessao =	SessaoTestDataBuilder
				.umaSessao()
				.deUmEspetaculoDoTipo(TipoDeEspetaculo.TEATRO)
				.comOPreco(10.0)
				.build();
		
		BigDecimal precoTotal = CalculadoraDePrecos.calculaValorIngresso(sessao, 1);
		
		assertEquals(0, BigDecimal.valueOf(10.0).compareTo(precoTotal));
	}

}
