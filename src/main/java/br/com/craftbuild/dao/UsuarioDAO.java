package br.com.craftbuild.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.craftbuild.beans.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer>{
	public Usuario findByEmailAndSenha(String email, String senha);
}
