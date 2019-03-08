package com.notas.core.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notas.core.entity.Usuario;

@Repository("gestorUsuario")
public interface UsuarioRepositorio extends JpaRepository<Usuario, Serializable>{
	public abstract Usuario findByUsuario(String usuario);
}
