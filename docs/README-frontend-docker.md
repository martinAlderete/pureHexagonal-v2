# Guía completa para Front-end Developers acerca de como utilizar Docker

### Nota: si ya tenes el DockerDesktop ejecuta el comando "docker compose up --build"
### estando en la carpeta raiz en la consola del git, esto va a levantar el back y la base de datos automaticamente



## 1️⃣ Requisitos previos

- Tener instalado:
    - **Docker Desktop** 
    - Opcional: **PGAdmin** para ver la base de datos

> No necesitan tener PostgreSQL instalado localmente ni configurar Java.

---

## 2️⃣ Estructura del proyecto

backbys-review/
│
├─ docker-compose.yml
├─ Dockerfile
├─ src/
│ └─ main/resources/application.properties
└─ ...


- `Dockerfile` → define cómo construir la imagen del backend.
- `docker-compose.yml` → levanta **PostgreSQL + backend** en contenedores conectados.
- `application.properties` → configuración del backend con **variables de entorno**, listo para Docker.

---

## 3️⃣ Configuración de puertos

En `docker-compose.yml`:

| Servicio      | Puerto contenedor | Puerto host | Comentario                         |
|---------------|-------------------|-------------|------------------------------------|
| PostgreSQL    | 5432              | 5433        | PGAdmin se conecta aquí            |
| Backend       | 8080              | 8080        | Acceso a los endpoints del backend |

> Usamos 5433 para el host porque 5432 puede estar ocupado por PostgreSQL local.

---

## 4️⃣ Levantar todo con Docker

Desde la raíz del proyecto ejecuta este comando en el git bash:

docker compose up --build

- `--build` fuerza la reconstrucción de la imagen del backend.

- Se levantarán dos contenedores:
    - `db` → PostgreSQL
    - `backend` → Spring Boot

- El backend estará accesible en: `http://localhost:8080`

- PGAdmin (si se abre) puede conectarse a `localhost:5433`.
---

##  5️⃣ Verificar que la base de datos funciona

1. Abrir **PGAdmin** y crear un nuevo servidor.
2. Configurar los siguientes parámetros:

| Parametro | Valor     |
|-----------|-----------|
| Hostname  | localhost |
| Port      | 5433      |
| Database  | postgres  |
| Username  | postgres  |
| Password  | postgres  |

3. Expandir **Schemas → public** y revisar las tablas creadas por Hibernate.
4. Confirmar que los datos iniciales (roles, usuarios) estén insertados.
