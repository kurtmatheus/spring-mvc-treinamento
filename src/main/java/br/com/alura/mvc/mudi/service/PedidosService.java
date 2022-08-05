package br.com.alura.mvc.mudi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Service
public class PedidosService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	
	public void cadastrar(Pedido pedido) {
		pedidoRepository.save(pedido);
	}
	
	public List<Pedido> listaPedidos(){
		return (List<Pedido>) this.pedidoRepository.findAll();
	}
	
	public List<Pedido> listaPedidosPorUsuario(String nomeUsuario){
		return (List<Pedido>) this.pedidoRepository.findByUsuario(nomeUsuario);
	}

	public List<Pedido> listaPorStatus(StatusPedido status, Pageable paginacao) {
		/*TODO PageRequest 
		 * 		O PageRequest ficaria apenas nesse service
		 * 		que receberia um Sort deixando para o controller 
		 * 		definir tipo de Sort seria utilizado	
		 */
		return (List<Pedido>) this.pedidoRepository.findByStatus(status, paginacao);
	}
	
	public User buscaUsuarioPorUsername(String nomeUsuario) {
		return this.userRepository.findByUsername(nomeUsuario);
	}

	public List<Pedido> listaPorStatusEUser(StatusPedido status, String nomeUsuario) {
		return this.pedidoRepository.findByStatusAndUsername(status, nomeUsuario);
	}

	public Optional<Pedido> buscarPedidoPorID(Long pedidoId) {
		return this.pedidoRepository.findById(pedidoId);
	}
	
}
