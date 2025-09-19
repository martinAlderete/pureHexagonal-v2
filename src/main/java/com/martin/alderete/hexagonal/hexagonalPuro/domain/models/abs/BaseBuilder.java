package com.gyl.bys.domain.models.abs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseBuilder<T, B extends BaseBuilder<T, B>> {

    /** Método que debe implementar la subclase para construir la entidad final */
    protected abstract T buildEntity();

    /** Retorna el builder correctamente tipado para métodos fluidos */
    @SuppressWarnings("unchecked")
    protected B self() {
        return (B) this;
    }

    /** Construye la entidad final */
    public T build() {
        return buildEntity();
    }

    /** Inicializa una lista si es null */
    public  <E> List<E> safeList(List<E> list) {
        return list != null ? list : new ArrayList<>();
    }

    // Método para sets
    public <E> Set<E> safeSet(Set<E> set) {
        return set != null ? set : new HashSet<>();
    }
}
