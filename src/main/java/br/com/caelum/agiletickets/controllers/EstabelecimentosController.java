package br.com.caelum.agiletickets.controllers;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.agiletickets.domain.DiretorioDeEstabelecimentos;
import br.com.caelum.agiletickets.models.Estabelecimento;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;

import com.google.common.base.Strings;

@Controller
public class EstabelecimentosController {

	private Result result;
	private Validator validator;
	private DiretorioDeEstabelecimentos diretorio;

	public EstabelecimentosController() {
	}

	@Inject
	public EstabelecimentosController(Result result, Validator validator, DiretorioDeEstabelecimentos diretorio) {
		this.result = result;
		this.validator = validator;
		this.diretorio = diretorio;
	}

	@Get("/estabelecimentos")
	public List<Estabelecimento> lista() {
		return diretorio.todos();
	}

	@Post("/estabelecimentos")
	public void adiciona(final Estabelecimento estabelecimento) {
		validator.addIf(Strings.isNullOrEmpty(estabelecimento.getNome()), new I18nMessage("estabelecimento.nome","nome.nulo"));
		validator.addIf(Strings.isNullOrEmpty(estabelecimento.getEndereco()), new I18nMessage("estabelecimento.endereco","endereco.nulo"));
		validator.onErrorRedirectTo(this).lista();

		diretorio.adiciona(estabelecimento);
		result.redirectTo(this).lista();
	}
}
