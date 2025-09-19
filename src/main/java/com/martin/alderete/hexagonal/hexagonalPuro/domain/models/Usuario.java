package com.gyl.bys.domain.models;

import com.gyl.bys.domain.models.abs.BaseBuilder;
import com.gyl.bys.domain.models.abs.DomainEntity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Usuario extends DomainEntity {

    private String email;
    private String password;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private LocalDateTime fechaAlta;
    private String googleId;
    private Set<Rol> roles;
    private Set<Cliente> clientes;
    private Set<GrupoFavorito> grupoFavoritos;


    public Usuario(Long id, String email, String password, boolean isEnabled) {
        super(id);
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.fechaAlta = LocalDateTime.now();
    }






    /*Getter y Setters*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Set<GrupoFavorito> getGrupoFavoritos() {
        return grupoFavoritos;
    }

    public void setGrupoFavoritos(Set<GrupoFavorito> grupoFavoritos) {
        this.grupoFavoritos = grupoFavoritos;
    }












    /*Builder*/

    private Usuario(Builder builder) {
        super(builder.id); // id obligatorio en DomainEntity
        this.email = builder.email;
        this.password = builder.password;
        this.isEnabled = builder.isEnabled;
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

        private final Long id; // obligatorio
        private String email;
        private String password;
        private boolean isEnabled;
        private boolean isAccountNonExpired;
        private boolean isAccountNonLocked;
        private boolean isCredentialsNonExpired;
        private LocalDateTime fechaAlta;
        private String googleId;
        private Set<Rol> roles = new HashSet<>();
        private Set<Cliente> clientes = new HashSet<>();
        private Set<GrupoFavorito> grupoFavoritos = new HashSet<>();

        public Builder(Long id) {
            this.id = id; // validación de DomainEntity
        }

        public Builder email(String email) { this.email = email; return self(); }
        public Builder password(String password) { this.password = password; return self(); }
        public Builder isEnabled(boolean isEnabled) { this.isEnabled = isEnabled; return self(); }
        public Builder isAccountNonExpired(boolean isAccountNonExpired) { this.isAccountNonExpired = isAccountNonExpired; return self(); }
        public Builder isAccountNonLocked(boolean isAccountNonLocked) { this.isAccountNonLocked = isAccountNonLocked; return self(); }
        public Builder isCredentialsNonExpired(boolean isCredentialsNonExpired) { this.isCredentialsNonExpired = isCredentialsNonExpired; return self(); }
        public Builder fechaAlta(LocalDateTime fechaAlta) { this.fechaAlta = fechaAlta; return self(); }
        public Builder googleId(String googleId) { this.googleId = googleId; return self(); }

        // Sets completos
        public Builder roles(Set<Rol> roles) { this.roles = roles; return self(); }
        public Builder clientes(Set<Cliente> clientes) { this.clientes = clientes; return self(); }
        public Builder grupoFavoritos(Set<GrupoFavorito> grupoFavoritos) { this.grupoFavoritos = grupoFavoritos; return self(); }

        // Agregar individualmente
        public Builder addRol(Rol rol) { this.roles.add(rol); return self(); }
        public Builder addCliente(Cliente cliente) { this.clientes.add(cliente); return self(); }
        public Builder addGrupoFavorito(GrupoFavorito grupoFavorito) { this.grupoFavoritos.add(grupoFavorito); return self(); }

        @Override
        protected Usuario buildEntity() {
            return new Usuario(this);
        }
    }


}