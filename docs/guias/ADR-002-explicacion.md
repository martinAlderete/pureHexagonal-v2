# 📘 DDD-Lite: Guia para el Equipo

> Esta guia complementa el [adr-002 – Uso de DDDLite en lugar de DDDFull](./adr/adr-002-DDD-LITE.md).  
> Su objetivo es explicar en un tono cercano por que adoptamos DDD-Lite y como aplicarlo en el dia a dia.

---

## 1. Introduccion
- **Proposito:** Brindar una explicacion clara y accesible para que todos entiendan el “porque” y el “como” del DDD-Lite.
- **Audiencia:** Miembros del equipo de desarrollo que comienzan a trabajar con DDD.

---

## 2. Contexto y Decision
- **Decision tomada:** Usar **DDD-Lite** en lugar de DDD-Full.
- **Motivo:** Evitar burocracia innecesaria en un proyecto hoy mediano/chico y dejar abierta la posibilidad de migrar a DDD-Full si el sistema escala.

---

## 3. Por que tomamos esta decision?

### Entendimiento progresivo de DDD
- DDD-Full puede abrumar a un dev que recien empieza.
- Queremos que las bases de DDD se aprendan y practiquen poco a poco.

### Requerimientos del sistema
- Nuestra arquitectura no debe complejizar la solucion actual.
- DDD-Full seria un overkill en este momento de incertidumbre sobre la evolucion del sistema.

### Reduccion de burocracia
- DDD-Full requiere conocimientos avanzados y experiencia previa (la cual no hay aun).
- Usarlo completo generaria retardos y overhead innecesarios para un sistema de complejidad media/baja.

---

## 4. Que significa DDD-Lite?

### Dejamos de lado (por ahora):
- Context Maps.
- Definicion explicita de Subdominios (Core, Supporting, Generic).
- Delimitacion estricta de Bounded Contexts.

### Pero si usamos:
- **Domain:**
    - Entities
    - Policies
    - Domain Services
    - Ports (out)
    - Aggregates
    - Value Objects (VO)
- **Application Layer**
- **Infrastructure Layer**

---

## 5. Preguntas frecuentes del equipo

**Esto quiere decir que usaremos mal DDD?**
> No. Es una version ligera para permitir un crecimiento ordenado.

**Por que hacemos esto?**
> Para refinar y mejorar el diseno mientras implementamos, sin bloquear entregas tempranas.

**Podremos pasar a DDD-Full mas adelante?**
> Si. Esta decision deja la puerta abierta para expandir la arquitectura si el sistema crece.

**Hay riesgo de que olvidemos pasar a DDD-Full?**
> No si revisamos periodicamente. Este documento y el ADR nos recordaran reevaluar cuando el sistema cambie.

**Que pasa si un nuevo dev solo conoce DDD-Full?**
> Esta guia aclara nuestro enfoque. Puede aportar practicas de DDD-Full cuando sean necesarias.

---

## 6. Proximos pasos
- Revisar esta guia en las dailies o reuniones tecnicas.
- Revaluar cuando el sistema aumente de complejidad.
- Documentar aprendizajes y ajustes para futuras iteraciones.

---

### ✅ Buenas practicas minimas
- Usa **Value Objects** para conceptos clave.
- Manten el **lenguaje ubicuo** en los nombres.
- Evita logica de negocio en **Infrastructure**.  
