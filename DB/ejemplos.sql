USE ultrachip;

INSERT INTO usuario(nombre, permiso_id) VALUES ('Javier Serrano', 1);
INSERT INTO usuario(nombre, permiso_id) VALUES ('Adriana Molina', 2);
INSERT INTO usuario(nombre, permiso_id) VALUES ('Jesús Paredes', 3);
INSERT INTO usuario(nombre, permiso_id) VALUES ('Melisa Cornejo', 3);
INSERT INTO usuario(nombre, permiso_id) VALUES ('Oscar Martínez', 2);

INSERT INTO credencial(nick, pass, usuario_id) VALUES ('javiersl', 'rz/rcJKsrvu9Y4rnqaob8OwUYZDRt+5aU/LOiTCays0=', 1);
INSERT INTO credencial(nick, pass, usuario_id) VALUES ('adiiml', 'Xwq2maIbcERx4HmLUpjn5Ozfy4BhMS5++eoHOJDu21A=', 2);
INSERT INTO credencial(nick, pass, usuario_id) VALUES ('jesusps', 'wMwpmyl8XvvsHD7s7eBDcDq/5/I1z/DmvFDS6c3pPDI=', 3);
INSERT INTO credencial(nick, pass, usuario_id) VALUES ('melico', 'Ci0ARns3fPEJjXaA1hIJ6kkxVBbroqu+1TcvklacFfM=', 4);
INSERT INTO credencial(nick, pass, usuario_id) VALUES ('oscarmtz', '+qvBJQ1T/sD/Lly6qFkakF9p+2nz4GxwHVjuhb1EsiA==', 5);