DROP DATABASE IF EXISTS ultrachip;
CREATE DATABASE IF NOT EXISTS ultrachip;
USE ultrachip;

-- Tabla que guarda los tipos de permiso de acceso
CREATE TABLE permiso
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) UNIQUE NOT NULL,
	activo BOOLEAN DEFAULT TRUE
);

INSERT INTO permiso(nombre) VALUES ('Administrador'), ('Supervisor'), ('Vendedor');

-- Tabla que guarda los usuarios autorizados para el ingreso
CREATE TABLE usuario
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL,
	telefono VARCHAR(10) DEFAULT 'S/R',
	permiso_id INT UNSIGNED NOT NULL,
	activo BOOLEAN DEFAULT TRUE,
	FOREIGN KEY(permiso_id) REFERENCES permiso(id)
);

-- Tabla que guarda los carrier de las compañias de recarga
CREATE TABLE carrier
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) UNIQUE NOT NULL,
	activo BOOLEAN DEFAULT TRUE
);

INSERT INTO carrier(nombre) VALUES('TELCEL'), ('SERVICIOS TELCEL'), ('MOVISTAR'), ('AT&T'), ('UNEFON'), ('ALÓ'), ('VIRGIN'), ('TEST');

-- Tabla que define los tipos de recarga que se hace (granel, activación, portabilidad, etc)
CREATE TABLE tipo_recarga
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) UNIQUE NOT NULL,
	activo BOOLEAN DEFAULT TRUE
);

INSERT INTO tipo_recarga(nombre) VALUES ('Activación'), ('Portabilidad'), ('Granel');

-- Tabla que registra los numeros en el sistema
CREATE TABLE numero
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	folio VARCHAR(40) UNIQUE NOT NULL,
	digitos VARCHAR(10) UNIQUE NOT NULL,
	iccid VARCHAR(30) UNIQUE NOT NULL,
	fecha DATETIME DEFAULT now(),
	monto INT UNSIGNED NOT NULL,
	tipoRecarga_id INT UNSIGNED NOT NULL,
	carrier_id INT UNSIGNED NOT NULL,
	usuario_id INT UNSIGNED NOT NULL,
	FOREIGN KEY(tipoRecarga_id) REFERENCES tipo_recarga(id),
	FOREIGN KEY(carrier_id) REFERENCES carrier(id),
	FOREIGN KEY(usuario_id) REFERENCES usuario(id)
);

-- Tabla que guarda los numeros activados (para Activachip)
CREATE TABLE recarga
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	monto INT UNSIGNED NOT NULL,
	folio VARCHAR(30) UNIQUE NOT NULL,
	idProceso VARCHAR(10) NOT NULL,
	fecha DATETIME NOT NULL DEFAULT now(),
	estado BOOLEAN DEFAULT TRUE,
	numero_id INT UNSIGNED UNIQUE NOT NULL,
	usuario_id INT UNSIGNED NOT NULL,
	FOREIGN KEY(numero_id) REFERENCES numero(id),
	FOREIGN KEY(usuario_id) REFERENCES usuario(id)
);

-- Tabla que guarda las credenciales de todos los usuarios
CREATE TABLE credencial
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nick VARCHAR(20) UNIQUE NOT NULL,
	pass VARCHAR(100) NOT NULL,
    usuario_id INT UNSIGNED NOT NULL,
    FOREIGN KEY(usuario_id) REFERENCES usuario(id)
);

-- Tabla que crea y especifica el tipo de usuario
CREATE TABLE tipo_usuario
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) UNIQUE NOT NULL,
	activo BOOLEAN DEFAULT TRUE
);

-- Tabla que guarda los registros de los clientes
CREATE TABLE informacion_usuario
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	direccion VARCHAR(150) NOT NULL,
	email VARCHAR(30) DEFAULT 'S/R',
	usuario_id INT UNSIGNED UNIQUE NOT NULL,
	-- tipoUsuario_id INT UNSIGNED NOT NULL,
	-- FOREIGN KEY(tipoUsuario_id) REFERENCES tipo_usuario(id),
    FOREIGN KEY(usuario_id) REFERENCES usuario(id)
);

