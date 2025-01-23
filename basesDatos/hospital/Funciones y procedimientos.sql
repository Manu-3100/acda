delimiter $$
CREATE FUNCTION `estatistica_medico`(medico int) RETURNS int deterministic
begin
  declare n int;
  select count(distinct i.ing_nhdoente) into n from ingreso i
    join tratamento t on tra_idingreso=ing_id
    where tra_codigomedico=medico;
  return n;
end $$

CREATE FUNCTION `existeHabitacion`(hab_numero INT) RETURNS tinyint deterministic
BEGIN
	Declare contador Int;
    
	Select count(*) into contador
    From habitacion h
    Where h.hab_numero = hab_numero;

    RETURN contador = 1;
END $$

CREATE FUNCTION `existePaciente`(doe_numhistoria INT) RETURNS tinyint deterministic
BEGIN
	Declare contador Int;
    
	Select count(*) into contador
    From doente d
    Where d.doe_numhistoria = doe_numhistoria;

	return contador = 1;
END $$

CREATE FUNCTION `hayCamaLibre`(hab_numero INT) RETURNS tinyint deterministic
BEGIN
	DECLARE contador INT;
    DECLARE numCamas INT;
    DECLARE camasOcupadas INT;
    
	SELECT Count(*) into contador
    FROM ingreso
    WHERE ing_numHabitacion = hab_numero;
    
    IF contador = 0 THEN
		RETURN true;
	END IF;
	
    SELECT hab_numCamas into numCamas
    FROM habitacion h
    WHERE h.hab_numero = hab_numero;
	
    SELECT Count(*) into camasOcupadas
    FROM ingreso
    WHERE ing_numHabitacion = hab_numero and
		  ing_dataAlta is null;
    
	RETURN numCamas > camasOcupadas;
END $$

CREATE FUNCTION estaIngresado(doe_numHistoria INT) RETURNS boolean DETERMINISTIC
BEGIN
    
    declare contador int;
    select count(*) into contador from ingreso
		where ing_nhdoente = doe_numHistoria and
				ing_dataalta is null;
	return contador > 0;
END$$

CREATE PROCEDURE pacientesPorMedico(medico int)
BEGIN
SELECT DISTINCT doe_numhistoria, doe_nome, doe_datanac FROM doente D
	INNER JOIN ingreso I 
		on d.doe_numhistoria = I.ing_nhdoente
    INNER JOIN tratamento T 
		on I.ing_id = T.tra_idingreso
	WHERE T.tra_codigomedico = medico;
END $$
delimiter ;