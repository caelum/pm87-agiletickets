package br.com.caelum.agiletickets.domain;

import org.joda.time.DateTime;

public class RelogioDoSistema implements Relogio {

	@Override
	public DateTime agora() {
		return new DateTime();
	}

}
