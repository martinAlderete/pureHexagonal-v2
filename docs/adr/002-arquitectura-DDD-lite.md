# ADR-002 – Uso de DDDLite en lugar de DDDFull

**Fecha:** 15-09-2025  
**Estado:** Aceptado

## 1. Contexto
- Nuestro proyecto actual es de complejidad media/baja y aun existe incertidumbre sobre su futura escala.
- El equipo esta comenzando a aplicar **Domain-Driven Design (DDD)** por primera vez.
- DDD-Full aporta gran calidad y alineacion con el negocio, pero introduce una burocracia y curva de aprendizaje elevadas.
- Necesitamos entregas rapidas sin comprometer las bases de un diseno que pueda evolucionar.

## 2. Decision
Adoptaremos **DDD-Lite** en lugar de DDD-Full. Esto significa:

**Incluimos:**
- **Domain Layer:** Entities, Policies, Domain Services, Ports (out), Aggregates, Value Objects (VO).
- **Application Layer.**
- **Infrastructure Layer.**

**Omitimos por ahora:**
- Context Maps.
- Definicion explicita de Subdominios (Core, Supporting, Generic).
- Delimitacion estricta de Bounded Contexts.

## 3. Alternativas consideradas
1. **DDD-Full**
    - Ventaja: Modelo de dominio robusto desde el inicio.
    - Desventaja: Sobrecarga burocratica y retrasos por curva de aprendizaje.
2. **No usar DDD**
    - Ventaja: Simplicidad inicial.
    - Desventaja: Riesgo de pobre alineacion con el negocio y mayor costo tecnico a futuro.

## 4. Consecuencias
**Positivas:**
- Menor burocracia inicial y curva de aprendizaje mas accesible.
- Permite entregas tempranas y aprendizaje progresivo.
- Facilita evolucionar a DDD-Full si el sistema crece.

**Negativas:**
- Riesgo de inconsistencias entre modulos al no tener Bounded Contexts definidos.
- Podra requerir refactorizacion adicional si la complejidad escala rapidamente.

## 5. Revision futura
Esta decision sera reevaluada cuando:
- El sistema muestre senales claras de crecimiento en complejidad.
- El equipo tenga mayor experiencia con DDD.  
