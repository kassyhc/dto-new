package com.projetodto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetodto.dto.UsuarioDTO;
import com.projetodto.entities.Usuario;
import com.projetodto.services.UsuarioService;

import jakarta.validation.Valid;


@RestController //indica que a classe é um controlador REST. //
@RequestMapping("/Usuario") // mapeia todas as solicitações para o caminho "/Usuario". //
public class UsuarioController { // Declaração da classe do controlador. //

	private final UsuarioService UsuarioService; // Declaração de uma variável final. //

	@Autowired // injeção de dependência do serviço na classe do controlador. //
	public UsuarioController(UsuarioService UsuarioService) { // Este é o construtor da classe do controlador. //
		this.UsuarioService = UsuarioService; // Atribui o serviço de usuários à variável //
	}

	@GetMapping("/{id}") // Mapeia as solicitações GET para este método //
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id) { // Este método retorna um usuário específico pelo seu ID. //
		Usuario Usuario = UsuarioService.getUsuarioDTOById(id); // Busca o usuário pelo ID no serviço. //
		if (Usuario != null) { // Verifica se o usuário existe. //
			return ResponseEntity.ok(Usuario); // Se existir, retorna o usuário. //
		} else {
			return ResponseEntity.notFound().build(); // Se não existir, retorna um status 404 (Not Found). //
		}
	}

	@GetMapping // Mapeia as solicitações GET para este método. //
	public ResponseEntity<List<Usuario>> buscaTodasLigacoesControl() { // Este método retorna todos os usuários. //
		List<Usuario> Usuario = UsuarioService.getAllUsuarioDTOs(); // Busca todos os usuários no serviço. //
		return ResponseEntity.ok(Usuario); // Retorna todos os usuários. //
	}

	@PostMapping // Mapeia as solicitações POST para este método. //
	public ResponseEntity<UsuarioDTO> saveUsuarioControl(@RequestBody @Valid UsuarioDTO UsuarioDTO) { // Este método salva um novo usuário. //
		UsuarioDTO saveUsuario = UsuarioService.saveUsuarioDTO(UsuarioDTO); // Salva o novo usuário no serviço. //
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUsuario); // Retorna um status 201 (Created) com o usuário salvo. //
	}

	@PutMapping("/{id}") // Mapeia as solicitações PUT para este método //
	public ResponseEntity<UsuarioDTO> alteraUsuarioControl(@PathVariable Long id, @RequestBody @Valid UsuarioDTO UsuarioDTO) { // Este método atualiza um usuário existente. //
		UsuarioDTO alteraUsuario = UsuarioService.changeUsuarioDTO(id, UsuarioDTO); // Atualiza o usuário no serviço. //

		if (alteraUsuario != null) { // Verifica se o usuário existe. //
			return ResponseEntity.ok(UsuarioDTO); // Se existir, retorna o usuário atualizado. //
		} else {
			return ResponseEntity.notFound().build(); // Se não existir, retorna um status 404 (Not Found). //
		}
	}

	@DeleteMapping("/{id}") // Mapeia as solicitações DELETE para este método //
	public ResponseEntity<String> deleteUsuarioControl(@PathVariable Long id) { // Este método deleta um usuário pelo seu ID. //
		boolean delete = UsuarioService.deleteUsuarioDTO(id); // Deleta o usuário no serviço. //
		if (delete) { // Verifica se o usuário foi deletado. //
			return ResponseEntity.ok().body("O produto foi excluido com o sucesso"); // Se foi deletado, retorna um status 200 (OK) com uma mensagem de sucesso. //
		} else {
			return ResponseEntity.notFound().build(); // Se não foi deletado, retorna um status 404 (Not Found). //
		}
	}
}
