package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveCriarUmaSessaoDiaria() {
		Espetaculo espetaculo = new Espetaculo();
		
		LocalDate inicio = new LocalDate(2015, 8, 13);
		LocalDate fim = new LocalDate(2015, 8, 13);
		LocalTime horario = new LocalTime(20, 0, 20);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(espetaculo, sessoes.get(0).getEspetaculo());
		Assert.assertEquals("13/08/15", sessoes.get(0).getDia());
		Assert.assertEquals("20:00", sessoes.get(0).getHora());
	}
	
	@Test
	public void deveCriarDuasSessoesDiarias() {
		Espetaculo espetaculo = new Espetaculo();
		
		LocalDate inicio = new LocalDate(2015, 8, 13);
		LocalDate fim = new LocalDate(2015, 8, 14);
		LocalTime horario = new LocalTime(20, 0, 20);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		
		Assert.assertEquals(2, sessoes.size());
	}
	
	@Test
	public void deveCriarUmaSessaoSemanalCom1Dia() {
		Espetaculo espetaculo = new Espetaculo();
		
		LocalDate inicio = new LocalDate(2015, 8, 13);
		LocalDate fim = new LocalDate(2015, 8, 13);
		LocalTime horario = new LocalTime(20, 0, 20);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);	
		
		Assert.assertEquals(1, sessoes.size());
	}
	
	@Test
	public void deveCriarUmaSessaoSemanalCom6Dias() {
		Espetaculo espetaculo = new Espetaculo();
		
		LocalDate inicio = new LocalDate(2015, 8, 13);
		LocalDate fim = new LocalDate(2015, 8, 19);
		LocalTime horario = new LocalTime(20, 0, 20);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);	
		
		Assert.assertEquals(1, sessoes.size());
	}
	
	@Test
	public void deveCriarDuasSessoesSemanaisCom7Dias() {
		Espetaculo espetaculo = new Espetaculo();
		
		LocalDate inicio = new LocalDate(2015, 8, 13);
		LocalDate fim = new LocalDate(2015, 8, 20);
		LocalTime horario = new LocalTime(20, 0, 20);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);	
		
		Assert.assertEquals(2, sessoes.size());
	}
	
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
	
}
