package br.com.estudos.models;

import java.math.BigDecimal;

public class DadosPagamentos {

	private BigDecimal value;

	public DadosPagamentos(BigDecimal total) {
		
		this.setValue(total);
	}

	public DadosPagamentos(){
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
}
