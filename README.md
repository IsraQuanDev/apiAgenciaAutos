 
# API Agencia de Autos

Este proyecto es una API RESTful para una agencia de autos, desarrollada con **Spring Boot**. La API permite gestionar modelos de autos, marcas y realizar validaciones sobre las entidades para garantizar la integridad de los datos.

## Endpoints

### 1. Listar Modelos

- **Endpoint**: `GET /ModelosWs/listar`
- **Descripción**: Obtiene la lista de todos los modelos de autos registrados.
- **Respuesta**: Devuelve una lista de objetos `Modelos` con los siguientes campos:
  - `id`: Identificador del modelo.
  - `nombre`: Nombre del modelo de auto.
  - `transmission`: Tipo de transmisión (ej. "Automático").
  - `precio`: Precio del modelo.
  - `existencia`: Número de unidades disponibles.
  - `marca`: Información de la marca asociada al modelo.

### 2. Guardar Modelo

- **Endpoint**: `POST /ModelosWs/guardar`
- **Descripción**: Permite guardar un nuevo modelo de auto. Valida que el `idMarca` exista antes de guardar el modelo.
- **Cuerpo de la solicitud**:
    ```json
    {
        "id": 1,
        "nombre": "Model X",
        "transmission": "Automático",
        "precio": 35000.0,
        "existencia": 50,
        "marca": {
            "id": 1,
            "nombre": "Tesla",
            "origen": "EEUU",
            "fechaLanz": "2015-01-01"
        }
    }
    ```
- **Respuesta**:
  - Si el `idMarca` no existe: `"No se guardo idMarca no existe"`.
  - Si el `idModelo` o el `nombreModelo` ya existen: `"No se guardo idModelo ya existe"` o `"No se guardo nombreModelo ya existe"`.
  - Si todo está correcto: Devuelve el modelo guardado.

### 3. Buscar Modelos por Nombre

- **Endpoint**: `POST /ModelosWs/buscar`
- **Descripción**: Busca los modelos que contengan el nombre proporcionado (insensible a mayúsculas).
- **Cuerpo de la solicitud**:
    ```json
    "Modelo X"
    ```
- **Respuesta**: Lista de modelos que coinciden con el nombre proporcionado.

### 4. Editar Modelo

- **Endpoint**: `POST /ModelosWs/editar`
- **Descripción**: Permite editar un modelo existente. Valida que el `idModelo` y el `idMarca` existan antes de editar el modelo.
- **Cuerpo de la solicitud**:
    ```json
    {
        "id": 1,
        "nombre": "Model X Actualizado",
        "transmission": "Manual",
        "precio": 33000.0,
        "existencia": 30,
        "marca": {
            "id": 1,
            "nombre": "Tesla",
            "origen": "EEUU",
            "fechaLanz": "2015-01-01"
        }
    }
    ```
- **Respuesta**:
  - Si el `idModelo` no existe: `"No se pudo editar: El modelo con el ID proporcionado no existe."`.
  - Si el `idMarca` no existe: `"No se pudo editar: El ID de marca proporcionado no existe."`.
  - Si el modelo fue editado correctamente: Devuelve el modelo editado.

### 5. Eliminar Modelo

- **Endpoint**: `POST /ModelosWs/eliminar`
- **Descripción**: Elimina un modelo de auto según el `idModelo`. Valida que el modelo exista antes de eliminarlo.
- **Cuerpo de la solicitud**:
    ```json
    1
    ```
- **Respuesta**:
  - Si el `idModelo` no existe: `"No se pudo eliminar: El modelo con el ID proporcionado no existe."`.
  - Si el modelo fue eliminado correctamente: `"Modelo eliminado exitosamente."`.

## Estructura de la Base de Datos

La base de datos tiene las siguientes tablas:

### 1. `MODELOS_AGEN`
- `id`: Identificador del modelo (Primary Key).
- `nombre`: Nombre del modelo de auto.
- `transmission`: Tipo de transmisión.
- `precio`: Precio del modelo.
- `existencia`: Número de unidades disponibles.
- `id_marca`: Clave foránea que referencia a la tabla `MARCAS_AGEN`.

### 2. `MARCAS_AGEN`
- `id`: Identificador de la marca (Primary Key).
- `nombre`: Nombre de la marca.
- `origen`: País de origen de la marca.
- `fechaLanz`: Fecha de lanzamiento de la marca.

## Requisitos

- Java 17 o superior.
- Spring Boot 3.0 o superior.
- JPA/Hibernate.
- Base de datos compatible con `JPA` (como MySQL, PostgreSQL, Oracle).

## Instalación

1. **Clonar el repositorio**:
    ```bash
    git clone https://github.com/tu_usuario/tu_repositorio.git
    cd tu_repositorio
    ```

2. **Configurar la base de datos**:
    Asegúrate de tener una base de datos configurada y actualiza el archivo `application.properties` con las credenciales correspondientes:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Compilar y ejecutar el proyecto**:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. La API estará disponible en `http://localhost:9000/`.

## Notas

- Asegúrate de tener correctamente configuradas las relaciones entre las tablas `Modelos` y `Marcas` con las claves foráneas.
- Para pruebas, puedes usar herramientas como **Postman** o **cURL** para interactuar con la API.

---
 
