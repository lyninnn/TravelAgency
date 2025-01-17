-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS travelagency;

-- Seleccionar la base de datos
USE travelagency;

-- Crear la tabla viajes
CREATE TABLE IF NOT EXISTS viajes (
    viajeId INT(11) AUTO_INCREMENT PRIMARY KEY,  -- Identificador único para cada viaje
    ciudad VARCHAR(255),  -- Ciudad del viaje
    pais VARCHAR(255),  -- País del viaje
    precio INT(11)  -- Precio del viaje
);

-- Crear la tabla clientes
CREATE TABLE IF NOT EXISTS clientes (
    clienteID INT(11) AUTO_INCREMENT PRIMARY KEY,  -- Identificador único para cada cliente
    nombre VARCHAR(255),  -- Nombre del cliente
    nacionalidad VARCHAR(255),  -- Nacionalidad del cliente
    fechaRegistro DATE,  -- Fecha de registro del cliente
    viajeId INT(11),  -- ID del viaje asignado al cliente
    FOREIGN KEY (viajeId) REFERENCES viajes(viajeId)  -- Relación con la tabla viajes
);
-- Insertar algunos datos de ejemplo en la tabla viajes
INSERT INTO viajes (ciudad, pais, precio) VALUES
('Paris', 'Francia', 1500),
('Roma', 'Italia', 1200),
('Londres', 'Reino Unido', 1000);

-- Insertar algunos datos de ejemplo en la tabla clientes
INSERT INTO clientes (nombre, nacionalidad, fechaRegistro, viajeId) VALUES
('Juan Pérez', 'Argentina', '2025-01-10', 1),
('Maria López', 'España', '2025-01-15', 2),
('Carlos García', 'México', '2025-01-16', 3);