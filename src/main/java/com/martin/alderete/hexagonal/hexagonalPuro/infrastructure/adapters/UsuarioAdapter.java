package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.adapters;

import com.martin.alderete.hexagonal.hexagonalPuro.domain.models.Usuario;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out.UsuarioModelPort;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.entities.UsuarioEntity;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.mappers.UsuarioMapper;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.repositories.UsuarioRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Component
public class UsuarioAdapter implements UsuarioModelPort {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper mapper;

    public UsuarioAdapter(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = usuarioMapper;
    }

    @Override @Transactional(readOnly = true)
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email).map(mapper::toDomain);
    }
    @Override @Transactional
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        return mapper.toDomain(usuarioRepository.save(entity));
    }
}