package br.com.caelum.agiletickets.controllers;

import br.com.caelum.agiletickets.domain.Agenda;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndexController {

	private final Result result;
	private final Agenda agenda;

	public IndexController(Result result, Agenda agenda) {
		this.result = result;
		this.agenda = agenda;
	}

	@Get("/")
	public void index() {
		result.include("sessoes", agenda.proximasSessoes(10));
	}
	
}
