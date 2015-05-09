package br.comvizzatech.teste.data;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.comvizzatech.teste.data.rep.CategoriaRepository;
import br.comvizzatech.teste.model.Categoria;
import br.comvizzatech.teste.model.mesa.Mesa;

@RequestScoped
public class CategoriaAcessor implements Serializable{

	@Inject
	private CategoriaRepository categoriaRepository;

	private List<Categoria> categorias;

	@Produces
	@Named
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void onMemberListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Mesa mesa) {
		buildCategoriaList();
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	@PostConstruct
	public void buildCategoriaList() {
		categorias = categoriaRepository.findAllOrderedById();
	}
}
