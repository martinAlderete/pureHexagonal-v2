package com.gyl.bys.domain.ports.out;

public interface AuthenticationModelPort {
    /**
     * Verifica si una contraseña en texto plano coincide con una ya codificada.
     * @param rawPassword la contraseña sin codificar.
     * @param encodedPassword la contraseña codificada de la base de datos.
     * @return true si coinciden, false en caso contrario.
     */
    boolean matches(String rawPassword, String encodedPassword);
}