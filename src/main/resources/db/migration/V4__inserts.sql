INSERT INTO drinks
VALUES (1,'COCA-COLA ZERO','2 LTS',10,'WITHOUT_SUGAR'),(2,'COCA-COLA','2 LTS',10,'WITH_SUGAR'),(3,'SUCO DE LARANJA','1 LT',13,'WITH_SUGAR'),(4,'ÁGUA MINERAL','500 ML',3,'WITHOUT_SUGAR');

INSERT INTO ingredients
VALUES (1,'QUEIJO','',0.5,'ADDITIONAL'),(2,'PÃO','',0.3,'NOT_ADDITIONAL'),(3,'CARNE','',5,'ADDITIONAL'),(4,'ALFACE','',1,'ADDITIONAL'),(5,'TOMATE','',1,'ADDITIONAL'),(6,'CEBOLA','',1,'ADDITIONAL'),(7,'BACON','',2,'ADDITIONAL'),(8,'BATATA','',2,'ADDITIONAL'),(9,'FRANGO','',4,'ADDITIONAL'),(10,'MOLHO','',1,'NOT_ADDITIONAL');

INSERT INTO users
VALUES (2,'teste@teste.com','$2a$10$aWjFYFQzJl9rAcba.SWYDuZhviN9BeOjBvAiyzR4LtLoxdmPih2Wu');

INSERT INTO hamburgers VALUES (1,'CHEESE SALADA','LANCHE QUE VAI QUEIJO, CARNE, ALFACE, TOMATE, CEBOLA',32),(2,'BURGER BACON','LANCHE COM MUITO BACON',40),(3,'BURGER SIMPLES','LANCHE SIMPLES',31);

INSERT INTO hamburgers_ingredients VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,1,2),(8,2,2),(9,3,2),(10,7,2),(11,10,2),(12,1,3),(13,2,3),(14,3,3),(15,10,3);

INSERT INTO order_drinks VALUES (1,1,1),(2,1,1),(3,2,3);

INSERT INTO order_hamburgers VALUES (1,1,1),(2,1,2),(3,2,2);

INSERT INTO orders VALUES (1,'2024-05-08 22:48:22','Guilherme Santiago','99999999998','Avenida Waldemar Alves','Jardim Brasil','16074000','',3256,'SP','Araçatuba',92,''),(2,'2024-05-08 22:50:39','Julia Monteiro','18000000000','Rua Aguapeí','Jardim do Prado','16025455','',8787,'SP','Araçatuba',53,'Tenho alergia a cebola e quero a carne ao ponto');
