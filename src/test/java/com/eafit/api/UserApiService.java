package com.eafit.api;

import io.restassured.response.Response;

/**
 * Servicio para interactuar con la API de usuarios.
 * <p>
 * Proporciona métodos para realizar operaciones relacionadas con los usuarios,
 * como la creación de usuarios.
 * </p>
 */
public class UserApiService {

    /**
     * Crea un nuevo usuario en la API con el nombre de usuario y contraseña
     * especificados.
     * <p>
     * Envía una solicitud POST a la ruta "/Account/v1/User" con los datos del
     * usuario en el cuerpo de la solicitud.
     * La solicitud espera que la API devuelva un código de estado 201 para indicar
     * que la creación del usuario fue exitosa.
     * </p>
     *
     * @param username el nombre de usuario para el nuevo usuario.
     * @param password la contraseña para el nuevo usuario.
     * @return el objeto {@link Response} que contiene la respuesta de la API.
     * @throws AssertionError si el código de estado de la respuesta no es 201.
     */
    public Response createUser(String username, String password) {
        String body = """
            {
                "userName": "%s",
                "password": "%s"
            }
        """;
        return ApiClient.getRequestSpecification()
                .body(String.format(body, username, password))
                .when()
                .post("/Account/v1/User")
                .then()
                .statusCode(201)
                .extract().response();
    }

}
