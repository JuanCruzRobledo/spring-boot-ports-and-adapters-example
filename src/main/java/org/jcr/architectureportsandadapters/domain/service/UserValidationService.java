package org.jcr.architectureportsandadapters.domain.service;

import org.jcr.architectureportsandadapters.domain.model.User;

/**
 * Servicio de validación para las reglas de negocio relacionadas al usuario.
 */
public class UserValidationService {

    /**
     * Valida si un usuario es válido según las reglas de negocio.
     *
     * @param user Usuario a validar.
     * @return true si el usuario es válido, false en caso contrario.
     */
    public boolean validate(User user) {
        // Implementar validaciones aquí
        return true;
    }
}
