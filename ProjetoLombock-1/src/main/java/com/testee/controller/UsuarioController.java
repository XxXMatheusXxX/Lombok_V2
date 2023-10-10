package com.testee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testee.entities.Usuario;
import com.testee.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioService UsuarioService;

	@Autowired
	public UsuarioController(UsuarioService UsuarioService) {
		this.UsuarioService = UsuarioService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id) {
		Usuario Usuario = UsuarioService.getUsuarioById(id);
		if (Usuario != null) {
			return ResponseEntity.ok(Usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Usuario>> buscaTodasLigacoesControl() {
		List<Usuario> Usuario = UsuarioService.getAllUsuarios();
		return ResponseEntity.ok(Usuario);
	}

	@PostMapping("/")
	public ResponseEntity<Usuario> saveUsuarioControl(@RequestBody @Valid Usuario Usuario) {
		Usuario saveUsuario = UsuarioService.saveUsuario(Usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUsuario);
	}

	@PostMapping("/{id}")
	public ResponseEntity<Usuario> alteraUsuarioControl(@PathVariable Long id, @RequestBody @Valid Usuario Usuario) {
		Usuario alteraUsuario = UsuarioService.changeUsuario(id, Usuario);

		if (alteraUsuario != null) {
			return ResponseEntity.ok(Usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUsuarioControl(@PathVariable Long id) {
		boolean delete = UsuarioService.deleteUsuario(id);
		if (delete) {
			return ResponseEntity.ok().body("O produto foi excluido com o sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}