# DER (Búsquedas y Postulaciones) [ByS] | Hoja de Ruta


## 1. Búsquedas

Johana crea una búsqueda la cual puede tener diferentes estados.  
La búsqueda contempla:
- **Seniority**
- **Habilidades**
- **Profesión** del candidato

Cada búsqueda está asociada a diferentes **reclutadores**.

### Candidatos y postulaciones
- Un reclutador puede:
    - Cargar candidatos disponibles en el sistema.
    - Cargar candidatos asociados a una búsqueda específica, creando así la **postulación**.
- La **postulación** tiene sucesivas **etapas** y **eventos**:
    - Ejemplo: en la etapa `Hunting` están los eventos `Contacto` y `Respuesta Contacto`.
    - (+ info: Ver Diagrama de eventos candidatos)

### Eventos y finalización
- En cada evento, existe la posibilidad de **finalizar el proceso**.
- Si se finaliza, se debe registrar el **motivo**, importante para análisis estadístico.
- Si el candidato avanza, se da la **entrevista**.
    - Si no se puede realizar, se registra el motivo y se puede **reagendar**.

---

## 2. Roles de usuario

Tipos de roles:

- **SuperAdmin**: Johana
- **Administrador**
- **Recruiter**
- **Comercial (recruiter externo)**:
    1. Solo puede **ver información**
    2. Además, puede **cargar candidatos**
        - Pregunta abierta: ¿puede saltarse los procesos y poner un candidato directamente a entrevista?

### Restricciones de visibilidad
- Reclutadores comerciales solo ven **sus propios candidatos**.
- Reclutadores G&L solo ven candidatos de G&L.
- Cada grupo va por **canales distintos**, sin superposición.

### Clasificación interna (Recursos Humanos)
- **Back**: acompaña candidatos hasta el primer contacto.
- **Front**: se encargan de todas las etapas sucesivas.

#### Objetivos por tipo
- **Reclutadores Back**: cargar 50 candidatos por día (o por semana).
    - Solo cuenta si se crea una postulación asociada a una búsqueda.
- **Reclutadores Front**: realizar 4 entrevistas por día (o por semana).
- Nota: A nivel sistema, no hay diferencias entre reclutadores de G&L; clasificación interna solo para RRHH.

---

## 3. Consideraciones futuras

- **Excel**: El ByS viejo autogenera un Excel cada 3 horas (TEDA).
- **Importación de datos** del sistema viejo al nuevo.
- Revisar DER viejo para identificar campos a actualizar: CV, auditoría, etc.
- En todas las instancias de eventos:
    - Posibilidad de crear **observaciones** y pasar a la siguiente fase.
- Cuando se **rechaza un candidato**:
    - Opción de enviar el perfil a **“reciclados”** (tabla de pantalla candidatos).
- Al final de la fase comercial:
    - Subir un archivo **PDF del informe** y pasar a la siguiente fase.

---

## 4. Observaciones adicionales

- **Persona**: en el campo industria, siempre **TECH**.
- **Reclutadores comerciales**:
    - Atados a un respectivo cliente.
    - Pueden cargar directamente una búsqueda con candidatos asociados, o responder a una búsqueda creada por Johana donde solo habrá reclutadores comerciales.  
