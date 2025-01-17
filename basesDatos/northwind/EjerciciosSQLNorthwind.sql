-- Estes son os do proyecto Northwind
-- Ejercicio 10
DELIMITER $$
Create PROCEDURE `actualizaCliente`(clave CHAR(5), 
	nombreEmpresa VARCHAR(40), nombreContacto VARCHAR(30))
BEGIN
	UPDATE customers
	SET companyName = nombreEmpresa,
		contactName = nombreContacto
	WHERE customerID = clave;
END $$
Delimiter ;

-- Ejercicio 11
DELIMITER $$
Create PROCEDURE getProduct(id int)
BEGIN
	Select ProductName from products
		where ProductID = id;
END $$
Delimiter ;

delimiter $$
CREATE FUNCTION clientesPais (pais varchar(15))
RETURNS int
deterministic
BEGIN
	declare contador int;
    set contador = (select count(*) from customers where country = pais);
    return contador;
END$$
delimiter ;


