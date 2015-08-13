package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;








import java.util.List;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void umaSesao(){
		LocalDate dataInicio = new LocalDate(2015,8,13);
		LocalDate dataFim = new LocalDate(2015,8,13);
		Espetaculo espetaculo = new Espetaculo();
		LocalTime horario = new LocalTime(20,0,0);
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicio, dataFim, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(dataInicio, sessoes.get(0).getInicio().toLocalDate());
		Assert.assertEquals("20:00", sessoes.get(0).getHora());
		Assert.assertEquals(espetaculo, sessoes.get(0).getEspetaculo());	
	}
	@Test
	public void duasSessoes(){
		LocalDate dataInicio = new LocalDate(2015,8,13);
		LocalDate dataFim = new LocalDate(2015,8,14);
		Espetaculo espetaculo = new Espetaculo();
		LocalTime horario = new LocalTime(20,0,0);
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicio, dataFim, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(2, sessoes.size());
		Assert.assertEquals(dataInicio, sessoes.get(0).getInicio().toLocalDate());
		Assert.assertEquals(dataInicio, sessoes.get(1).getInicio().toLocalDate());
		Assert.assertEquals("20:00", sessoes.get(0).getHora());
		Assert.assertEquals("20:00", sessoes.get(1).getHora());
		Assert.assertEquals(espetaculo, sessoes.get(0).getEspetaculo());
		Assert.assertEquals(espetaculo, sessoes.get(1).getEspetaculo());
	}

	
}
