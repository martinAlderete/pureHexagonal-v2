package com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.shared;

import com.martin.alderete.hexagonal.hexagonalPuro.domain.models.Usuario;

/**
 * Representa la respuesta unificada para cualquier flujo de autenticación o registro.
 * Al ser una 'sealed interface', obliga a los llamadores a manejar todos los casos posibles.
 */
public sealed interface ResultadoAutenticacion {
    record Exitoso(Usuario usuario) implements ResultadoAutenticacion {}
    record UsuarioPendiente(Usuario usuario, String mensaje) implements ResultadoAutenticacion {}
    record DominioNoPermitido(String mensaje) implements ResultadoAutenticacion {}
    record CredencialesInvalidas(String mensaje) implements ResultadoAutenticacion {}
}