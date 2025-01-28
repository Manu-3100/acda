use hospital;

-- Ejercicio 2

Select * from doente;
select * from habitacion;
Select * from ingreso;
Select * from ingreso where ing_nhdoente = 467;
Select * from medico;
Select * from tratamento;
Select * from tratamento where tra_idingreso = 487;
select * from habitacion where ing_nhdoente = 467;


insert into doente (doe_nome) values ("nadir");
insert into habitacion (hab_numero, hab_numcamas) values (353, 2);

delete from tratamento where tra_idingreso IN
	(Select ing_id from ingreso inner join doente on ing_nhdoente = doe_numhistoria where ing_nhdoente = 467);
    
delete from ingreso where ing_nhdoente = 467;

insert into tratamento (tra_idingreso, tra_enfermidade, tra_codigomedico) values (487 , "Infarto", 1);

Error Code: 1451. Cannot delete or update a parent row: a foreign key constraint fails (`hospital`.`tratamento`, CONSTRAINT `tratamento_ibfk_1` FOREIGN KEY (`tra_idingreso`) REFERENCES `ingreso` (`ing_id`))	0.266 sec
