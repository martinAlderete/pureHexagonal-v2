package com.martin.alderete.hexagonal.hexagonalPuro.domain.models.abs;

public abstract class DomainEntity {

    private final Long id;

    protected DomainEntity(Long id) {
        this.id=validarId(id);
    }

    private Long validarId (Long id){
        validarQueSeaPositivo(id);
        return id;
    }

    // este metodo no , porque todas las  entidadades de dominio al depender de la base de datos en en sistema, siempre sus claves van a ser null al inicio
    private void validarQueNoSeaNulo (Long id){
        if (id == null) throw new IllegalArgumentException("El id del modelo no puede ser nulo");
    }

    private void validarQueSeaPositivo(Long id){
        if(id!=null && id<=0) throw new IllegalArgumentException("El id del modelo debe ser un numero positivo");
    }

    public Long getId() {
        return id;
    }

    // Igualdad basada en ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DomainEntity)) return false;
        DomainEntity that = (DomainEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

