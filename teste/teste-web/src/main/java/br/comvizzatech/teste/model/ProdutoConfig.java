package br.comvizzatech.teste.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import br.comvizzatech.teste.model.produtos.Produto;
import br.comvizzatech.teste.model.produtos.ProdutoProdutoAdicional;
import br.comvizzatech.teste.model.produtos.ProdutoSabor;
import br.comvizzatech.teste.model.produtos.ProdutoTamanho;

public class ProdutoConfig {

	private int id;
	private Produto produto;
	private Integer tamanho;
	private Integer sabor;
	private int idCombo;
	private int idxCombo;
	private List<String> adicionais;
	private int qtd = 1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public Integer getSabor() {
		return sabor;
	}

	public void setSabor(Integer sabor) {
		this.sabor = sabor;
	}

	public int getIdCombo() {
		return idCombo;
	}

	public void setIdCombo(int idCombo) {
		this.idCombo = idCombo;
	}

	public int getIdxCombo() {
		return idxCombo;
	}

	public void setIdxCombo(int idxCombo) {
		this.idxCombo = idxCombo;
	}

	public List<String> getAdicionais() {
		return adicionais;
	}

	public void setAdicionais(List<String> adicionais) {
		this.adicionais = adicionais;
	}

	public String printProdutoInfo() {
		if (this.produto == null) {
			return null;
		}

		StringBuilder desc = new StringBuilder(produto.getNome());

		if (tamanho != null) {
			ProdutoTamanho prdTamanho = produto
					.getTamanhoInfoByTamanhoId(tamanho);
			desc.append(" ").append(prdTamanho.getTamanho().getNome());
		}

		if (sabor != null) {
			ProdutoSabor prdSabor = produto.getSaborInfoBySaborId(sabor);
			desc.append(" ").append(prdSabor.getSabor().getNome());
		}
		
		if (adicionais != null) {
			desc.append(" c/ ");
			boolean first = true;
			for(String i : adicionais)
			{
				ProdutoProdutoAdicional prdSabor = produto.getProdAdInfoByProdAdicionalId(Integer.valueOf(i));
				if(!first)
				{
					desc.append(",");
				}
				desc.append("\n").append(prdSabor.getProdutoAdicional().getNome());
				first = false;
			}
		}

		return desc.toString();
	}
	
	public BigDecimal getPrecoTtl() {
		if (produto == null) {
			return BigDecimal.ZERO;
		}
		BigDecimal dec = BigDecimal.ZERO;
		if (produto.getPrecoPadrao() != null) {
			dec = produto.getPrecoPadrao();
		}

		if (tamanho != null) {
			ProdutoTamanho prdTamanho = produto
					.getTamanhoInfoByTamanhoId(tamanho);
			if (prdTamanho.getPreco() != null) {
				dec = prdTamanho.getPreco();
			}
		}

		if (sabor != null) {
			ProdutoSabor prdSabor = produto.getSaborInfoBySaborId(sabor);
			if (prdSabor.getPrecoAdicional() != null) {
				dec = dec.add(prdSabor.getPrecoAdicional());
			}
		}
		if(adicionais != null && !adicionais.isEmpty())
		{
			Short qtdAdIncl = produto.getQtdAdicionalIncluso();
			if(qtdAdIncl < adicionais.size())
			{
				int qtd = adicionais.size() - qtdAdIncl;
				dec = dec.add(produto.getPrecoAdicional().multiply(BigDecimal.valueOf(qtd)));
			}
		}
		dec = dec.multiply(BigDecimal.valueOf(qtd));

		return dec;
	}

	public String getPreco() {
		if (produto == null) {
			return "NULO";
		}
		StringBuilder preco = new StringBuilder("R$ ");

		BigDecimal dec = getPrecoTtl().setScale(2,BigDecimal.ROUND_HALF_EVEN);

		preco.append(dec.toString());

		return preco.toString();
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public void addQty() {
		qtd++;
	}

	public void remQty() {
		if (qtd > 1) {
			qtd--;
		}
	}

	public String getDetalhes() {
		return null;
	}

}
