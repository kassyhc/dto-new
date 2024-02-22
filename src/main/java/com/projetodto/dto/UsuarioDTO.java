package com.projetodto.dto;

public record UsuarioDTO(Long id, String nome, String senha) { // filtra os dados para não ser acessado a camada permissão //


}
