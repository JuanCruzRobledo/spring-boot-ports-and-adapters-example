package org.jcr.architectureportsandadapters.infraestructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA que representa un usuario en la base de datos.
 * Utiliza Lombok para generar constructores y builder.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity(name = "usuarios")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
}
