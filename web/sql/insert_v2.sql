--Insert Recursos
insert into Recurso (nome, descricao) values('template-mapa-ocorrencia','Permite acessar a página de mapa de ocorrência.');
insert into Recurso (nome, descricao) values('template-posicionamento-gps','Permite acessar a página de posicionamento gps.');
insert into Recurso (nome, descricao) values('template-viatura','Permite acessar a página de viaturas.');
insert into Recurso (nome, descricao) values('template-funcionario','Permite acessar a página de funcionários.');
insert into Recurso (nome, descricao) values('template-usuario','Permite acessar a página de usuários.');
insert into Recurso (nome, descricao) values('template-perfil','Permite acessar a página de perfil.');
insert into Recurso (nome, descricao) values('template-registro-ponto','Permite acessar a página de registro ponto.');
insert into Recurso (nome, descricao) values('template-base','Permite acessar a página das bases');
insert into Recurso (nome, descricao) values('template-atendimento','Permite acessar a página de atendimento.');
insert into Recurso (nome, descricao) values('template-ficha-aph','Permite acessar a página de ficha aph.');

-- Inser Perfil
insert into Perfil (nome, descricao) values('ADMINISTRADOR','Administrador do Sistema.');
insert into Perfil (nome, descricao) values('MÉDICO REGULADOR','Médicos regulador da Central.'); -- No registro do atendimento carrega apenas os médicos (Perfil id 2).
insert into Perfil (nome, descricao) values('ATENDENTE CENTRAL','Atendentes da Central.');
insert into Perfil (nome, descricao) values('ATENDENTE VIATURA','Atendentes da Viatura.');

-- Perfil recurso: Administrador
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (1,1);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (1,2);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (1,3);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (1,4);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (1,5);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (1,6);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (1,7);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (1,8);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (1,9);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (1,10);

--Perfil recurso: Médico Regulador da Central
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (2,1);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (2,2);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (2,3);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (2,4);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (2,7);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (2,9);

--Perfil recurso: Atendente da Central
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (3,1);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (3,2);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (3,3);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (3,4);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (3,7);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (3,9);

--Perfil recurso: Atendente Viatura
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (4,1);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (4,2);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (4,3);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (4,4);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (4,7);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (4,9);
insert into Perfil_Recurso (perfil_idPerfil, recurso_idRecurso) values (4,10);

--insert das bases
insert into base (codigo, nome, descricao) values (12337,'siqueira campos','base');
insert into base (codigo, nome, descricao) values (12414,'atalaia','base');
insert into base (codigo, nome, descricao) values (42329,'luzia','base');
insert into base (codigo, nome, descricao) values (12342,'ponto novo','base');
insert into base (codigo, nome, descricao) values (87381,'centro','base');
insert into base (codigo, nome, descricao) values (28760,'barra dos coqueiros','base');
insert into base (codigo, nome, descricao) values (09856,'hermes fontes','base');
insert into base (codigo, nome, descricao) values (97634,'barão','base');
insert into base (codigo, nome, descricao) values (73648,'beira mar','base');


-- Insert Viaturas.
insert into Viatura(codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (12798, 'hzi-1001', 'Van 01', 'skype:hzi1001?call', 'USB', 1);
insert into Viatura(codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (22987, 'hzi-1002', 'Van 02', 'skype:hzi1001?call', 'USA', 2);
insert into Viatura(codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (93838, 'hzi-1003', 'Van 03', 'skype:hzi1001?call', 'USB', 1);
insert into Viatura(codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (38432, 'hzi-1004', 'Van 04', 'skype:hzi1001?call', 'USA', 4);
insert into Viatura(codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (48573, 'hzi-1005', 'Van 05', 'skype:hzi1001?call', 'USA', 1);
insert into Viatura(codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (26783, 'hzi-1006', 'Van 06', 'skype:hzi1001?call', 'USA', 4);
insert into Viatura(codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (94573, 'hzi-1007', 'Van 07', 'skype:hzi1001?call', 'USB', 5);
insert into Viatura(codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (28493, 'hzi-1008', 'Van 08', 'skype:hzi1001?call', 'USA', 1);
insert into Viatura(codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (18372, 'hzi-1009', 'Van 09', 'skype:hzi1001?call', 'USA', 2);
insert into Viatura(codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (38294, 'hzi-1010', 'Van 10', 'skype:hzi1001?call', 'USA', 1);
insert into Viatura(codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (38132, 'hzi-1011', 'Van 11', 'skype:hzi1001?call', 'USB', 3);
insert into Viatura(codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (38873, 'hzi-1012', 'Van 12', 'skype:hzi1001?call', 'USB', 1);
insert into Viatura(codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (40638, 'hzi-1013', 'Van 13', 'skype:hzi1001?call', 'USA', 1);

-- Insert Usuários.
insert into Login_Funcionario (perfil_idPerfil, nome, usuario, senha, cpf, situacao, skype, email) values (1,'FELIPE VIANA','felipe','e10adc3949ba59abbe56e057f20f883e','19609915078',1,'skype:swsamucentral1?call','felipe@gmail.com');
insert into Login_Funcionario (perfil_idPerfil, nome, usuario, senha, cpf, situacao, skype, email) values (2,'ADRIEL TRINDADE','adriel','e10adc3949ba59abbe56e057f20f883e','19609915078',1,'skype:swsamucentral1?call','adriel@gmail.com');
insert into Login_Funcionario (perfil_idPerfil, nome, usuario, senha, cpf, situacao, skype, email) values (3,'THALLES DANIEL','thalles','e10adc3949ba59abbe56e057f20f883e','19609915078',1,'skype:swsamucentral1?call','thalles@gmail.com');
insert into Login_Funcionario (perfil_idPerfil, nome, usuario, senha, cpf, situacao, skype, email) values (4,'DAVID NUNES','david','e10adc3949ba59abbe56e057f20f883e','19609915078',1,'skype:swsamucentral1?call','david@gmail.com');
insert into Login_Funcionario (perfil_idPerfil, nome, usuario, senha, cpf, situacao, skype, email) values (4,'JOSÉ ROBERTO','roberto','e10adc3949ba59abbe56e057f20f883e','19609915078',1,'skype:swsamucentral1?call','roberto@gmail.com');
