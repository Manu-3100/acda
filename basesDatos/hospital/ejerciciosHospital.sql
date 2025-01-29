use hospital;

-- Ejercicio 2

Select * from doente;
Select * from doente where doe_numhistoria = 2;
select * from habitacion;
Select * from ingreso;
Select * from ingreso where ing_nhdoente = 469;
Select * from medico;
Select * from tratamento;
Select * from tratamento where tra_idingreso = 489;
select * from habitacion;

insert into habitacion (hab_numero, hab_numcamas) values (354, 2);

insert into doente (doe_nome) values ("nadir");
insert into ingreso (ing_nhdoente, ing_numhabitacion) values (470, 354);
insert into tratamento (tra_idingreso, tra_enfermidade, tra_codigomedico) values (493 , "Infarto", 1);

delete from tratamento where tra_idingreso IN
	(Select ing_id from ingreso inner join doente on ing_nhdoente = doe_numhistoria where ing_nhdoente = 467);

DELETE t FROM tratamento t 
	INNER JOIN ingreso i ON t.tra_Idingreso = i.ing_id 
WHERE i.ing_nhdoente = 470;

-- Error Code: 1064. You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 
-- 'INNER JOIN ingreso i ON t.tra_IdIngreso = i.ing_id  WHERE i.ing_nhDoente = ?' at line 2

delete from ingreso where ing_nhdoente = 467;

delete from doente where doe_numhistoria = 467;
-- Error Code: 1452. Cannot add or update a child row: a foreign key constraint fails 
-- (`hospital`.`tratamento`, CONSTRAINT `tratamento_ibfk_1` FOREIGN KEY (`tra_idingreso`) REFERENCES `ingreso` (`ing_id`))
