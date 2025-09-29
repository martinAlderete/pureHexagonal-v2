package com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.registrarOProcesarUsuario.dto;

public record RegistroUsuarioRequest(String email, String password, String googleId) {
}
