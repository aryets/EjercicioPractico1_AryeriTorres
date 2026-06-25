-- =====================================
-- 1) CREAR USUARIO
-- =====================================
DROP USER IF EXISTS 'usuario_caso_prac2co_01'@'localhost';
CREATE USER 'usuario_caso_prac2co_01'@'localhost'
IDENTIFIED BY '123456';

-- =====================================
-- 2) CREAR BASE DE DATOS
-- =====================================
DROP DATABASE IF EXISTS gimnasio_fitness;
CREATE DATABASE gimnasio_fitness;
GRANT ALL PRIVILEGES ON gimnasio_fitness.*
TO 'usuario_caso_prac2co_01'@'localhost';
FLUSH PRIVILEGES;
USE gimnasio_fitness;

-- =====================================
-- 3) TABLA CATEGORIA
-- =====================================
CREATE TABLE categoria (
id INT AUTO_INCREMENT PRIMARY KEY, nombre
VARCHAR(100) NOT NULL
);

-- =====================================
-- 4) TABLA SERVICIO
-- =====================================
CREATE TABLE servicio ( id INT
AUTO_INCREMENT PRIMARY KEY, nombre
VARCHAR(100) NOT NULL, precio DECIMAL(8,2)
NOT NULL, categoria_id INT NOT NULL,
FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

-- =====================================
-- 5) TABLA RESERVA
-- =====================================
CREATE TABLE reserva ( id INT
AUTO_INCREMENT PRIMARY KEY,
nombre_cliente VARCHAR(100) NOT NULL,
fecha DATE NOT NULL, servicio_id INT NOT
NULL,
FOREIGN KEY (servicio_id) REFERENCES servicio(id)
);

-- =====================================
-- 6) DATOS DE PRUEBA
-- =====================================
-- Categorías
INSERT INTO categoria (nombre) VALUES
('Entrenamiento Funcional'),
('Cardio'),
('Fuerza'),
('Flexibilidad');
-- Servicios / Clases
INSERT INTO servicio (nombre, precio, categoria_id) VALUES
('Clase de CrossFit', 8000.00, 1),
('HIIT Intensivo', 7000.00, 2),
('Entrenamiento con Pesas', 9000.00, 3),
('Clase de Yoga', 6500.00, 4),
('Pilates', 7000.00, 4),
('Bootcamp Fitness', 8500.00, 1);
-- Reservas
INSERT INTO reserva (nombre_cliente, fecha, servicio_id) VALUES
('Ana Rodríguez', '2026-03-10', 1),
('María López', '2026-03-12', 4),
('Carlos Sánchez', '2026-03-15', 3);
-- Fin del Script