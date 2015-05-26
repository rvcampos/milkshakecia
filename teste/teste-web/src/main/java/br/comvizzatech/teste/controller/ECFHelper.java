package br.comvizzatech.teste.controller;

import jACBrFramework.ACBrEventListener;
import jACBrFramework.ACBrException;
import jACBrFramework.serial.ecf.ACBrECF;
import jACBrFramework.serial.ecf.AbreCupomEventObject;
import jACBrFramework.serial.ecf.FormaPagamento;
import jACBrFramework.serial.ecf.VendeItemEventObject;

import java.math.BigDecimal;
import java.util.EventObject;
import java.util.List;

import br.comvizzatech.teste.model.mesa.Mesa;
import br.comvizzatech.teste.model.ordem.ItemOrdemDet;
import br.comvizzatech.teste.model.produtos.Produto;

public final class ECFHelper {

	public static synchronized void emiteCupom(Mesa mesa,
			List<ItemOrdemDet> produtos, int tpPagto, Double valorPago) {
		ACBrECF ecf = null;
		try {
			ecf = new ACBrECF();
			ecf.addOnAntesAbreCupom(new ACBrEventListener<AbreCupomEventObject>() {

				public void notification(AbreCupomEventObject e) {
					System.out.println(">> Evento OnAntesAbreCupom <<");
				}
			});

			ecf.addOnDepoisVendeItem(new ACBrEventListener<VendeItemEventObject>() {

				public void notification(VendeItemEventObject e) {
					System.out.println(">> Evento OnDepoisVendeItem <<");
					System.out.println(e.getCodigo() + " " + e.getDescricao());
				}
			});

			ecf.addOnMsgPoucoPapel(new ACBrEventListener<EventObject>() {

				public void notification(EventObject e) {
					System.out.println(">> Evento OnMsgPoucoPapel <<");
				}
			});
			ecf.setModelo(2);
			ecf.getDevice().setPorta("COM4");
			ecf.ativar();
			ecf.corrigeEstadoErro(true);
			ecf.carregaAliquotas();
			efetuaVenda(ecf, produtos, tpPagto, valorPago);
		} catch (ACBrException e) {
			e.printStackTrace();
		} finally {
			if (ecf != null) {
				try {
					ecf.desativar();
				} catch (ACBrException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static synchronized void efetuaVenda(ACBrECF ecf,
			List<ItemOrdemDet> produtos, int tpPagto, Double valorPago)
			throws ACBrException {
		try {
			ecf.abreCupom();
			for (ItemOrdemDet det : produtos) {
				Produto produto = det.getProduto();
				String descricao = det.getNomeComTamanho();
				String codigo = "" + produto.getIdProduto();
				Double qtd = Double.valueOf(det.getQuantidade());
				Double valorUnitario = det.calculaPrecoProdutoUnit(false)
						.doubleValue();
				BigDecimal descontoAcrescimo = det.getPrecoAdicionais();
				ecf.vendeItem(codigo, descricao, "18", qtd, valorUnitario, 0,
						"UN", "", "", 0);
				if (descontoAcrescimo != null
						&& descontoAcrescimo.compareTo(BigDecimal.ZERO) > 0) {
					ecf.vendeItem(codigo + "A",
							"Adicional " + det.getNomeComTamanho(), "I", det
									.getQtdAdicionaisCobrados(), produto
									.getPrecoAdicional().doubleValue(), 0,
							"UN", "", "", 0);
				}
				valorPago += det.calculaPrecoProduto().doubleValue();
			}
			ecf.subtotalizaCupom(0, "Total");
			if (ecf.getFormasPagamento() == null) {
				ecf.carregaFormasPagamento();
			}
			FormaPagamento formaPagto = ecf.getFormasPagamento()[tpPagto];
			ecf.efetuaPagamento(formaPagto.getIndice(), valorPago, "", false);
			ecf.fechaCupom("Obrigado e Volte Sempre!!");
		} catch (Exception e) {
			ecf.cancelaCupom();
			throw e;
		} finally {
			try {
				ecf.cancelaCupom();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}
}
