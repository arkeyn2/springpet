INSERT INTO rol (rol_nombre) VALUES ('ROLE_ADMIN'), ('ROLE_USER'), ('ROLE_SECRETARIA');

INSERT INTO public.usuario(apellido, email, nombre, nombre_usuario, password, rut) VALUES ('alexis', 'a@a.a','alexis' ,'alexis' , '$2a$10$g1NiG2AiQpTxg/a8HkvB.ObqopcO1QCk76jywtelrv17BMpulR4F2', '123456');
INSERT INTO public.usuario(apellido, email, nombre, nombre_usuario, password, rut) VALUES ('secretaria', 's@s.s', 'secretaria', 'secretaria', '$2a$10$uNq0xm359UvjKgS4L0Ad5umETvBYtCNFysqokAkisxOCUGC7XsDri', '1234569');
INSERT INTO public.usuario(apellido, email, nombre, nombre_usuario, password, rut) VALUES ('yuki', 'u@u.u', 'yuki', 'yuki','$2a$10$GrxgW1ydycwaoNGdMWrykulnU8w90ay2oS/p4G60mkMx3hbJRlNfC', '98765432');

INSERT INTO public.usuario_rol(	usuario_id, rol_id)	VALUES (1, 1);
INSERT INTO public.usuario_rol(	usuario_id, rol_id)	VALUES (2, 3);
INSERT INTO public.usuario_rol(	usuario_id, rol_id)	VALUES (3, 2);

INSERT INTO public.mascotas(detalle_mas, estado, imag_mas, nombre_mas, numero, provincia, region)	VALUES ('sd', 'Perdido','im','pedro','9999999999','Linares','Maule');
INSERT INTO public.mascotas(detalle_mas, estado, imag_mas, nombre_mas, numero, provincia, region)	VALUES ('sd', 'En casa','im','sushi','99458616','Talca','Talca');
INSERT INTO public.mascotas(detalle_mas, estado, imag_mas, nombre_mas, numero, provincia, region)	VALUES ('sd', 'En casa','im','juanjo','9999999999','Parral','Maule');
INSERT INTO public.mascotas(detalle_mas, estado, imag_mas, nombre_mas, numero, provincia, region)	VALUES ('sd', 'Perdido','im','pax','9999999999','Longavi','Maule');

INSERT INTO public.tipo(detalle, nombre_tip)	VALUES ('perro','perro');
INSERT INTO public.tipo(detalle, nombre_tip)	VALUES ('gato','gato');
INSERT INTO public.tipo(detalle, nombre_tip)	VALUES ('ave','ave');
INSERT INTO public.tipo(detalle, nombre_tip)	VALUES ('pez','pez');

INSERT INTO public.inscripcion(fecha_desde, fecha_hasta, usuario_id, mascota_id)VALUES ('2016-02-12','2015-02-12', 1, 1);
INSERT INTO public.inscripcion(fecha_desde, fecha_hasta, usuario_id, mascota_id)VALUES ('2016-02-12','2015-02-12', 3, 2);
INSERT INTO public.inscripcion(fecha_desde, fecha_hasta, usuario_id, mascota_id)VALUES ('2016-02-12','2015-02-12', 3, 3);
INSERT INTO public.inscripcion(fecha_desde, fecha_hasta, usuario_id, mascota_id)VALUES ('2016-02-12','2015-02-12', 4, 4);
