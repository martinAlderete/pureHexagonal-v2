package com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.autenticacion.dto;

public record RegistroUsuarioRequest(String email, String password, String googleId) {
}
