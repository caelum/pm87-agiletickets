package br.com.caelum.agiletickets.models;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

@Entity
public class Espetaculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String descricao;

	@Enumerated(EnumType.STRING)
	private TipoDeEspetaculo tipo;

	@ManyToOne
	private Estabelecimento estabelecimento;

	@OneToMany(mappedBy = "espetaculo")
	private List<Sessao> sessoes = newArrayList();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoDeEspetaculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeEspetaculo tipo) {
		this.tipo = tipo;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public List<Sessao> criaSessoes(LocalDate inicio, LocalDate fim,
			LocalTime horario, Periodicidade periodicidade) {
		// ALUNO: Não apague esse metodo. Esse sim será usado no futuro! ;)
		return null;
	}

	public boolean Vagas(int qtd, int min) {
		// ALUNO: Não apague esse metodo. Esse sim será usado no futuro! ;)
		int totDisp = 0;

		for (Sessao s : sessoes) {
			if (s.getIngressosDisponiveis() < min)
				return false;
			totDisp += s.getIngressosDisponiveis();
		}

		if (totDisp >= qtd)
			return true;
		else
			return false;
	}

	public boolean Vagas(int qtd) {
		// ALUNO: Não apague esse metodo. Esse sim será usado no futuro! ;)
		int totDisp = 0;

		for (Sessao s : sessoes) {
			totDisp += s.getIngressosDisponiveis();
		}

		if (totDisp >= qtd)
			return true;
		else
			return false;
	}

}
