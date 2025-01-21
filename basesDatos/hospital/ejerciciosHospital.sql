use hospital;

-- Ejercicio 2

Select * from doente;
select * from habitacion;
select * from ingreso;

SELECT ing_dataalta FROM Ingreso where ing_nhdoente = 101 order by ing_dataingreso;