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