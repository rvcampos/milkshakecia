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

import br.comvizzatech.teste.data.rep.MesaRepository;
import br.comvizzatech.teste.model.mesa.Mesa;

@RequestScoped
public class MesasAcessor implements Serializable{

	@Inject
	private MesaRepository mesaRepository;

	private List<Mesa> mesas;

	private Mesa selectedMesa;

	@Produces
	@Named
	public Mesa getSelectedMesa() {
		return selectedMesa;
	}

	public void setSelectedMesa(Mesa selectedMesa) {
		this.selectedMesa = selectedMesa;
	}

	@Produces
	@Named
	public List<Mesa> getMesas() {
		return mesas;
	}

	public void onMemberListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Mesa mesa) {
		buildMesaList();
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	@PostConstruct
	public void buildMesaList() {
		mesas = mesaRepository.findAllOrderedById();
	}
}
