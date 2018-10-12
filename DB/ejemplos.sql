USE ultrachip;

INSERT INTO usuario(nombre, permiso_id) VALUES ('Javier Serrano', 1);
INSERT INTO usuario(nombre, permiso_id) VALUES ('Adriana Molina', 2);
INSERT INTO usuario(nombre, permiso_id) VALUES ('Jesús Paredes', 3);
INSERT INTO usuario(nombre, permiso_id) VALUES ('Melisa Cornejo', 3);
INSERT INTO usuario(nombre, permiso_id) VALUES ('Oscar Martínez', 2);

INSERT INTO credencial(nick, pass, usuario_id) VALUES ('javiersl', 'zLAdINWrLxOiDTChF6hDeASaX36biINPGfIJBcwPEXc=', 1);
INSERT INTO credencial(nick, pass, usuario_id) VALUES ('adiiml', 'SQOGOkAvKOsvb3XwoqUbE2RjzCEjIFGKtykLSsggqp8=', 2);
INSERT INTO credencial(nick, pass, usuario_id) VALUES ('jesusps', 'R0bbeh7OAP8XZBbb8pAIkRBizZRtiAPCNk0eyuyl8hk=', 3);
INSERT INTO credencial(nick, pass, usuario_id) VALUES ('melico', 'GwmGBhVUKNoZ6ucHXU/FrSSlR5+DrWTPxCoI5/hcNXM=', 4);
INSERT INTO credencial(nick, pass, usuario_id) VALUES ('oscarmtz', 'yuR5M0Qsh/Hl5d5iva7VvlWsboO/MgWPrNLHQVIFe80=', 5);