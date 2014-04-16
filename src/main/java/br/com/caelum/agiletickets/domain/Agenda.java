package br.com.caelum.agiletickets.domain;

import java.util.List;

import br.com.caelum.agiletickets.models.Espetaculo;
import br.com.caelum.agiletickets.models.Sessao;

public interface Agenda {

	List<Espetaculo> espetaculos();

	void cadastra(Espetaculo espetaculo);

	Espetaculo espetaculo(Long espetaculoId);
	
	void agende(List<Sessao> sessoes);
	
	List<Sessao> proximasSessoes(int maximo);
	
	Sessao sessao(Long sessaoId);

}
