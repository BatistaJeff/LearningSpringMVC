package br.com.estudos.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Produto {
	
	@Column(name="id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="descricao")
	@Lob
	private String descricao;
	
	@Column(name="paginas")
	private int paginas;
	
	@Column(name="dataLancamento")
	@DateTimeFormat
	private Calendar dataLancamento;
	
	@Column(name="precos")
	@ElementCollection
	private List<Preco> precos;
	
	@Column(name="sumarioPath")
	private String sumarioPath;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public Calendar getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public List<Preco> getPrecos() {
		if(this.precos == null) {
			return this.precos = new ArrayList<Preco>();
		}
		return precos;
	}
	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}
	public String getSumarioPath() {
		return sumarioPath;
	}
	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}

	public BigDecimal precoPara(TipoPreco tipoPreco) {
		System.out.println(precos.stream().filter(preco -> preco.getTipo().equals(tipoPreco)).findFirst().get().getValor());
		return precos.stream().filter(preco -> preco.getTipo().equals(tipoPreco)).findFirst().get().getValor();
	}
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas
				+ ", dataLancamento=" + dataLancamento + ", precos=" + precos + ", sumarioPath=" + sumarioPath + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
