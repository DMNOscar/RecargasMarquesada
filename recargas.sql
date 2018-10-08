DROP TABLE IF EXISTS recargasmarquesada;
CREATE TABLE IF NOT EXISTS recargasmarquesada;
USE recargasmarquesada;

-- Tabla que guarda los numeros activados (para Activachip)
CREATE TABLE activado
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cantidad INT UNSIGNED NOT NULL,
	folio VARCHAR(30) NOT NULL,
	idProceso VARCHAR(10) NOT NULL;
	fecha DATETIME NOT NULL DEFAULT now();
	estado BOOLEAN DEFAULT TRUE,
	numero_id INT UNSIGNED NOT NULL,
	FOREIGN KEY(numero_id) REFERENCES numero(id)
);

-- Tabla que guarda los tipos de permiso de acceso
CREATE TABLE permiso
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	activo BOOLEAN DEFAULT TRUE
);

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

-- Tabla que guarda las credenciales de todos los usuarios
CREATE TABLE credencial
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nick VARCHAR(20) UNIQUE NOT NULL,
	pass VARCHAR(100) NOT NULL
);

-- Tabla que crea y especifica el tipo de usuario
CREATE TABLE tipo_usuario
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	activo BOOLEAN DEFAULT TRUE
);

-- Tabla que guarda los registros de los clientes
CREATE TABLE informacion_usuario
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	direccion VARCHAR(150) NOT NULL,
	email VARCHAR(30) DEFAULT 'S/R',
	usuario_id INT UNSIGNED NOT NULL,
	tipoUsuario_id INT UNSIGNED NOT NULL,
	FOREIGN KEY(usuario_id) REFERENCES usuario(id),
	FOREIGN KEY(tipoUsuario_id) REFERENCES tipo_usuario(id)
);

-- Tabla que guarda los carrier de las compañias de recarga
CREATE TABLE carrier
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	activo BOOLEAN DEFAULT TRUE
);

-- Tabla que guarda las clave de los clientes
CREATE TABLE clave_usuario
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	numero VARCHAR(5) NOT NULL,
	activo BOOLEAN DEFAULT TRUE,
	usuario_id INT UNSIGNED NOT NULL,
	puntoVenta_id INT UNSIGNED NOT NULL,
	FOREIGN KEY(usuario_id) REFERENCES usuario(id),
	FOREIGN KEY(puntoVenta_id) REFERENCES punto_venta(id)
);

-- Tabla que guarda el tipo de transaccion (deposito, comision, etc)
CREATE TABLE tipo_transaccion
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	activo BOOLEAN DEFAULT TRUE
);

-- Tabla que guarda el tipo de concepto de la transaccion (general, activaciones, servicios, etc)
CREATE TABLE concepto_transaccion
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	activo BOOLEAN DEFAULT TRUE
);

-- Tabla que registra los movimientos de dinero
CREATE TABLE transaccion
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	monto DOUBLE NOT NULL,
);