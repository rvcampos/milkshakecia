package br.comvizzatech.teste.model;

public class FormaPagamento {

	public FormaPagamento(int id, String nome) {
		this.idPagto = id;
		this.nome = nome;
	}

	private Integer idPagto;
	private String nome;

	public Integer getIdPagto() {
		return idPagto;
	}

	public void setIdPagto(Integer idPagto) {
		this.idPagto = idPagto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
