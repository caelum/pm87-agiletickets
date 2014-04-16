package br.com.caelum.agiletickets.controllers;

import java.util.List;

import br.com.caelum.agiletickets.domain.Agenda;
import br.com.caelum.agiletickets.domain.DiretorioDeEstabelecimentos;
import br.com.caelum.agiletickets.models.Espetaculo;
import br.com.caelum.agiletickets.models.Estabelecimento;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.google.common.base.Strings;

@Resource
public class EspetaculosController {

	private final Result result;
	private final Validator validator;
	private final Agenda agenda;
	private final DiretorioDeEstabelecimentos estabelecimentos;

	public EspetaculosController(Result result, Validator validator, Agenda agenda, DiretorioDeEstabelecimentos estabelecimentos) {
		this.result = result;
		this.validator = validator;
		this.agenda = agenda;
		this.estabelecimentos = estabelecimentos;
	}

	@Get("/espetaculos")
	public List<Espetaculo> lista() {
		// inclui a lista de estabelecimentos
		result.include("estabelecimentos", estabelecimentos.todos());
		return agenda.espetaculos();
	}

	@Post("/espetaculos")
	public void adiciona(Espetaculo espetaculo) {
		// aqui eh onde fazemos as varias validacoes
		// se nao tiver nome, avisa o usuario
		// se nao tiver descricao, avisa o usuario
		if (Strings.isNullOrEmpty(espetaculo.getNome())) {
			validator.add(new ValidationMessage("Nome do espetáculo não pode estar em branco", ""));
		}
		if (Strings.isNullOrEmpty(espetaculo.getDescricao())) {
			validator.add(new ValidationMessage("Descrição do espetáculo não pode estar em branco", ""));
		}
		validator.onErrorRedirectTo(this).lista();

		agenda.cadastra(espetaculo);
		result.redirectTo(this).lista();
	}

	// metodo antigo. aqui soh por backup
	private Estabelecimento criaEstabelecimento(Long id) {
		return estabelecimentos.todos().get(0);
	}
	
}
