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