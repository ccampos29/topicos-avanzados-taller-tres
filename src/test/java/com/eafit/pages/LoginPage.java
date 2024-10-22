package com.eafit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Representa la página de inicio de sesión de la aplicación.
 * <p>
 * Proporciona métodos para realizar acciones relacionadas con el inicio de sesión,
 * incluyendo autenticación y manejo de errores.
 * </p>
 */
public class LoginPage {
    private final WebDriver driver;
    private final By usernameField = By.xpath("//*[@id='userName']");
    private final By passwordField = By.xpath("//*[@id='password']");
    private final By loginByButton = By.xpath("//button[@id='login' and text()='Login']");
    private final By labelByError = By.xpath("//p[@id='name' and text()='Invalid username or password!']");

    /**
     * Constructor de la clase que inicializa el controlador de la página.
     *
     * @param driver el controlador WebDriver que maneja la página.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Realiza el inicio de sesión con el nombre de usuario y la contraseña proporcionados.
     * <p>
     * Envía las credenciales al formulario de inicio de sesión y hace clic en el botón de inicio de sesión.
     * </p>
     *
     * @param username el nombre de usuario a utilizar para la autenticación.
     * @param password la contraseña a utilizar para la autenticación.
     */
    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        WebElement loginButton = driver.findElement(loginByButton);
        loginButton.click();
    }

    /**
     * Intenta iniciar sesión y captura el mensaje de error en caso de credenciales inválidas.
     * <p>
     * Realiza el inicio de sesión con las credenciales proporcionadas y espera a que se muestre el mensaje
     * de error en caso de que las credenciales no sean correctas.
     * </p>
     *
     * @param username el nombre de usuario a utilizar para la autenticación.
     * @param password la contraseña a utilizar para la autenticación.
     * @return el mensaje de error mostrado en la página en caso de fallo en el inicio de sesión.
     */
    public String loginWithErrors(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.login(username, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(labelByError));
        return driver.findElement(labelByError).getText();
    }
}
