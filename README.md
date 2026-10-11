# Sistema de Gestión de Biblioteca Escolar

Aplicación de escritorio desarrollada en **Java** para la Evaluación Final Transversal de **Desarrollo Orientado a Objetos II**.

## Características y Requisitos Técnicos
- **Patrón Arquitectónico:** MVC (Modelo - Vista - Controlador).
- **Patrones de Diseño:**
  - **Singleton:** Para la clase `DatabaseConnection`, garantizando una única conexión centralizada.
  - **DAO (Data Access Object):** Desacoplamiento de la persistencia para `LibroDAO`, `EstudianteDAO`, `UsuarioDAO` y `PrestamoDAO`.
- **Programación Orientada a Objetos:**
  - Clase abstracta `Persona` y clases derivadas (`Usuario`, `Estudiante`).
  - Polimorfismo mediante sobrescritura (`getDetalle()`) y sobrecarga de constructores.
  - Implementación de múltiples interfaces en `PrestamoDAO` (`ICrud<Prestamo>` e `IPrestamo`).
- **Concurrencia y Sincronización:**
  - Procesamiento asíncrono con `Thread` en el registro de préstamos para evitar bloqueos en la interfaz gráfica (`JFrame`).
  - Control de concurrencia y consistencia mediante métodos `synchronized` y transacciones SQL (`commit` / `rollback`) para evitar condiciones de carrera en el stock de libros.
- **Base de Datos:** MySQL mediante JDBC.

## Estructura del Proyecto
```
SistemaBiblioteca/
├── sql/
│   └── biblioteca.sql       # Script de creación de tablas y datos de prueba
├── src/main/java/
│   ├── config/              # Conexión Singleton a MySQL
│   ├── controllers/         # Controladores MVC
│   ├── dao/                 # Clases de acceso a datos (DAO)
│   ├── interfaces/          # Interfaces ICrud e IPrestamo
│   ├── models/              # Modelos y jerarquías POO
│   ├── views/               # Interfaces gráficas Swing (JFrame)
│   └── Main.java            # Punto de entrada de la aplicación
├── pom.xml                  # Dependencias Maven (MySQL Connector/J)
└── README.md
```

## Configuración y Ejecución
1. **Base de Datos:**
   - Ejecutar el script `sql/biblioteca.sql` en MySQL Server (`localhost:3306`).
2. **Ejecución en IntelliJ IDEA:**
   - Abrir el proyecto en IntelliJ IDEA.
   - Cargar dependencias de Maven.
   - Ejecutar la clase `Main.java`.
