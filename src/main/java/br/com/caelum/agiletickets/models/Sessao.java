package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

@Entity
public class Sessao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Espetaculo espetaculo;

	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime inicio;

	private Integer duracaoEmMinutos;

	private Integer totalIngressos = 0;

	private Integer ingressosReservados = 0;

	private BigDecimal preco;

	public Long getId() {
		return id;
	}

	public void setEspetaculo(Espetaculo espetaculo) {
		this.espetaculo = espetaculo;
	}

	public DateTime getInicio() {
		return inicio;
	}

	public void setInicio(DateTime inicio) {
		this.inicio = inicio;
	}

	public Espetaculo getEspetaculo() {
		return espetaculo;
	}

	public Integer getDuracaoEmMinutos() {
		return duracaoEmMinutos;
	}

	public void setDuracaoEmMinutos(Integer duracaoEmMinutos) {
		this.duracaoEmMinutos = duracaoEmMinutos;
	}

	public String getDia() {
		return inicio.toString(DateTimeFormat.shortDate().withLocale(new Locale("pt", "BR")));
	}

	public String getHora() {
		return inicio.toString(DateTimeFormat.shortTime().withLocale(new Locale("pt", "BR")));
	}

	public Integer getTotalIngressos() {
		return totalIngressos;
	}

	public void setTotalIngressos(Integer totalIngressos) {
		this.totalIngressos = totalIngressos;
	}

	public Integer getIngressosReservados() {
		return ingressosReservados;
	}

	public void setIngressosReservados(Integer ingressosReservados) {
		this.ingressosReservados = ingressosReservados;
	}

	public Integer getIngressosDisponiveis() {
		// faz a conta de total de ingressos menos ingressos reservados
		return totalIngressos - ingressosReservados;
	}
	

	public void reserva(Integer numeroDeIngressos) {
		// soma quantidade na variavel ingressos reservados
		this.ingressosReservados += numeroDeIngressos;
	}

	public boolean podeReservar(Integer numeroDeIngressos) {
		int sobraram = getIngressosDisponiveis() - numeroDeIngressos;
        boolean naoTemEspaco = sobraram <= 0;

        return !naoTemEspaco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getPreco() {
		return preco;
	}
	
}
