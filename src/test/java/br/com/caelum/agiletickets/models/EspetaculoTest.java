package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

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
	
	/**
     * Esse metodo eh responsavel por criar sessoes para
     * o respectivo espetaculo, dado o intervalo de inicio e fim,
     * mais a periodicidade.
     * 
     * O algoritmo funciona da seguinte forma:
     * - Caso a data de inicio seja 01/01/2010, a data de fim seja 03/01/2010,
     * e a periodicidade seja DIARIA, o algoritmo cria 3 sessoes, uma 
     * para cada dia: 01/01, 02/01 e 03/01.
     * 
     * - Caso a data de inicio seja 01/01/2010, a data fim seja 31/01/2010,
     * e a periodicidade seja SEMANAL, o algoritmo cria 5 sessoes, uma
     * a cada 7 dias: 01/01, 08/01, 15/01, 22/01 e 29/01.
     * >> Primeira sessão é no primeiro dia do intervalo, os demais é somado 7 dias
     * 
     * Repare que a data da primeira sessao é sempre a data inicial.
     */
	
	@Test
	public void cria3SessoesComPeriodicidadeDiariaEntreHojeE3DiasSeguintes(){
		Espetaculo ivete = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(2);
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.DIARIA;
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertEquals(sessoes.size(),3);
	}
	
	@Test
	public void cria5SessosComPeridiocidadeSemanalEntre01e31(){
		Espetaculo ivete = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(30);
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertEquals(sessoes.size(),5);
	}
	
	@Test
	public void naoCriaSessosComPeridiocidadeSemanalEntre31e01(){
		Espetaculo ivete = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.minusDays(30);		
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertEquals(sessoes.size(),0);
	}
	
	@Test
	public void naoCriaSessoesComPeriodicidadeDiariaEntre13e10(){
		Espetaculo ivete = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.minusDays(2);
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.DIARIA;
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertEquals(sessoes.size(),0);
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
