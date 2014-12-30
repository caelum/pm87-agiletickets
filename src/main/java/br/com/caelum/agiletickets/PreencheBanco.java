package br.com.caelum.agiletickets;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.joda.time.DateTime;

import br.com.caelum.agiletickets.models.Espetaculo;
import br.com.caelum.agiletickets.models.Estabelecimento;
import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class PreencheBanco {

	// ALUNO: Não apague essa classe
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
		
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		manager.createQuery("delete from Sessao").executeUpdate();
		manager.createQuery("delete from Espetaculo").executeUpdate();
		manager.createQuery("delete from Estabelecimento").executeUpdate();
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setNome("Casa de shows");
		estabelecimento.setEndereco("Rua dos Silveiras, 12345");

		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setEstabelecimento(estabelecimento);
		espetaculo.setNome("Depeche Mode");
		espetaculo.setTipo(TipoDeEspetaculo.SHOW);

		manager.persist(estabelecimento);
		manager.persist(espetaculo);

		for (int i = 0; i < 10; i++) {
			Sessao sessao = new Sessao();
			sessao.setEspetaculo(espetaculo);
			sessao.setInicio(new DateTime().plusDays(7+i));
			sessao.setDuracaoEmMinutos(60 * 3);
			sessao.setTotalIngressos(100);
			sessao.setIngressosReservados(i < 5 ? 100 - i : 0);
			sessao.setPreco(new BigDecimal("50"));
			manager.persist(sessao);
		}

		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
	
}
