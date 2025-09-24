package com.gyl.bys.infrastructure.adapters;

import com.gyl.bys.domain.models.Usuario;
import com.gyl.bys.domain.ports.out.UsuarioModelPort;
import com.gyl.bys.infrastructure.entities.UsuarioEntity;
import com.gyl.bys.infrastructure.mappers.UsuarioMapper;
import com.gyl.bys.infrastructure.repositories.UsuarioRepository;
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