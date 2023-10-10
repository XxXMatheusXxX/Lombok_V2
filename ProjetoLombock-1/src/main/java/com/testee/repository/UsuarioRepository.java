package com.testee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testee.entities.Usuario;

public interface  UsuarioRepository extends JpaRepository<Usuario,Long>{

}
