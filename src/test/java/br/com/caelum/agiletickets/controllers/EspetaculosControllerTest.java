package br.com.caelum.agiletickets.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.agiletickets.domain.Agenda;
import br.com.caelum.agiletickets.domain.DiretorioDeEstabelecimentos;
import br.com.caelum.agiletickets.models.Espetaculo;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;

public class EspetaculosControllerTest {

	private @Mock Agenda agenda;
	private @Mock DiretorioDeEstabelecimentos estabelecimentos;
	private @Spy Validator validator = new MockValidator();
	private @Spy Result result = new MockResult();
	
	private EspetaculosController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new EspetaculosController(result, validator, agenda, estabelecimentos);
	}

	@Test(expected=ValidationException.class)
	public void naoDeveCadastrarEspetaculosSemNome() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setDescricao("uma descricao");

		controller.adiciona(espetaculo);

		verifyZeroInteractions(agenda);
	}

	@Test(expected=ValidationException.class)
	public void naoDeveCadastrarEspetaculosSemDescricao() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setNome("um nome");

		controller.adiciona(espetaculo);

		verifyZeroInteractions(agenda);
	}

	@Test
	public void deveCadastrarEspetaculosComNomeEDescricao() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setNome("um nome");
		espetaculo.setDescricao("uma descricao");

		controller.adiciona(espetaculo);

		verify(agenda).cadastra(espetaculo);
	}

}
