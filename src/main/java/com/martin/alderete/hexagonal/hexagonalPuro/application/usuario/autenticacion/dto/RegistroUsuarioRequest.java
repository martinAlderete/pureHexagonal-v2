package com.gyl.bys.application.usuario.autenticacion.dto;

public record RegistroUsuarioRequest(String email, String password, String googleId) {
}
