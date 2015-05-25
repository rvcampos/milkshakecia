package br.comvizzatech.teste.controller;

import java.util.EventObject;

import jACBrFramework.ACBrEventListener;
import jACBrFramework.ACBrException;
import jACBrFramework.serial.ecf.ACBrECF;
import jACBrFramework.serial.ecf.AbreCupomEventObject;
import jACBrFramework.serial.ecf.FormaPagamento;
import jACBrFramework.serial.ecf.VendeItemEventObject;
import br.comvizzatech.teste.model.mesa.Mesa;
import br.comvizzatech.teste.model.ordem.ItemOrdem;
import br.comvizzatech.teste.model.ordem.ItemOrdemDet;
import br.comvizzatech.teste.model.ordem.Ordem;
import br.comvizzatech.teste.model.produtos.Produto;

public class ECFHelper {

	public synchronized void emiteCupom(Mesa mesa) {
		try {
			ACBrECF ecf = new ACBrECF();
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
		} catch (ACBrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private synchronized void efetuaVenda(ACBrECF ecf,Mesa mesa, double valorRecebido) throws ACBrException
	{
		ecf.abreCupom();
		
		for(Ordem ordem : mesa.getPedidosMesa())
		{
			for(ItemOrdem ord : ordem.getItemOrdems())
			{
				for(ItemOrdemDet det : ord.getItemOrdemDet())
				{
					Produto produto = det.getProduto();
					String nome = produto.getNome();
					if(produto.getCategoria().getNome().toLowerCase().equals("milkshake"))
					{
						nome = "Milkshake " + nome;
					}
					ecf.vendeItem(""+produto.getIdProduto(), nome, "T18", det.getQuantidade(), det.calculaPrecoProduto().doubleValue(), 0, "UN", "%", "", 0);
					valorRecebido += det.calculaPrecoProduto().doubleValue();
				}
			}
		}
		ecf.subtotalizaCupom(0, "Total");
		FormaPagamento formaPagto = ecf.getFormasPagamento()[3];
		ecf.efetuaPagamento(formaPagto.getIndice(), 50, "MENSAGEM FORMA DE PAGTO", false);
		
		ecf.fechaCupom("Obrigado e Volte Sempre!!");
	}
}
