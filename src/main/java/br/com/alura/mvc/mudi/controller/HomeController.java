package br.com.alura.mvc.mudi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.alura.mvc.mudi.model.Pedido;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String home(Model model) {
		Pedido pedido = new Pedido();
		pedido.setNome("Notebook Lenovo Ultrafino IdeaPad 3");
		pedido.setUrlImagem("https://m.media-amazon.com/images/I/61i8c2d4tML._AC_SL1000_.jpg");
		pedido.setUrlDoProduto("https://www.amazon.com.br/Notebook-Lenovo-Ultrafino-IdeaPad-82MFS00100/dp/B09LVLN4L4?ref_=Oct_d_obs_d_16364755011&pd_rd_w=IOEzQ&content-id=amzn1.sym.574d7dde-c8d1-436d-a7fc-f2654a8c3190&pf_rd_p=574d7dde-c8d1-436d-a7fc-f2654a8c3190&pf_rd_r=0Q1P3JCYG95VM1RQGR11&pd_rd_wg=Jj3or&pd_rd_r=283f3e0f-f9eb-4129-8267-4d7c32eb14a5&pd_rd_i=B09LVLN4L4&th=1");
		pedido.setDescricao("uma descrição ai qualquer");
		
		List<Pedido> pedidos = Arrays.asList(pedido);
		model.addAttribute("pedidos", pedidos);
		
		return "home";		
	}	
}
