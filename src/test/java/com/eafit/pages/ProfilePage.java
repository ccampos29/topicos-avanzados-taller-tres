package com.eafit.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Representa la página de perfil del usuario.
 * <p>
 * Proporciona métodos para realizar acciones relacionadas con la gestión del perfil,
 * tales como la eliminación de la cuenta.
 * </p>
 */
public class ProfilePage {

    private final WebDriver driver;
    private final By deleteAccountButton = By.xpath("//button[@id='submit' and text()='Delete Account']");
    private final By okModalButton = By.xpath("//button[@id='closeSmallModal-ok' and text()='OK']");

    /**
     * Constructor de la clase que inicializa el controlador de la página.
     *
     * @param driver el controlador WebDriver que maneja la página.
     */
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Elimina la cuenta del usuario actual desde la página de perfil.
     * <p>
     * Espera a que el botón "Delete Account" sea visible, hace clic en él,
     * confirma la acción en el modal y maneja la alerta del navegador.
     * </p>
     */
    public void deleteAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteAccountButton));

        WebElement deleteButton = driver.findElement(deleteAccountButton);
        deleteButton.click();

        WebElement okButton = driver.findElement(okModalButton);
        okButton.click();

        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

}
