-- Inserción de roles
INSERT INTO role (name) VALUES ('ROLE_COLLABORATOR'), ('ROLE_ADMIN'), ('ROLE_BOSS');

-- Inserción de proyectos
INSERT INTO proyectos (nombre, ubicacion, tipo_de_trabajo) VALUES
('Proyecto Mapa', 'Chile, Arauco, Los Notros 6542', 'Eléctrico'),
('Proyecto Alpha', 'Chile, Santiago, Avenida Providencia 123', 'Construcción'),
('Proyecto Beta', 'Chile, Valparaíso, Calle Bellavista 456', 'Hidráulico'),
('Proyecto Gamma', 'Chile, Concepción, Avenida O''Higgins 789', 'Mecánico'),
('Proyecto Delta', 'Chile, Antofagasta, Paseo del Mar 200', 'Civil');

-- Inserción de usuarios
INSERT INTO usuario (nombre, apellido, correo_electronico, rut, contraseña, estado, rol_id) VALUES
('Juan', 'Perez', 'juan.perez@example.com', '12345678-9', '$2a$10$dWMuGcjOCUi82IfSiGBHkugBwoKrhteZwaC2tZtRKBcNLIijAnswi', true, (SELECT id FROM role WHERE name = 'ROLE_COLLABORATOR')),
('Maria', 'Lopez', 'maria.lopez@example.com', '98765432-1', '$2a$10$dWMuGcjOCUi82IfSiGBHkugBwoKrhteZwaC2tZtRKBcNLIijAnswi', true, (SELECT id FROM role WHERE name = 'ROLE_ADMIN')),
('Carlos', 'Gomez', 'carlos.gomez@example.com', '11223344-5', '$2a$10$dWMuGcjOCUi82IfSiGBHkugBwoKrhteZwaC2tZtRKBcNLIijAnswi', true, (SELECT id FROM role WHERE name = 'ROLE_BOSS')),
('Pedro', 'Martinez', 'pedro.martinez@example.com', '55443322-6', '$2a$10$dWMuGcjOCUi82IfSiGBHkugBwoKrhteZwaC2tZtRKBcNLIijAnswi', true, (SELECT id FROM role WHERE name = 'ROLE_COLLABORATOR')),
('Ana', 'Garcia', 'ana.garcia@example.com', '77889900-4', '$2a$10$dWMuGcjOCUi82IfSiGBHkugBwoKrhteZwaC2tZtRKBcNLIijAnswi', true, (SELECT id FROM role WHERE name = 'ROLE_COLLABORATOR')),
('Laura', 'Rodriguez', 'laura.rodriguez@example.com', '99887766-2', '$2a$10$dWMuGcjOCUi82IfSiGBHkugBwoKrhteZwaC2tZtRKBcNLIijAnswi', true, (SELECT id FROM role WHERE name = 'ROLE_COLLABORATOR')),
('Gabriel', 'Sánchez', 'gabriel.sanchez@example.com', '22334455-7', '$2a$10$dWMuGcjOCUi82IfSiGBHkugBwoKrhteZwaC2tZtRKBcNLIijAnswi', true, (SELECT id FROM role WHERE name = 'ROLE_BOSS')),
('Sofía', 'Hernández', 'sofia.hernandez@example.com', '66778899-3', '$2a$10$dWMuGcjOCUi82IfSiGBHkugBwoKrhteZwaC2tZtRKBcNLIijAnswi', true, (SELECT id FROM role WHERE name = 'ROLE_BOSS'));

-- Inserción de colaboradores
INSERT INTO colaboradores (id, id_usuario, salario_bruto, estado_pago_adicional, fecha_ingreso, jefe_directo_id, proyecto_id) VALUES
((SELECT id FROM usuario WHERE rut = '12345678-9'), (SELECT id FROM usuario WHERE rut = '12345678-9'), 500000, true, '2020-01-15', (SELECT id FROM usuario WHERE rut = '11223344-5'), (SELECT id FROM proyectos WHERE nombre = 'Proyecto Mapa')),
((SELECT id FROM usuario WHERE rut = '55443322-6'), (SELECT id FROM usuario WHERE rut = '55443322-6'), 450000, true, '2019-09-10', (SELECT id FROM usuario WHERE rut = '11223344-5'), (SELECT id FROM proyectos WHERE nombre = 'Proyecto Beta')),
((SELECT id FROM usuario WHERE rut = '77889900-4'), (SELECT id FROM usuario WHERE rut = '77889900-4'), 550000, true, '2021-03-25', (SELECT id FROM usuario WHERE rut = '66778899-3'), (SELECT id FROM proyectos WHERE nombre = 'Proyecto Gamma')),
((SELECT id FROM usuario WHERE rut = '99887766-2'), (SELECT id FROM usuario WHERE rut = '99887766-2'), 480000, true, '2020-11-01', (SELECT id FROM usuario WHERE rut = '66778899-3'), (SELECT id FROM proyectos WHERE nombre = 'Proyecto Delta'));
