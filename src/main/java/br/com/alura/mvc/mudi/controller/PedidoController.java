package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.service.PedidosService;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidosService pedidosService;
	
	
	@GetMapping("/formulario")
	public String novoPedido() {		
		return "pedido/formulario";
	}
	
	@PostMapping("novo")
	public String cadastrar(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
		
		if (result.hasErrors()) {
			return "pedido/formulario";
		}
		
		String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = pedidosService.buscaUsuarioPorUsername(nomeUsuario);
		
		Pedido pedido = requisicao.toPedido();
		pedido.setUser(user);
		pedidosService.cadastrar(pedido);
		
		return "redirect:/usuario/pedidos";
	}
	
	@ModelAttribute
	RequisicaoNovoPedido requisicao() {
		return new RequisicaoNovoPedido();
	}
}
