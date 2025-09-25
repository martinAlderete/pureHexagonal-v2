package com.gyl.bys.domain.models;

import com.gyl.bys.domain.VO.EstadoUsuario;
import com.gyl.bys.domain.exception.DominioNoPermitidoException;
import com.gyl.bys.domain.models.abs.BaseBuilder;
import com.gyl.bys.domain.models.abs.DomainEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Usuario extends DomainEntity {

    private String email;
    private String password;
    private EstadoUsuario estado;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private LocalDateTime fechaAlta;
    private String googleId;

    private static final Logger log = LoggerFactory.getLogger(Usuario.class);

    private Set<Rol> roles = new HashSet<>();
    private Set<Cliente> clientes = new HashSet<>();
    private Set<GrupoFavorito> grupoFavoritos = new HashSet<>();

    public Usuario(
            Long id, String email, String password, EstadoUsuario estado,
            boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
            LocalDateTime fechaAlta, String googleId, Set<Rol> roles,
            Set<Cliente> clientes, Set<GrupoFavorito> grupoFavoritos
    ) {
        super(id);
        this.email = email;
        this.password = password;
        this.estado = estado;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.fechaAlta = fechaAlta;
        this.googleId = googleId;
        this.roles = roles;
        this.clientes = clientes;
        this.grupoFavoritos = grupoFavoritos;
    }

    /* ------------------- Métodos de negocio ------------------- */

    public void aprobar() {
        if (this.estado != EstadoUsuario.PENDIENTE) {
            throw new IllegalStateException("Solo se puede aprobar un usuario en estado PENDIENTE.");
        }
        this.estado = EstadoUsuario.ACTIVO;
        this.isAccountNonLocked = true;
        this.isAccountNonExpired = true;
        this.isCredentialsNonExpired = true;
    }

    public void desactivar() {
        if (this.estado != EstadoUsuario.ACTIVO) {
            throw new IllegalStateException("Solo se puede desactivar un usuario en estado ACTIVO.");
        }
        this.estado = EstadoUsuario.INACTIVO;
    }

    public void cambiarPassword(String nuevoPassword) {
        if (nuevoPassword == null || nuevoPassword.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
        }
        this.password = nuevoPassword;
    }

    public void asignarRol(Rol rol) {
        if (rol == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo.");
        }
        this.roles.add(rol);
    }

    public void asignarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        this.clientes.add(cliente);
    }

    public void asignarGrupoFavorito(GrupoFavorito grupoFavorito) {
        if (grupoFavorito == null) {
            throw new IllegalArgumentException("El grupo de favoritos no puede ser nulo.");
        }
        this.grupoFavoritos.add(grupoFavorito);
    }

    /* ------------------- Getters protegidos ------------------- */

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public boolean isAccountNonExpired() { return isAccountNonExpired; }
    public boolean isAccountNonLocked() { return isAccountNonLocked; }
    public boolean isCredentialsNonExpired() { return isCredentialsNonExpired; }
    public LocalDateTime getFechaAlta() { return fechaAlta; }
    public String getGoogleId() { return googleId; }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public Set<Rol> getRoles() { return Collections.unmodifiableSet(roles); }
    public Set<Cliente> getClientes() { return Collections.unmodifiableSet(clientes); }
    public Set<GrupoFavorito> getGrupoFavoritos() { return Collections.unmodifiableSet(grupoFavoritos); }



    /* ------------------- Creación y reconstitución ------------------- */

    // Creación: parámetros mínimos obligatorios
    public static Usuario create(String email, String password) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El email es obligatorio.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }

        return new Builder()
                .email(email)
                .password(password)
                .estado(EstadoUsuario.PENDIENTE)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .fechaAlta(LocalDateTime.now())
                .build();
    }



    public static Usuario registrarNuevoUsuario(String email, String password, String googleId){
        validarCreacion(email,password,googleId);

        return new Builder()
                .email(email)
                .password(password)
                .googleId(googleId)
                .estado(EstadoUsuario.PENDIENTE)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .fechaAlta(LocalDateTime.now())
                .build();

    }

    // Reconstitución desde BD
    public static Usuario reconstitute(
            Long id,
            String email,
            String password,
            EstadoUsuario estado,
            boolean isAccountNonExpired,
            boolean isAccountNonLocked,
            boolean isCredentialsNonExpired,
            LocalDateTime fechaAlta,
            String googleId,
            Set<Rol> roles,
            Set<Cliente> clientes,
            Set<GrupoFavorito> grupoFavoritos
    ) {
        return new Builder()
                .id(id)
                .email(email)
                .password(password)
                .estado(estado)
                .isAccountNonExpired(isAccountNonExpired)
                .isAccountNonLocked(isAccountNonLocked)
                .isCredentialsNonExpired(isCredentialsNonExpired)
                .fechaAlta(fechaAlta)
                .googleId(googleId)
                .roles(roles)
                .clientes(clientes)
                .grupoFavoritos(grupoFavoritos)
                .build();
    }

    private static void validarCreacion(String email, String password, String googleId) {
        log.info("--- DENTRO de Usuario.validarCreacion. Email: [{}], Password es nulo?: [{}], GoogleId: [{}]",
                email, (password == null), googleId); // LOG 13

        if (email == null || email.isBlank()) {
            log.error("VALIDACIÓN FALLÓ: El email es nulo o vacío.");
            throw new IllegalArgumentException("El email es obligatorio.");
        }
        if (!email.endsWith("@gylgroup.com")) {
            log.error("VALIDACIÓN FALLÓ: El dominio no es @gylgroup.com.");
            throw new DominioNoPermitidoException("El dominio de tu mail es invalido");
        }

        boolean isGoogleLogin = googleId != null && !googleId.isBlank();
        log.info("isGoogleLogin determinado como: [{}]", isGoogleLogin); // LOG 14

        if (!isGoogleLogin && (password == null || password.isBlank())) {
            log.error("VALIDACIÓN FALLÓ: No es login de Google y la contraseña es nula/vacía.");
            throw new IllegalArgumentException("La contraseña es obligatoria para el registro normal.");
        }
        log.info("Validación de creación superada exitosamente."); // LOG 15
    }

    /* ------------------- Builder ------------------- */

    private Usuario(Builder builder) {
        super(builder.id);
        this.email = builder.email;
        this.password = builder.password;
        this.estado = builder.estado;
        this.isAccountNonExpired = builder.isAccountNonExpired;
        this.isAccountNonLocked = builder.isAccountNonLocked;
        this.isCredentialsNonExpired = builder.isCredentialsNonExpired;
        this.fechaAlta = builder.fechaAlta;
        this.googleId = builder.googleId;
        this.roles = builder.safeSet(builder.roles);
        this.clientes = builder.safeSet(builder.clientes);
        this.grupoFavoritos = builder.safeSet(builder.grupoFavoritos);
    }

    public static class Builder extends BaseBuilder<Usuario, Builder> {
        private Long id;
        private String email;
        private String password;
        private EstadoUsuario estado;
        // SOLUCIÓN: Inicializar con valores por defecto
        private boolean isAccountNonExpired = true;
        private boolean isAccountNonLocked = true;
        private boolean isCredentialsNonExpired = true;
        private LocalDateTime fechaAlta;
        private String googleId;

        private Set<Rol> roles = new HashSet<>();
        private Set<Cliente> clientes = new HashSet<>();
        private Set<GrupoFavorito> grupoFavoritos = new HashSet<>();

        public Builder id(Long id) { this.id = id; return self(); }
        public Builder email(String email) { this.email = email; return self(); }
        public Builder password(String password) { this.password = password; return self(); }
        public Builder estado(EstadoUsuario estado) { this.estado = estado; return self(); }
        public Builder isAccountNonExpired(boolean isAccountNonExpired) { this.isAccountNonExpired = isAccountNonExpired; return self(); }
        public Builder isAccountNonLocked(boolean isAccountNonLocked) { this.isAccountNonLocked = isAccountNonLocked; return self(); }
        public Builder isCredentialsNonExpired(boolean isCredentialsNonExpired) { this.isCredentialsNonExpired = isCredentialsNonExpired; return self(); }
        public Builder fechaAlta(LocalDateTime fechaAlta) { this.fechaAlta = fechaAlta; return self(); }
        public Builder googleId(String googleId) { this.googleId = googleId; return self(); }

        public Builder roles(Set<Rol> roles) { this.roles = roles; return self(); }
        public Builder clientes(Set<Cliente> clientes) { this.clientes = clientes; return self(); }
        public Builder grupoFavoritos(Set<GrupoFavorito> grupoFavoritos) { this.grupoFavoritos = grupoFavoritos; return self(); }

        public Builder addRol(Rol rol) { this.roles.add(rol); return self(); }
        public Builder addCliente(Cliente cliente) { this.clientes.add(cliente); return self(); }
        public Builder addGrupoFavorito(GrupoFavorito grupoFavorito) { this.grupoFavoritos.add(grupoFavorito); return self(); }

        @Override
        protected Usuario buildEntity() {
            if (email == null || email.isBlank()) {
                throw new IllegalStateException("El email es obligatorio para crear el usuario.");
            }
            return new Usuario(this);
        }
    }
}