package br.com.caelum.agiletickets.controllers;

import javax.inject.Inject;

import br.com.caelum.agiletickets.domain.Agenda;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;

@Controller
public class IndexController {

	private Result result;
	private Agenda agenda;
	
	/** @deprecated CDI eyes only*/
	protected IndexController() {
	}

	@Inject
	public IndexController(Result result, Agenda agenda) {
		this.result = result;
		this.agenda = agenda;
	}

	@Get("/")
	public void index() {
		result.include("sessoes", agenda.proximasSessoes(10));
	}
	
}
