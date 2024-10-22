package com.eafit.tests;

import com.eafit.api.UserApiService;
import com.eafit.pages.LoginPage;
import com.eafit.pages.ProfilePage;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Clase de prueba para validar la creación, inicio de sesión y eliminación
 * de un usuario en la aplicación web.
 * <p>
 * Utiliza Selenium WebDriver para la interacción con la interfaz de usuario
 * y Rest Assured para la comunicación con la API.
 * </p>
 */
class UserTest {

    WebDriver driver;
    UserApiService userApiService;
    String username;
    String password;

    /**
     * Configuración inicial de las pruebas.
     * <p>
     * Se ejecuta antes de cada prueba, inicializando el nombre de usuario,
     * la contraseña, el navegador y el servicio de la API.
     * </p>
     */
    @BeforeEach
    public void setUp() {
        Long time = new Date().getTime();
        username = String.format("usereafit%s", time);
        password = String.format("Usereafit%s!", time);
        initializeDriver();
        userApiService = new UserApiService();
    }

    /**
     * Prueba que valida la creación de un usuario, su inicio de sesión
     * y la eliminación del usuario.
     * <p>
     * Verifica que el usuario sea creado correctamente, luego realiza el inicio
     * de sesión en la aplicación, elimina la cuenta del usuario, y finalmente
     * intenta autenticar al usuario eliminado, comprobando que se muestre un mensaje
     * de error adecuado.
     * </p>
     */
    @Test
    void testUserCreationAndLogin() {
        createUser();
        performLogin();
        deleteUser();

        // Intentar autenticación nuevamente
        LoginPage loginPage = new LoginPage(driver);
        String errorMessage = loginPage.loginWithErrors(username, password);
        assertTrue(errorMessage.contains("Invalid username or password"), "Verificación fallida.");
    }

    /**
     * Limpieza después de cada prueba.
     * <p>
     * Se ejecuta después de cada prueba, cerrando el navegador para liberar
     * recursos.
     * </p>
     */
    @AfterEach
    void finishTest() {
        driver.quit();
    }

    /**
     * Crea un usuario mediante la API y verifica que la creación sea exitosa.
     * <p>
     * Utiliza el servicio de API para enviar la solicitud de creación de usuario
     * y verifica que el código de estado devuelto sea 201.
     * </p>
     *
     * @throws AssertionError si el código de estado no es 201.
     */
    private void createUser() {
        Response response = userApiService.createUser(username, password);
        assertEquals(201, response.getStatusCode(), "Falló la creación del usuario");
    }

    /**
     * Realiza el inicio de sesión en la aplicación con el usuario creado.
     * <p>
     * Utiliza la página de inicio de sesión para autenticar al usuario
     * con las credenciales previamente generadas.
     * </p>
     */
    private void performLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    /**
     * Elimina la cuenta del usuario desde la página de perfil.
     * <p>
     * Accede a la página de perfil y realiza la eliminación de la cuenta.
     * </p>
     */
    private void deleteUser() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.deleteAccount();
    }

    /**
     * Inicializa el controlador WebDriver y configura el navegador.
     * <p>
     * Abre Firefox, navega a la página de inicio de sesión, ajusta el zoom de la página
     * y maximiza la ventana del navegador.
     * </p>
     */
    private void initializeDriver() {
        driver = new FirefoxDriver();
        driver.get("https://demoqa.com/login");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.body.style.zoom='60%'");
        driver.manage().window().maximize();
    }

}
