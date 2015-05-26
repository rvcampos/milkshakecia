package br.comvizzatech.teste.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import br.comvizzatech.teste.data.rep.CategoriaRepository;
import br.comvizzatech.teste.model.Categoria;
import br.comvizzatech.teste.model.ProdutoConfig;
import br.comvizzatech.teste.model.mesa.Mesa;
import br.comvizzatech.teste.model.ordem.ItemOrdem;
import br.comvizzatech.teste.model.ordem.ItemOrdemDet;
import br.comvizzatech.teste.model.ordem.ItemOrdemDetAdic;
import br.comvizzatech.teste.model.ordem.Ordem;
import br.comvizzatech.teste.model.produtos.Produto;
import br.comvizzatech.teste.model.produtos.ProdutoProdutoAdicional;
import br.comvizzatech.teste.model.produtos.ProdutoSabor;
import br.comvizzatech.teste.model.produtos.ProdutoTamanho;
import br.comvizzatech.teste.service.MesaService;
import br.comvizzatech.teste.service.OrdemService;

@ManagedBean(name = "pedidosView")
// @Named("pedidosView")
@ViewScoped
public class PedidosController implements Serializable {

	@Inject
	private FacesContext facesContext;

	@Inject
	private CategoriaRepository service;

	@Inject
	private OrdemService ordemService;

	@Inject
	private MesaService mesaService;

	private String pageTitle = "BALCÃO";

	private String idMesa;

	private Mesa selectedMesa;

	private List<Categoria> categorias;

	private ProdutoConfig produtoConfig;

	private List<Produto> produtos;

	private List<ProdutoConfig> produtosSelectionados;

	private Produto produtoSelecionado;

	private Categoria categoriaSelecionada;

	private List<ProdutoTamanho> tamanhosDisponiveis;

	private List<ProdutoSabor> saboresDisponiveis;

	private List<ProdutoProdutoAdicional> adicionaisDisponiveis;

	private int quantidade;

	private String cpf;

	@PostConstruct
	public void init() {
		categorias = service.findAllOrderedById();
		if (facesContext.getExternalContext().getRequestParameterMap()
				.get("idMesa") != null) {
			this.idMesa = facesContext.getExternalContext()
					.getRequestParameterMap().get("idMesa");
			this.pageTitle = "MESA " + idMesa;
		}
		produtosSelectionados = new ArrayList<ProdutoConfig>();
		produtoConfig = new ProdutoConfig();
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
		} else {
			setAdicionaisDisponiveis(null);
			setTamanhosDisponiveis(null);
			setSaboresDisponiveis(null);
		}
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
		setTamanhosDisponiveis(null);
		setSaboresDisponiveis(null);
		setProdutoSelecionado(null);
	}

	public List<ProdutoTamanho> getTamanhosDisponiveis() {
		return tamanhosDisponiveis;
	}

	public void setTamanhosDisponiveis(List<ProdutoTamanho> tamanhosDisponiveis) {
		this.tamanhosDisponiveis = tamanhosDisponiveis;
		RequestContext.getCurrentInstance().update("tamanho");
		RequestContext.getCurrentInstance().update("tamanhoGrid");
	}

	public List<ProdutoSabor> getSaboresDisponiveis() {
		return saboresDisponiveis;
	}

	public void setSaboresDisponiveis(List<ProdutoSabor> saboresDisponiveis) {
		this.saboresDisponiveis = saboresDisponiveis;
		RequestContext.getCurrentInstance().update("sabores");
	}

	public List<ProdutoProdutoAdicional> getAdicionaisDisponiveis() {
		return adicionaisDisponiveis;
	}

	public void setAdicionaisDisponiveis(
			List<ProdutoProdutoAdicional> adicionaisDisponiveis) {
		this.adicionaisDisponiveis = adicionaisDisponiveis;
		RequestContext.getCurrentInstance().update("adicionais");
	}

	public void adicionaPedido() {
		if (getProdutoConfig() != null) {
			if (!validate()) {
				return;
			}
			if (this.quantidade > 1) {
				produtoConfig.setQtd(quantidade);
				setQuantidade(1);
			}
			produtoConfig.setProduto(produtoSelecionado);
			this.produtosSelectionados.add(getProdutoConfig());
			produtoConfig = new ProdutoConfig();
			produtoConfig.setId(this.produtosSelectionados.size());
			produtoSelecionado = null;
			setAdicionaisDisponiveis(null);
			setTamanhosDisponiveis(null);
			setSaboresDisponiveis(null);
			RequestContext.getCurrentInstance().update("parentPanel");
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Produto adicionado com sucesso",
					"Produto adicionado com sucesso"));
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

	public void removeProduto(ProdutoConfig produto) {
		if (produto != null) {
			produtosSelectionados.remove(produto);
		}
	}

	public Mesa getSelectedMesa() {
		return selectedMesa;
	}

	public void setSelectedMesa(Mesa selectedMesa) {
		this.selectedMesa = selectedMesa;
	}

	public String getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(String idMesa) {
		this.idMesa = idMesa;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String totalPedidos() {
		BigDecimal precoTotal = BigDecimal.ZERO;
		if (produtosSelectionados != null && !produtosSelectionados.isEmpty()) {
			for (ProdutoConfig produtoProdutoAdicional : produtosSelectionados) {
				precoTotal = precoTotal.add(produtoProdutoAdicional
						.getPrecoTtl());
			}
		}

		return "R$ " + precoTotal.toString();
	}

	public void confirmaOrdem() {
		Ordem ordem = new Ordem();
		if (produtosSelectionados != null && !produtosSelectionados.isEmpty()) {
			ItemOrdem itmOrdem = new ItemOrdem();
			itmOrdem.setOrdem(ordem);
			for (ProdutoConfig config : produtosSelectionados) {
				ItemOrdemDet det = new ItemOrdemDet();
				det.setProduto(config.getProduto());
				det.setIdSabor(config.getSabor());
				det.setIdTamanho(config.getTamanho());
				det.setQuantidade(config.getQtd());
				det.setDetalhe(config.getDetalhes());
				if (config.getAdicionais() != null
						&& !config.getAdicionais().isEmpty()) {
					for (String adic : config.getAdicionais()) {
						ItemOrdemDetAdic itemAdic = new ItemOrdemDetAdic();
						itemAdic.setIdProdutoAdic(Integer.valueOf(adic));
						det.addItemDetAdic(itemAdic);
					}
				}
				itmOrdem.addItemOrdemDet(det);
			}
			ordem.addItemOrdem(itmOrdem);
			ordem.setStatus(0);
			ordem.setDataOrdem(new Timestamp(System.currentTimeMillis()));
			ordem.setDocumentoNota(cpf);
			setCpf(null);
			if (this.getIdMesa() != null) {
				ordem.setId_mesa(Integer.valueOf(this.getIdMesa()));
			}
			boolean criaOrdem = ordemService.criaOrdem(ordem);
			if (criaOrdem) {
				facesContext.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Pedido Enviado com sucesso",
						"Pedido Enviado com sucesso"));
			} else {
				facesContext.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Falha ao realizar pedido, atualize a página",
						"Falha ao realizar pedido, atualize a página"));
			}
			produtosSelectionados.clear();
			produtos.clear();
			RequestContext.getCurrentInstance().update("curr");
			RequestContext.getCurrentInstance().update("currentItems");
		}
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}