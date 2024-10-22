package com.eafit.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

/**
 * Clase utilitaria para configurar la especificación de solicitudes HTTP
 * utilizando Rest Assured.
 * <p>
 * Proporciona una configuración base que incluye la URL base y
 * los encabezados comunes necesarios para realizar solicitudes a la API.
 * </p>
 */
public class ApiClient {

    /**
     * Devuelve la especificación de solicitud configurada para interactuar
     * con la API.
     * <p>
     * Configura la URL base y el encabezado "Content-Type" como "application/json".
     * </p>
     *
     * @return la especificación de solicitud configurada para Rest Assured.
     */
    public static RequestSpecification getRequestSpecification() {
        return RestAssured
                .given()
                .baseUri("https://demoqa.com")
                .header("Content-Type", "application/json");
    }
}
