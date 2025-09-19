# 0001 - Arquitectura DDD con Vertical Slice 

**Estado:** Aceptada

## Contexto
Se requiere una arquitectura limpia y escalable para nuestro backend en Java con Spring Boot.  
El objetivo es que el sistema sea **mantenible, comprensible y alineado con el negocio**.  
Queremos que la arquitectura “grite” el comportamiento del sistema, facilitando la comprensión de los casos de uso y la evolución de las funcionalidades.

## Decision
Adoptamos la siguiente estructura de capas y convenciones:

1. **Domain**
    - Contiene entidades, aggregates, value objects, business rules y domain policies.
    - Esta capa **no depende de ninguna otra** y se implementa en Java puro, sin frameworks.

2. **Application**
    - Contiene casos de uso organizados en **vertical slices**, donde cada slice corresponde a una funcionalidad completa.
    - Incluye DTOs, validadores y servicios de integración interna específicos de la funcionalidad.
    - Depende únicamente de la capa `domain` y se implementa en Java puro.

3. **Infrastructure**
    - Implementación de repositorios, adaptadores de servicios externos y configuración técnica.
    - Puede depender de `application` y `domain`.
    - Implementado con Spring Boot y librerías de infraestructura necesarias.



### Principios clave:

- **Lenguaje ubicuo:** todos los nombres reflejan el dominio del negocio.
- **Vertical slices:** cada slice representa una funcionalidad autónoma y testable de manera independiente.
- **Dependencias estrictas:** domain → ninguna | application → domain | infrastructure → application/domain.
- **Documentación versionada:** ADRs y guías de contribución se mantienen junto con el código (Doc as Code).


## Beneficios
- Claridad inmediata sobre responsabilidades de cada capa y slice.
- Facilita pruebas unitarias e integración independiente por funcionalidad.
- Facilita onboarding y reduce la curva de aprendizaje del equipo.


## Verificacion
Para asegurar que la arquitectura definida se cumpla de manera consistente:

1. **ArchUnit:**
    - Se configurarán pruebas unitarias de ArchUnit que validan las dependencias entre capas (`domain`, `application`, `infrastructure`).
    - Las pruebas impedirán que `domain` dependa de otra capa, que `application` no dependa de infraestructura directamente, y que cada slice sea autónoma dentro de `application`.
    - Las pruebas impediran que se utilice tecnologias externas en la capa de application y domain, todo lo que no sea .java , .javax o anotaciones propias seran bloqueadas.

2. **Pipeline de CI/CD:**
    - Todas las pruebas de ArchUnit se ejecutarán automáticamente en cada commit y merge request.
    - Si alguna regla de arquitectura es violada, el pipeline falla y bloquea el commit.
    - Esto garantiza cumplimiento automático y evidencia auditada de que la arquitectura se respeta.

3. **Documentación viva:**
    - Los ADRs y convenciones de capas estarán versionados junto con el código (Doc as Code).
    - Esto permite que cualquier cambio de arquitectura quede registrado y revisable históricamente.


## Alternativas consideradas
1. **Arquitectura en capas horizontal tradicional**
    - Descartada por generar acoplamiento horizontal, dificultad de pruebas y limitación para reflejar comportamientos de negocio de manera explícita.

2. **Arquitectura MVC tradicional**
    - Descartada por complejidad de mantenimiento, acoplamiento entre controladores y servicios, y riesgo de que la arquitectura no sea comprensible para nuevos miembros del equipo.