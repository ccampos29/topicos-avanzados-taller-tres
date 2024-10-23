# TOPICOS AVANZADOS DE SOFTWARE - TALLER # 3

Este proyecto está implementado en Java 17 y tiene como objetivo realizar una prueba automática que valida el siguiente flujo:

1. Crear un usuario a través de la API en [DemoQA Swagger](https://demoqa.com/swagger/#/).
2. Verificar que el usuario se haya creado correctamente mediante una validación `assert`.
3. Iniciar sesión con el usuario creado en la página de [DemoQA Login](https://demoqa.com/login).
4. Una vez autenticado, borrar el usuario desde la página de perfil.
5. Intentar iniciar sesión nuevamente con el mismo usuario y validar que el mensaje de error esperado sea visible, confirmando que el usuario ya no existe.

## Integrantes
- Andres Felipe Olis
- Carlos Alberto Campos

## Requisitos previos

- Java 17
- Gradle

## Estructura del Proyecto

El proyecto sigue los principios de organización de código para pruebas automatizadas (FIRST: Fast, Independent, Repeatable, Self-validating, Timely).

## Ejecución de los Tests

1. Clona el repositorio en tu máquina local.
2. Navega hasta la raíz del proyecto.
3. Ejecuta los siguientes comandos para compilar y ejecutar las pruebas:

```bash
./gradlew clean test
```
Los resultados de las pruebas se mostrarán en la consola, y también podrás acceder a un reporte detallado en la carpeta build/reports/tests/test.

## Configuración adicional
Para evitar problemas con la ejecución del test debido a anuncios en la página, se utiliza la siguiente línea de código:

```
JavascriptExecutor jse = (JavascriptExecutor) driver;
jse.executeScript("document.body.style.zoom='60%'");
```

Esto permite manejar el tamaño de la página, reduciendo el zoom al 60% y evitando que los anuncios interfieran en la correcta ejecución de las pruebas.

## Dependencias
El proyecto utiliza las siguientes dependencias en Gradle:

- [Rest Assured](https://rest-assured.io/) para la automatización de pruebas API.
- [Selenium WebDriver](https://www.selenium.dev/documentation/) para la automatización de la interacción con la interfaz web.


# Colección de Postman: TOPICS_AVANZADOS_TALLER_3

Esta colección de Postman está diseñada para interactuar con la API de la NASA, específicamente con los servicios relacionados con la imagen del día (APOD) y los objetos cercanos a la Tierra (NEO). La colección incluye varias solicitudes con scripts de pre-solicitud y pruebas para validar las respuestas.

## Estructura de la Colección

### 1. Apod

- **Descripción:** Solicitud para obtener la imagen del día de la NASA.
- **Método:** GET
- **URL:** `{{BASE_URL}}/planetary/apod?count=10`
- **Scripts de Pre-solicitud:**
  - Validación de formato de fecha.
  - Configuración de parámetros por defecto.
  - Inserción de la clave de API si no está presente.
- **Scripts de Prueba:**
  - Verificación del código de estado.
  - Validación de la cantidad de resultados.
  - Comprobación de formato de fechas y campos no vacíos.

### 2. Neo - Feed

- **Descripción:** Solicitud para obtener el feed de objetos cercanos a la Tierra.
- **Método:** GET
- **URL:** `{{BASE_URL}}/neo/rest/v1/feed?start_date=2024-01-01&end_date=2024-01-08`
- **Scripts de Pre-solicitud:**
  - Validación de formato de fecha y rango de fechas.
  - Inserción de la clave de API si no está presente.
- **Scripts de Prueba:**
  - Verificación del código de estado y formato JSON.
  - Validación de campos en la respuesta y extracción de IDs de referencia.

### 3. Neo - Lookup

- **Descripción:** Solicitud para obtener detalles de un objeto cercano a la Tierra específico.
- **Método:** GET
- **URL Dinámica:** Basada en el ID de referencia actual.
- **Scripts de Pre-solicitud:**
  - Obtención y manejo de IDs de referencia desde variables de entorno.
  - Construcción de la URL de solicitud dinámica.
- **Scripts de Prueba:**
  - Validaciones de respuesta esperadas.

### 4. Neo - Browse

- **Descripción:** Solicitud para explorar objetos cercanos a la Tierra.
- **Método:** GET
- **URL:** `{{BASE_URL}}/neo/rest/v1/neo/browse`
- **Scripts de Pre-solicitud:**
  - Validación del parámetro `api_key` y configuración según el entorno.
- **Scripts de Prueba:**
  - Verificación de que el código de estado es 200.

### 5. Earth - Imagery

- **Descripción:** Solicitud para obtener imágenes de la Tierra en una ubicación y fecha específicas.
- **Método:** GET
- **URL:** `{{BASE_URL}}/planetary/earth/imagery?lon=-75.578485&lat=6.200202&date=2018-01-01&dim=0.15`
- **Parámetros de Consulta:**
  - `lon`: Longitud de la ubicación.
  - `lat`: Latitud de la ubicación.
  - `date`: Fecha para la cual se desea la imagen.
  - `dim`: Dimensión de la imagen.
- **Scripts de Pre-solicitud:**
  - Validación del parámetro `api_key` y configuración según el entorno.

### 6. Earth - Assets

- **Descripción:** Solicitud para obtener los activos de la Tierra en una ubicación y fecha específicas.
- **Método:** GET
- **URL:** `{{BASE_URL}}/planetary/earth/assets?lon=100.75&lat=1.5&date=2014-02-01&dim=0.15`
- **Parámetros de Consulta:**
  - `lon`: Longitud de la ubicación.
  - `lat`: Latitud de la ubicación.
  - `date`: Fecha para la cual se desean los activos.
  - `dim`: Dimensión de la imagen.
- **Scripts de Pre-solicitud:**
  - Validación del parámetro `api_key` y configuración según el entorno.
- **Scripts de Prueba:**
  - Verificación de que la respuesta contiene los campos esperados como `date`, `id`, `resource`, `service_version`, y `url`.
  - Validación de que la URL es una cadena válida y no vacía.
  - Almacenamiento de la URL de la imagen en una variable de entorno.

## Uso

1. **Configuración del Entorno:**
   - Asegúrate de tener configurado el entorno `ENV_TESTS` con las variables necesarias, como `BASE_URL` y `API_KEY`.

2. **Ejecución de Solicitudes:**
   - Las solicitudes están diseñadas para ejecutarse en secuencia, asegurando que las dependencias de datos (como los IDs de NEO) se manejen correctamente.

3. **Validación de Respuestas:**
   - Los scripts de prueba están incluidos para validar automáticamente las respuestas de la API y asegurar que cumplen con los criterios esperados.