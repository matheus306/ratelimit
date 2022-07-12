INSERT INTO tb_parceiros (id, login, name, rate_limit) VALUES (1, 'dog@mg.com.br',      'Matheus', 1);
INSERT INTO tb_parceiros (id, login, name, rate_limit) VALUES (2, 'dog@santos.com.br',  'Victor',  1);
INSERT INTO tb_parceiros (id, login, name, rate_limit) VALUES (3, 'dog@awsman.coml.br', 'Yago',    0);

INSERT INTO tb_parceiros_rate_limit (id, id_parceiro, metodo, path, plan) VALUES (1, 1, 'GET',  '/bordoes-pedrim', 'FREE');
INSERT INTO tb_parceiros_rate_limit (id, id_parceiro, metodo, path, plan) VALUES (2, 1, 'POST', '/bordoes-pedrim', 'PROFESSIONAL');
INSERT INTO tb_parceiros_rate_limit (id, id_parceiro, metodo, path, plan) VALUES (3, 2, 'GET',  '/bordoes-pedrim', 'BASIC');
INSERT INTO tb_parceiros_rate_limit (id, id_parceiro, metodo, path, plan) VALUES (4, 2, 'POST', '/bordoes-pedrim', 'BASIC');