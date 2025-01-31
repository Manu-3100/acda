DELIMITER $$
DROP PROCEDURE IF EXISTS addUsuario;
CREATE PROCEDURE addUsuario(
	IN name VARCHAR(20),
    IN pass VARCHAR(46))
BEGIN 
	INSERT INTO usuarios (nombre, password)
    VALUES (name, pass);
END$$

DELIMITER ;
