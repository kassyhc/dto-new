package com.projetodto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetodto.dto.UsuarioDTO;
import com.projetodto.entities.Usuario;
import com.projetodto.repository.UsuarioRepository;

@Service // indica que a classe é um serviço  //
public class UsuarioService { // Declaração da classe de serviço. //
	private final UsuarioRepository UsuarioRepository;  // Declaração de uma variável final //
	
	@Autowired  // injeção de dependência do repositório na classe de serviço. //
	public UsuarioService(UsuarioRepository UsuarioRepository) { // Este é o construtor da classe de serviço. //
		this.UsuarioRepository = UsuarioRepository; // Atribui o repositório de usuários à variável de instância. //
	}

	public List<Usuario> getAllUsuarioDTOs() { // Este método retorna todos os usuários. //
		return UsuarioRepository.findAll(); // Chama o método findAll que retorna todos os usuários. //
	}

	public Usuario getUsuarioDTOById(Long id) { // Este método retorna um usuário específico pelo seu ID. //
		Optional<Usuario> Usuario = UsuarioRepository.findById(id); // Busca o usuário pelo ID //
		return Usuario.orElse(null); // Retorna o usuário se encontrado, ou null se não encontrado. //
	}

	public UsuarioDTO saveUsuarioDTO(UsuarioDTO UsuarioDTO) { // Este método salva um novo usuário no repositório. //
		Usuario usuario = new Usuario(UsuarioDTO.nome(), UsuarioDTO.senha()); // Cria um novo objeto de usuário com o nome e senha fornecidos. //
		Usuario salvarUsuario = UsuarioRepository.save(usuario); // Salva o novo usuário no repositório. //
		return new UsuarioDTO(salvarUsuario.getId(), salvarUsuario.getNome(), salvarUsuario.getSenha()); // Retorna um DTO do usuário salvo. //
	}

	public UsuarioDTO changeUsuarioDTO(Long id, UsuarioDTO UsuarioDTO) { // Este método atualiza um usuário existente. //
		Usuario existeUsuario = UsuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado")); // Busca o usuário pelo ID. Se não encontrado, lança uma exceção. //
		
		existeUsuario.setNome(UsuarioDTO.nome()); // Atualiza o nome do usuário. //
		existeUsuario.setSenha(UsuarioDTO.senha()); // Atualiza a senha do usuário. //
		
		Usuario updateUsuario = UsuarioRepository.save(existeUsuario); // Salva o usuário atualizado no repositório. //
		return new UsuarioDTO(updateUsuario.getId(), updateUsuario.getNome(), updateUsuario.getSenha()); // Retorna um DTO do usuário atualizado. //
	}

	public boolean deleteUsuarioDTO(Long id) { // Este método deleta um usuário pelo seu ID. //
		Optional<Usuario> existeUsuarioDTO= UsuarioRepository.findById(id); // Busca o usuário pelo ID. //
		if (existeUsuarioDTO.isPresent()) { // Verifica se o usuário existe. //
			UsuarioRepository.deleteById(id); // Se existir, deleta o usuário pelo ID. //
			return true; // Retorna verdadeiro indicando que o usuário foi deletado. //
		}
		return false; // Se o usuário não existir, retorna falso. //
	}
}

