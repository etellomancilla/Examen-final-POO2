CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    rut VARCHAR(20),
    correo VARCHAR(100),
    contraseña VARCHAR(100),
    rol VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    rut VARCHAR(20),
    curso VARCHAR(50),
    correo VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS libros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200),
    autor VARCHAR(100),
    isbn VARCHAR(50),
    editorial VARCHAR(100),
    stock INT,
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id)
);

CREATE TABLE IF NOT EXISTS prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT,
    id_libro INT,
    fecha_prestamo DATE,
    fecha_devolucion DATE,
    devuelto BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id),
    FOREIGN KEY (id_libro) REFERENCES libros(id)
);

-- Datos de prueba
INSERT IGNORE INTO categorias (id, nombre) VALUES 
(1, 'Novela'), (2, 'Ciencia'), (3, 'Historia');

INSERT IGNORE INTO usuarios (id, nombre, rut, correo, contraseña, rol) VALUES
(1, 'Enrique Tello', '20.123.456-7', 'biblioteca@colegio.cl', 'admin123', 'Bibliotecario');

INSERT IGNORE INTO estudiantes (id, nombre, rut, curso, correo) VALUES
(1, 'Juan Pérez', '22.345.678-9', '4° Medio A', 'juan.perez@alumnos.cl'),
(2, 'Maria Gomez', '22.987.654-3', '3° Medio B', 'maria.gomez@alumnos.cl');

INSERT IGNORE INTO libros (id, titulo, autor, isbn, editorial, stock, id_categoria) VALUES
(1, 'Cien Años de Soledad', 'Gabriel García Márquez', '978-0307474728', 'Sudamericana', 5, 1),
(2, 'El Principito', 'Antoine de Saint-Exupéry', '978-0156013987', 'Reynal & Hitchcock', 8, 1),
(3, 'Breve historia del tiempo', 'Stephen Hawking', '978-8418045615', 'Crítica', 3, 2);
