DELIMITER $$

CREATE PROCEDURE addUsuario(
	IN name VARCHAR(20),
    IN pass VARCHAR(46))
BEGIN 
	
	if EXISTS (SELECT 1 FROM usuarios WHERE nombre = name) THEN
		SIGNAL SQLSTATE '45000' SET MESsAGE_TEXT = "El usuario ya esta registrado";
	else
		INSERT INTO usuarios (nombre, password)
        VALUES (name, pass);
    END IF;
    
	
END$$

DELIMITER ;