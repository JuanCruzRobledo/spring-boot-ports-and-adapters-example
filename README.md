# 🏦 **Ports and Adapters Architecture**

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen?style=for-the-badge&logo=spring)
![Gradle](https://img.shields.io/badge/Gradle-8.0-blue?style=for-the-badge&logo=gradle)
![Architecture](https://img.shields.io/badge/Architecture-Hexagonal-purple?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

### 🌟 Una implementación profesional de Arquitectura Hexagonal en Java

[Ver Documentación Completa](#-documentación) • [Arquitectura Detallada](ARQUITECTURA_CONEXIONES.md) • [Empezar](#-inicio-rápido)

</div>

---

## 📚 **Índice**

- [🌟 Descripción](#-descripción-del-proyecto)
- [🎯 Objetivo](#-objetivo-de-la-arquitectura)
- [🏗️ Estructura](#-estructura-del-proyecto)
- [🚀 Inicio Rápido](#-inicio-rápido)
- [💬 API Endpoints](#-api-endpoints)
- [🛠️ Stack Tecnológico](#-stack-tecnológico)
- [📝 Documentación](#-documentación)
- [🧑‍💻 Contribuir](#-contribuir)

---

## 🌟 **Descripción del Proyecto**

Este proyecto es una **implementación profesional** de la arquitectura de Puertos y Adaptadores (también conocida como **arquitectura Hexagonal**) en Java utilizando Spring Boot. 

🎯 **Enfoque principal**: Proporcionar un marco modular que facilite el mantenimiento, escalabilidad y testing de aplicaciones empresariales.

### ✨ **Características Principales**

- ✅ **Arquitectura Hexagonal** completa y bien estructurada
- ✅ **Separación de responsabilidades** clara entre capas
- ✅ **Inyección de dependencias** con Spring
- ✅ **Manejo de errores** centralizado y robusto
- ✅ **Transformación de datos** automática con MapStruct
- ✅ **Testing** fácil con interfaces mockeable
- ✅ **Documentación** completa y comentarios en código

---

## 🎯 **Objetivo de la Arquitectura**

La **arquitectura Hexagonal** busca:

🔄 **Desacoplar** el núcleo de negocio de la infraestructura  
🔌 **Aislar** la lógica de negocio de tecnologías específicas  
🔧 **Facilitar** el testing mediante interfaces  
🚀 **Permitir** cambios de tecnología sin afectar el dominio  
📚 **Mantener** el código limpio y legible  

---

## 🏗️ **Estructura del Proyecto**

```
src/main/java/org/jcr/architectureportsandadapters/
├── 💼 domain/                  # Lógica de negocio pura
│   ├── model/
│   └── service/
├── ⚙️ application/            # Casos de uso y orquestación
│   ├── service/
│   └── mapper/
├── 🔌 port/                   # Interfaces (Contratos)
│   ├── in/                  # Puertos de entrada
│   └── out/                 # Puertos de salida
├── 🏧 infrastructure/        # Implementaciones concretas
│   └── persistence/
└── 📦 shared/                 # Utilidades compartidas
    ├── exception/
    └── response/
```

| Capa | Responsabilidad | Tecnologías |
|------|----------------|-------------|
| **Domain** | Lógica de negocio pura | Java Records, Validaciones |
| **Application** | Casos de uso, orquestación | Spring Services, MapStruct |
| **Ports** | Contratos de entrada/salida | Java Interfaces |
| **Infrastructure** | Persistencia, APIs externas | Spring Data JPA, H2 |

---

## 🚀 **Inicio Rápido**

### 📌 **Prerrequisitos**

- **Java 17** o superior
- **Gradle 8.0** o superior
- **IDE** (IntelliJ IDEA, VS Code, Eclipse)

### 🛠️ **Instalación**

```bash
# Clonar el repositorio
git clone <repository-url>
cd ArchitecturePortsAndAdapters

# Ejecutar la aplicación
./gradlew bootRun
```

### 🌍 **Acceso**

La aplicación estará disponible en:
- **API**: http://localhost:8080/api/v1
- **H2 Console**: http://localhost:8080/h2-console

---

## 💬 **API Endpoints**

### 👤 **Usuarios**

| Método | Endpoint | Descripción | Ejemplo |
|--------|----------|-------------|----------|
| `POST` | `/api/v1/users` | Crear usuario | [Ver ejemplo](#crear-usuario) |
| `GET` | `/api/v1/users/{id}` | Obtener usuario por ID | [Ver ejemplo](#obtener-usuario) |

#### Crear Usuario
```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan",
    "apellido": "Pérez"
  }'
```

**Respuesta:**
```json
{
  "success": true,
  "message": "Usuario creado exitosamente",
  "data": {
    "id": 1,
    "nombre": "Juan",
    "apellido": "Pérez"
  },
  "timestamp": 1672531200000
}
```

#### Obtener Usuario
```bash
curl http://localhost:8080/api/v1/users/1
```

---

## 🛠️ **Stack Tecnológico**

### 🏢 **Backend**

| Tecnología | Versión | Propósito |
|-------------|---------|----------|
| **Java** | 17 | Lenguaje de programación |
| **Spring Boot** | 3.5.3 | Framework principal |
| **Spring Data JPA** | 3.5.3 | Persistencia de datos |
| **H2 Database** | 2.2.224 | Base de datos en memoria |
| **MapStruct** | 1.6.3 | Mapeo de objetos |
| **Lombok** | 1.18.30 | Reducción de boilerplate |
| **Gradle** | 8.0 | Herramienta de construcción |

### 🎨 **Patrones y Principios**

- ✅ **Hexagonal Architecture** (Ports & Adapters)
- ✅ **SOLID Principles**
- ✅ **Clean Architecture**
- ✅ **Dependency Injection**
- ✅ **Repository Pattern**
- ✅ **DTO Pattern**

---

## 📝 **Documentación**

### 📚 **Documentación Principal**

| Documento | Descripción | Enlace |
|-----------|-------------|--------|
| **Arquitectura Detallada** | Conexiones entre capas, diagramas y flujos | [🔗 ARQUITECTURA_CONEXIONES.md](ARQUITECTURA_CONEXIONES.md) |
| **Código Comentado** | Explicaciones en el código fuente | 💻 Archivos .java |
| **Configuración** | Propiedades y configuración | 📄 application.properties |

### 🕰️ **Contenido de la Documentación Arquitectónica**

- 📊 **Diagramas** de arquitectura hexagonal
- 🔄 **Flujos de datos** detallados
- 🔌 **Conexiones** entre capas
- 💻 **Ejemplos de código** paso a paso
- ⚙️ **Configuración** de Spring
- 🧑‍💻 **Principios SOLID** aplicados

---

## 🧑‍💻 **Contribuir**

¡Las contribuciones son bienvenidas! Para contribuir:

1. 🍴 Fork el proyecto
2. 🌱 Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. 📝 Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. 📤 Push a la rama (`git push origin feature/AmazingFeature`)
5. 📩 Abre un Pull Request

---

<div align="center">

### ✨ **¡Gracias por usar Ports and Adapters Architecture!** ✨

**📚 Más información**: [ARQUITECTURA_CONEXIONES.md](ARQUITECTURA_CONEXIONES.md)

---

🚀 **Creado con** ❤️ **por el Juan Cruz Robledo**

![Footer](https://img.shields.io/badge/Made%20with-❤️-red?style=for-the-badge)

</div>

