package br.com.alura.mvc.mudi.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.service.PedidosService;

@RestController
@RequestMapping("/api/oferta")
public class OfertaRest {
	
	@Autowired
	private PedidosService pedidoService;
	
	@PostMapping
	public Oferta criarNovaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao) {
		Optional<Pedido> pedidoBuscado = this.pedidoService.buscarPedidoPorID(requisicao.getPedidoId());
		if(!pedidoBuscado.isPresent()) {
			return null;
		}
		
		Pedido pedido = pedidoBuscado.get();
		
		Oferta oferta = requisicao.toOferta();
		oferta.setPedido(pedido);
		pedido.adicionaOferta(oferta);
		this.pedidoService.cadastrar(pedido);
		
		return oferta;
		
	}
}
