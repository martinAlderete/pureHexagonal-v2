##  Convención de Testing en Java

# Estructura de carpetas
En un proyecto **Maven** la estructura recomendada para los tests es:


- **`src/main/java`** → código fuente.
- **`src/test/java`** → código de prueba (unitaria, integración, etc.).
- Las carpetas de test deben **reflejar la misma estructura de paquetes** que `src/main/java`.

### Ejemplo:

src/main/java/com/empresa/proyecto/service/UserService.java

src/test/java/com/empresa/proyecto/service/UserServiceTest.java


---

# Convención de nombres para clases de test

| Tipo de Test                | Formato sugerido              | Ejemplo                                   |
|-----------------------------|--------------------------------|-------------------------------------------|
| Test unitario               | `NombreClaseTest`              | `UserServiceTest`                         |
| Test de integración         | `NombreClaseIT`                | `UserRepositoryIT`                        |

---

# Convención de nombres para métodos de test (Given–When–Then)

### Formato:

given(CondiciónInicial)_when(Acción)_then(ResultadoEsperado)

Ejemplos:
- `givenValidId_whenUserExists_thenReturnUser`
- `givenNullInput_whenSaving_thenThrowException`



---

# Ejemplo en código

```java


class UserServiceTest {

    @Test
    void givenValidId_whenUserExists_thenReturnUser() {
        // GIVEN: Preparación del contexto y datos iniciales
        UserRepository userRepository = new InMemoryUserRepository();
        userRepository.save(new User(1L, "Juan"));
        UserService userService = new UserService(userRepository);

        // WHEN: Ejecución de la acción que se quiere probar
        User result = userService.findById(1L);

        // THEN: Verificación de resultados
        assertNotNull(result);
        assertEquals("Juan", result.getName());
    }
}

```

# Notas adicionales
GIVEN:

- Inicializar datos.

- Configurar mocks/stubs.

- Preparar el estado inicial del sistema.

WHEN:

- Llamar al método que se está probando.

- Ejecutar la acción principal.

THEN:

- Usar aserciones (assertEquals, assertTrue, etc.).

- Verificar interacciones (en caso de usar Mockito: verify(...)).