-- Tabla que registra un area de venta (agrupacion que contiene los que permiten realizar recargas o servicios)
CREATE TABLE area_venta
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL,
	clave VARCHAR(10) UNIQUE NOT NULL,
	activo BOOLEAN DEFAULT TRUE
);

-- Tabla que guarda las clave de los clientes
CREATE TABLE clave_venta
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	numero VARCHAR(5) NOT NULL,
	activo BOOLEAN DEFAULT TRUE,
	usuario_id INT UNSIGNED UNIQUE NOT NULL,
	areaVenta_id INT UNSIGNED NOT NULL,
	FOREIGN KEY(usuario_id) REFERENCES usuario(id),
	FOREIGN KEY(areaVenta_id) REFERENCES area_venta(id)
);

-- Tabla que guarda el tipo de transaccion (deposito, comision, etc)
CREATE TABLE tipo_transaccion
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) UNIQUE NOT NULL,
	activo BOOLEAN DEFAULT TRUE
);

INSERT INTO tipo_transaccion(nombre) VALUES('Depósito'), ('Comisión'), ('Gasto');

-- Tabla que guarda el tipo de concepto de la transaccion (general, activaciones, servicios, etc)
CREATE TABLE concepto_transaccion
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) UNIQUE NOT NULL,
	activo BOOLEAN DEFAULT TRUE
);

INSERT INTO concepto_transaccion(nombre) VALUES('General'), ('Activaciones'), ('Saldo'), ('Servicios');

-- Tabla que registra los movimientos de dinero
CREATE TABLE transaccion
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	monto DOUBLE NOT NULL,
	fecha DATETIME DEFAULT now(),
	tipoTransaccion_id INT UNSIGNED NOT NULL,
	conceptoTransaccion_id INT UNSIGNED NOT NULL,
	FOREIGN KEY(tipoTransaccion_id) REFERENCES tipo_transaccion(id),
	FOREIGN KEY(conceptoTransaccion_id) REFERENCES concepto_transaccion(id)
);

-- Tabla que agrega las excepciones de los numeros de recarga
CREATE TABLE excepcion
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	activo BOOLEAN DEFAULT TRUE,
	numero_id INT UNSIGNED UNIQUE NOT NULL,
	FOREIGN KEY(numero_id) REFERENCES numero(id)
);

-- Tabla que agrega el historial de recargas de las excepciones
CREATE TABLE excepcion_historial
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	monto INT UNSIGNED NOT NULL,
	fecha DATETIME DEFAULT now(),
	excepcion_id INT UNSIGNED NOT NULL,
	FOREIGN KEY(excepcion_id) REFERENCES excepcion(id)
);

-- Tabla que registra un historial de los intentos fallidos de recarga
CREATE TABLE error_historial
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	fecha DATETIME DEFAULT now(),
	topUpID VARCHAR(10) NOT NULL,
	errorCode VARCHAR(10) NOT NULL,
	numero_id INT UNSIGNED NOT NULL,
	FOREIGN KEY(numero_id) REFERENCES numero(id)
);

-- Tabla que registra los montos de recargas permitidos
CREATE TABLE monto_carrier
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	monto INT UNSIGNED NOT NULL,
	carrier_id INT UNSIGNED NOT NULL,
	FOREIGN KEY(carrier_id) REFERENCES carrier(id)
);

-- Tabla que registra los detalles de carrier a guardar por defecto
CREATE TABLE recarga_carrier
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	monto INT unsigned NOT NULL,
	carrier_id INT UNSIGNED UNIQUE NOT NULL,
	FOREIGN KEY(carrier_id) REFERENCES carrier(id)
);