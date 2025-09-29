package com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out;


public interface PasswordEncoderModelPort {

    String encode(String password);
}
