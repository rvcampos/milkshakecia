package br.comvizzatech.teste.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.DragDropEvent;

import br.comvizzatech.teste.data.rep.CategoriaRepository;
import br.comvizzatech.teste.model.Categoria;
import br.comvizzatech.teste.model.ProdutoConfig;
import br.comvizzatech.teste.model.produtos.Produto;
import br.comvizzatech.teste.model.produtos.ProdutoProdutoAdicional;
import br.comvizzatech.teste.model.produtos.ProdutoSabor;
import br.comvizzatech.teste.model.produtos.ProdutoTamanho;

@ManagedBean(name = "pedidosView")
@ViewScoped
public class PedidosController implements Serializable {

	@Inject
	private FacesContext facesContext;

	@Inject
	private CategoriaRepository service;

	private List<Categoria> categorias;

	private ProdutoConfig produtoConfig;

	private List<Produto> produtos;

	private List<ProdutoConfig> produtosSelectionados;

	private Produto produtoSelecionado;

	private Categoria categoriaSelecionada;

	private List<ProdutoTamanho> tamanhosDisponiveis;

	private List<ProdutoSabor> saboresDisponiveis;

	private List<ProdutoProdutoAdicional> adicionaisDisponiveis;

	@PostConstruct
	public void init() {
		categorias = service.findAllOrderedById();
		produtosSelectionados = new ArrayList<ProdutoConfig>();
		produtoConfig = new ProdutoConfig();
	}

	public void onProdutoDrop(DragDropEvent ddEvent) {
		Produto produto = ((Produto) ddEvent.getData());

		ProdutoConfig cfg = new ProdutoConfig();
		cfg.setProduto(produto);

		produtosSelectionados.add(cfg);
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public List<ProdutoConfig> getProdutosSelecionados() {
		return produtosSelectionados;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
		if (produtoSelecionado != null) {
			setAdicionaisDisponiveis(produtoSelecionado.getProdutosAdicionais());
			setTamanhosDisponiveis(produtoSelecionado.getTamanhos());
			setSaboresDisponiveis(produtoSelecionado.getSabores());
			this.produtoConfig = new ProdutoConfig();
		}
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public ProdutoConfig getProdutoConfig() {
		return produtoConfig;
	}

	public void setProdutoConfig(ProdutoConfig produtoConfig) {
		this.produtoConfig = produtoConfig;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ProdutoTamanho> getTamanhosDisponiveis() {
		return tamanhosDisponiveis;
	}

	public void setTamanhosDisponiveis(List<ProdutoTamanho> tamanhosDisponiveis) {
		this.tamanhosDisponiveis = tamanhosDisponiveis;
	}

	public List<ProdutoSabor> getSaboresDisponiveis() {
		return saboresDisponiveis;
	}

	public void setSaboresDisponiveis(List<ProdutoSabor> saboresDisponiveis) {
		this.saboresDisponiveis = saboresDisponiveis;
	}

	public List<ProdutoProdutoAdicional> getAdicionaisDisponiveis() {
		return adicionaisDisponiveis;
	}

	public void setAdicionaisDisponiveis(
			List<ProdutoProdutoAdicional> adicionaisDisponiveis) {
		this.adicionaisDisponiveis = adicionaisDisponiveis;
	}

	public void adicionaPedido() {
		if (getProdutoConfig() != null) {
			if (!validate()) {
				return;
			}
			produtoConfig.setProduto(produtoSelecionado);
			this.produtosSelectionados.add(getProdutoConfig());
			produtoConfig = new ProdutoConfig();
			produtoConfig.setId(this.produtosSelectionados.size());
			produtoSelecionado = null;
			setAdicionaisDisponiveis(null);
			setTamanhosDisponiveis(null);
			setSaboresDisponiveis(null);
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Sucesso",
					"Mesa aberta com sucesso"));
		}
	}

	private boolean validate() {
		if (produtoSelecionado == null) {
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Produto",
					"Produto Não Selecionado"));
			return false;
		}

		if ((produtoConfig.getTamanho() == null && (produtoSelecionado
				.getTamanhos() != null && !produtoSelecionado.getTamanhos()
				.isEmpty()))) {
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Informe o Tamanho",
					"Tamanho não selecionado"));
			return false;
		}

		if ((produtoConfig.getSabor() == null && (produtoSelecionado
				.getSabores() != null && !produtoSelecionado.getSabores()
				.isEmpty()))) {
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Informe o Sabor",
					"Sabor não Selecionado"));
			return false;
		}

		return true;
	}
	
	public void removeProduto(ProdutoConfig produto)
	{
		if(produto != null)
		{
			produtosSelectionados.remove(produto);
		}
	}
}