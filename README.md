# Ports and Adapters Architecture

## Descripción del Proyecto

Este proyecto es una implementación de la arquitectura de Puertos y Adaptadores (también conocida como arquitectura Hexagonal) en Java utilizando Spring Boot. El enfoque principal de este proyecto es proporcionar un marco modular que facilite el mantenimiento y la escalabilidad.

## Objetivo de la Arquitectura

El objetivo de la arquitectura Hexagonal es desacoplar el núcleo de negocio de las preocupaciones de infraestructura, permitiendo que los cambios en las capas de infraestructura no afecten la lógica de negocio central. Esto se logra mediante el uso de puertos e interfaces, que definen contratos que deben cumplir las implementaciones de infraestructura.

## Estructura del Proyecto

El proyecto está organizado de la siguiente manera:

- **Domain**: Contiene las entidades y servicios de lógica de negocio pura.
- **Application**: Encapsula la lógica de aplicación y es donde residen los casos de uso.
- **Port**: Define interfaces para las operaciones de entrada y salida.
- **Infrastructure**: Contiene las implementaciones concretas de los puertos.

## Cómo Ejecutar el Proyecto

1. Asegúrate de tener **Java 17** y **Maven** o **Gradle** instalados.
2. Ejecuta el comando `./gradlew bootRun` en el directorio raíz del proyecto para iniciar la aplicación.

## Ejemplos de Uso

### Endpoints

- **POST /api/v1/users**: Crea un nuevo usuario.
- **GET /api/v1/users/{id}**: Obtiene información del usuario por ID.

## Stack Tecnológico Utilizado

- **Spring Boot**: Framework principal de la aplicación.
- **Spring Data JPA**: Acceso y persistencia de datos.
- **H2 Database**: Base de datos en memoria para desarrollo y testing.
- **MapStruct**: Herramienta de mapeo de DTOs y entidades.
- **Lombok**: Reducción de boilerplate de código.

---
Este proyecto proporciona una base robusta para construir aplicaciones escalables y mantenibles siguiendo los principios de diseño limpio.

