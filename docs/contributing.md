# 📘 Git – Convención de Ramas y Roles (v1.1.2)

Este documento establece la convención profesional adoptada para la **gestión de ramas y permisos en Git**, aplicando una jerarquía clara basada en Git Flow, con control estricto de accesos por rol y tipo de rama.

**Regla general:** Los nombres de ramas y los mensajes de commit deben escribirse en **Español**.

---

## 📑 Índice

1. [Alcance]
2. [Modelo de ramificación]
3. [Roles y permisos]
4. [Flujo de trabajo (Workflow)]
5. [Seguridad y Auditoría]
6. [Prohibiciones Claras]
7. [Buenas Prácticas]
8. [Estándar de Commits: Conventional Commits (v1.0.0)]


═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

## 1. Alcance

Este documento aplica a **todas las ramas** del repositorio, a **todos los commits** y al proceso de **revisión, integración y despliegue**.  
Cualquier excepción debe ser aprobada explícitamente por el **Owner** y documentada en un issue asociado.

═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

## 2. Modelo de ramificación

La organización de ramas sigue un flujo jerárquico descendente:


main
└─ release
└─ develop
└─ topic-branches   
hotfix/*


### Descripción por nivel:

| Rama             | Propósito                                                                                                                 |
|------------------|---------------------------------------------------------------------------------------------------------------------------|
| `main`           | Rama principal. Contiene el código liberado en producción.                                                                |
| `release`        | Prepara versiones candidatas para producción.                                                                             |
| `develop`        | Rama de integración de nuevas funcionalidades antes de ser liberadas.                                                     |
| `topic_branches` | Ramas de desarrollo específicas creadas desde `develop`, utilizadas por los Developers (ej: feature,fix,enhancement,etc). |
| `hotfix/*`       | Correcciones urgentes directamente sobre `main`.                                                                          |

`topic branch`: es cualquier rama temporal que nace desde `develop` o desde otra rama de trabajo para atacar un tema específico, y que se eliminará después de mergearse.

Ejemplo:
feature/ARCH-101-pantalla-login  
bugfix/ARCH-303-null-pointer-dashboard  
spike/ARCH-789-nuevo-csv-parser


###  Convención de Nombres de Ramas (Naming)

- Usar `kebab-case` (minúsculas y guiones) para nombres de ramas.
- Prefijos obligatorios:

`````````````````````
feature/ARCH-101-login-mobile   ✔️
feature/ARCH-202-gestion-de-factura   ✔️
fix/ARCH-303-redondeo-decimal   ✔️
hotfix/ARCH-404-crasheo-del-email     ✔️
release/v1.2.0                  ✔️
`````````````````````

`````````````````````
FeatureLoginMobile    ❌
FixDecimalRounding    ❌
bugFixEmailCrash      ❌
release_v1.2.0        ❌
hotFix/emailCrash     ❌
Release/1.2.0         ❌
`````````````````````

═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

## 3. Roles y Permisos

A continuación, se detallan los roles definidos en el repositorio Git y sus alcances.

🔐 Rol: `Owner` (Rol superior)

Funcion: **Responsable de fusiones críticas y mantenimiento de la línea de producción.**

| Permisos                                                  | Estado                            |
|-----------------------------------------------------------|-----------------------------------|
| Gestión completa de `develop`,`release`,`main`            | ✅                                 |
| Crear, gestionar y fusionar ramas `hotfix/*` sobre `main` | ✅                                 |
| Fusionar `develop` → `release/*`                          | ✅                                 |
| Fusionar `release/*` → `main`                             | ✅                                 |
| Fusionar  `hotfix/*` → `main` y `develop`                 | ✅                                 |
| Aprobación de MR en `develop`,`release`,`main`            | ✅                                 |
| Push directo a `main`, `release/*`, `develop`             | ❌ Solo mediante Merge Request(MR) |

────────────────────────────────────────────────────────────────────────────────────────────

🛠️ Rol: `Maintainer`

Funcion: **Responsable de controlar calidad de código en la fase de desarrollo.**

| Permisos                                         | Estado             |
|--------------------------------------------------|--------------------|
| Gestión completa de `develop`                    | ✅                  |
| Acceso a `develop` y a todas las Topic Branches. | ✅                  |
| Fusionar `topicBranch` → `develop`               | ✅                  |
| Revisión y aprobación de MR desde `topicBranch`  | ✅                  |
| Crear ramas `topicBranch` desde `develop`        | ✅                  |
| Push directo a `develop`                         | ❌ Solo mediante MR |

────────────────────────────────────────────────────────────────────────────────────────────


👨‍💻 Rol: `Developer`

Funcion: **Encargado de desarrollar funcionalidades y aplicar fixes.**

| Permisos                                                            | Estado      |
|---------------------------------------------------------------------|-------------|
| Crear ramas `topicBranch` desde `develop`                           | ✅           |
| Solo puede trabajar en ramas derivadas de `develop`(Topic Branches) | ✅           |
| Hacer Pull Request únicamente hacia `develop`                       | ✅           |
| Requiere revisión de MR por parte de `Maintainer`                   | ✅           |
| Push directo a `main`, `release/*`, `develop`, `hotfix/*`           | ❌ Prohibido |

═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

## 4. Flujo de Trabajo Esperado

🛠️ Topic-Branch

1. `Developer` crea `feature/login-mobile` (topic-branch) desde `develop`.
2. Realiza commits y push en su rama.
3. Envía MR hacia `develop`.
4. `Maintainer` revisa y aprueba.
5. Se fusiona en `develop`.

🚀 Release

1. `Owner` crea `release/v1.2.0` desde `develop`.
2. Se testea, documenta y estabiliza el código.
3. Una vez aprobado, se fusiona en `main`.
4. Se taggea versión estable.

🔥 Hotfix

1. `Owner` crea `hotfix/timeout-bug` desde `main`.
2. Aplica fix crítico.
3. Fusiona directamente en `main`.
4. Se propaga el fix a `develop` y `release/*` si corresponde.

═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

## 5. Seguridad y Auditoría

- Toda actividad debe quedar registrada mediante **Merge Requests (MR)**. No se permite trabajo fuera de control.
- Se deben habilitar **reglas de protección de ramas** en el repositorio:
    - `main` → ❌ push directo,      ✅ solo vía MR aprobado.
    - `release/*` → ❌ push directo, ✅ solo vía MR aprobado.
    - `develop` → ❌ push directo,   ✅ solo vía MR aprobado.
- Se recomienda habilitar validación de mínimo un revisor para poder mergear.
- Está **prohibido el uso de `git rebase --interactive` o `force push` sobre ramas compartidas**.
- En caso de pérdida o corrupción de una rama, se debe restaurar desde los commits anteriores o tags correspondientes (por eso es clave el versionado y tagging controlado).

═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

## 6. Prohibiciones Claras

❌ Push directo a `main`, `release/*`, `develop`        
❌ Merge sin MR aprobado                             
❌ Commit sin convenciones de nombre
❌ Evitar `git push --force`

═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

## 7. Buenas Prácticas

- Siempre crear ramas desde `develop`, salvo excepciones (`hotfix`).
- Un MR debe estar validado por al menos un revisor (preferentemente `Maintainer`).
- Borrar ramas luego de mergear (debe ser hecho por un  `Maintainer`) (Las ramas deben ser eliminadas pasados los 7 dias desde su merge).
- Los nombres de ramas deben ser descriptivos y reflejar el objetivo.

═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

## 8. Estándar de Commits: Conventional Commits (v1.0.0)

🎯 Objetivo: Garantizar claridad, trazabilidad, y automatización del historial de commits.

📝 Formato del mensaje:

- tipo (alcance): descripción corta [#ID_TICKET] [n/m]

### Descripción:

| Elemento                | Descripción                                                                |
|-------------------------|----------------------------------------------------------------------------|
| `tipo`                  | Tipo de cambio (ver tabla de #Tipos válidos).                                            |
| `alcance` (recomendado) | Área del código afectada (por ejemplo: `auth`, `core`, `ui`).              |
| `descripción corta`     | Resumen breve y claro del cambio realizado.                                |
| `#ID_TICKET`            | Identificador de la tarea o issue (por ejemplo: numero de ticket en Jira). |
| `[n/m]` (opcional)      | Indica que el commit forma parte de una serie de cambios divididos.        | 

`[n/m]` : Indica que el commit forma parte de una serie de commits relacionados divididos en **m** partes. **n** es el número de este commit. Ejemplo: [1/3] es el primer commit de una serie de tres. se usa cuando el cambio que se quiere hacer es complejo y amerita dividir su desarrollo en multiples partes, entonces para no subir un solo cambio muy grande, se particiona y se sube en commits separados. pero usamos el [n/m] para no perder el hilo del avance de este cambio, por si alguien mete mas commits en el medio y no llegamos a subir todas las partes una tras otra


• Ejemplo:

- feat(auth): add support for Google login #AUTH-42e
- fix(billing): fix tax calculation #BILL-231
- refactor(core): extract validation logic into independent module #CORE-78
- refactor(core): extract validation logic into independent module #CORE-78 [1/3]


### Tipos válidos:

| Tipo         | Descripción                                             |
|------------- |---------------------------------------------------------|
| `feat`       | Nueva funcionalidad                                     |
| `fix`        | Corrección de errores                                   |
| `enhancement`| Mejoras menores sobre funcionalidades existentes        |
| `docs`       | Cambios en la documentación                             |
| `style`      | Cambios de formato que no afectan la lógica             |
| `refactor`   | Cambios internos sin alterar funcionalidad              |
| `perf`       | Mejoras de rendimiento                                  |
| `test`       | Agregado o modificación de tests                        |
| `chore`      | Tareas del sistema de construcción, mantenimiento, etc  |
| `ci`         | Cambios relacionados con la integración continua        |

---

> ⚠️ Todos los commits deben seguir este estándar para asegurar consistencia y trazabilidad en el historial del proyecto.


