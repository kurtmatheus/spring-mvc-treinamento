package br.com.alura.mvc.mudi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alura.mvc.mudi.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
	
	User findByUsername(String username);
}
