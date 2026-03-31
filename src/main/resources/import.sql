insert into tb_categoria (nome, criado_em) values ('Livros',NOW());
insert into tb_categoria (nome, criado_em) values ('Notebookes',NOW());
insert into tb_categoria (nome, criado_em) values ('Computadores',NOW());
insert into tb_categoria (nome, criado_em) values ('Brinquedos',NOW());

insert into tb_produto (nome, descricao,preco,img_Url,criado_em) values ('Brinquedos 01','Boa qualidade 01','10','',NOW());
insert into tb_produto (nome, descricao,preco,img_Url,criado_em) values ('Brinquedos 02','Boa qualidade 02','20','',NOW());
insert into tb_produto (nome, descricao,preco,img_Url,criado_em) values ('Brinquedos 03','Boa qualidade 03','30','',NOW());
insert into tb_produto (nome, descricao,preco,img_Url,criado_em) values ('Brinquedos 04','Boa qualidade 04','40','',NOW());
insert into tb_produto (nome, descricao,preco,img_Url,criado_em) values ('Brinquedos 05','Boa qualidade 05','50','',NOW());

insert into tb_categoria (id_produto,id_categoria) values (1,1);
insert into tb_categoria (id_produto,id_categoria) values (1,2);
insert into tb_categoria (id_produto,id_categoria) values (2,1);
insert into tb_categoria (id_produto,id_categoria) values (3,3);
insert into tb_categoria (id_produto,id_categoria) values (4,4);
insert into tb_categoria (id_produto,id_categoria) values (5,4);


insert into tb_perfil (nome) values ('ROLE_ADMINISTRADOR');
insert into tb_perfil (nome) values ('ROLE_VENDEDOR');
insert into tb_perfil (nome) values ('ROLE_CLIENTE');

insert into tb_usuarios (nome,telefone,email,senha,criadoEm) values ('Thiago','37999647841','thiago@gmail.com','ifmg123','');
insert into tb_usuarios (nome,telefone,email,senha,criadoEm) values ('Igor','37999978435','igor@gmail.com','ifmg987','');

insert into tb_usuario_perfil (id_usuario, id_perfil) values (1,1);
insert into tb_usuario_perfil (id_usuario, id_perfil) values (1,2);
insert into tb_usuario_perfil (id_usuario, id_perfil) values (1,3);
insert into tb_usuario_perfil (id_usuario, id_perfil) values (2,3);