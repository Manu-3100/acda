use northwind;
-- Ejercicio 1
Select ProductName from products order by UnitPrice desc limit 1;

-- Ejercicio 2
Select ContactName from customers where country = "Germany" or country = "Italy" or country = "Austria";

-- Ejercicio 3
Select country, count(ContactName) from customers group by country;

-- Ejercicio 4
Select ContactName, OrderDate from customers
		inner join orders on orders.CustomerID = customers.CustomerID 
        where OrderDate = (Select max(OrderDate) from orders);

-- Ejercicio 5
Select CompanyName from suppliers
		inner join products on products.SupplierID = suppliers.SupplierID
        order by UnitPrice desc limit 1;

-- Ejercicio 6
select count(*) from orders where CustomerID = (select CustomerID from customers where CompanyName = "Romero y tomillo");

-- Ejercicio 7
select OrderID from orders where CustomerID = (select CustomerID from customers where CompanyName = "Romero y tomillo") order by OrderDate asc limit 1;

-- Ejercicio 8
select FirstName from employees 
		inner join orders on orders.EmployeeID = employees.EmployeeID
        inner join customers on customers.CustomerID = orders.CustomerID
        where CompanyName = "Romero y tomillo" 
        group by FirstName;

-- Ejercicio 9
select LastName, FirstName from employees 
		inner join orders on orders.EmployeeID = employees.EmployeeID
        inner join customers on customers.CustomerID = orders.CustomerID
        where CompanyName = "Romero y tomillo" 
        group by FirstName
        order by orders.OrderDate desc limit 1;
        
-- Ejercicio 10
select ContactName from orders
			inner join customers on customers.CustomerID = orders.CustomerID
			where ShipVia = (Select ShipperID from shippers where CompanyName = "United Package");
            
-- Ejercicio 11
select customers.ContactName, orders.OrderDate, orders.ShippedDate from orders
			inner join customers on customers.CustomerID = orders.CustomerID
			where ShipVia = (Select ShipperID from shippers where CompanyName = "United Package");
            
-- Ejercicio 12
select count(*) from orders where ShippedDate = "0000-00-00 00:00:00";

-- Ejercicio 13
select count(*) from orders where CustomerID = (select CustomerID from customers where CompanyName = "Lino-Delicateses") and ShippedDate = "0000-00-00 00:00:00";

-- Ejercicio 14
select ProductName from orders
		inner join customers on customers.CustomerID = orders.CustomerID
        inner join orderdetails on orders.OrderID = orderdetails.OrderID
        inner join products on products.ProductID = orderdetails.ProductID
        where customers.CompanyName = "B?lido Comidas Preparadas";
        
-- Ejercicio 15
select ProductName, Quantity from orders
		inner join customers on customers.CustomerID = orders.CustomerID
        inner join orderdetails on orders.OrderID = orderdetails.OrderID
        inner join products on products.ProductID = orderdetails.ProductID
        where customers.CompanyName = "B?lido Comidas Preparadas";

-- Ejercicio 16
select CategoryName from orders
		inner join customers on customers.CustomerID = orders.CustomerID
        inner join orderdetails on orders.OrderID = orderdetails.OrderID
        inner join products on products.ProductID = orderdetails.ProductID
        inner join categories on categories.CategoryID = products.CategoryID
        where customers.CompanyName = "Antonio Moreno Taquer?a"
        group by CategoryName;
        
-- Ejercicio 17
select CategoryName, count(products.ProductName) from orders
		inner join customers on customers.CustomerID = orders.CustomerID
        inner join orderdetails on orders.OrderID = orderdetails.OrderID
        inner join products on products.ProductID = orderdetails.ProductID
        inner join categories on categories.CategoryID = products.CategoryID
        where customers.CompanyName = "Antonio Moreno Taquer?a"
        group by CategoryName;
        
-- Ejercicio 18
Select count(ProductName), CategoryName from products
		inner join categories on categories.CategoryID = products.CategoryID
        group by CategoryName
        having count(ProductName) > 10;
        
-- Ejercicio 19
Select count(*), ShipCity from orders group by ShipCity;

-- Ejercicio 20
select * from products order by UnitPrice desc limit 1;

-- Ejercicio 21
select count(*), Year(OrderDate) from orders group by Year(OrderDate);

-- Ejercicio 22
select count(*), Year(OrderDate) from orders 
		where ShipCountry = "USA"
        group by Year(OrderDate);
        
-- Ejercicio 23
select count(*), Year(OrderDate) from orders group by Year(OrderDate) having count(*) > 200;
        
-- Ejercicio 24

Select customers.CompanyName, orders.OrderID, sum(UnitPrice) from customers
		inner join orders on orders.CustomerID = customers.CustomerID
        inner join orderdetails on orderdetails.OrderID = orders.OrderID
        group by CompanyName;
        
-- Ejercicio 25 
Select customers.CompanyName, count(orders.OrderID), sum(UnitPrice) from customers
		inner join orders on orders.CustomerID = customers.CustomerID
        inner join orderdetails on orderdetails.OrderID = orders.OrderID
        where ShipCountry = "Spain"
        group by CompanyName;
        
-- Ejercicio 26        
select employees.FirstName, idJefe.FirstName from employees, (select FirstName, EmployeeID from employees) as idJefe 
		where ReportsTo = idJefe.EmployeeID
        group by employees.EmployeeID;
        
-- Ejercicio 27
SELECT case when (SELECT COUNT(*) FROM employees
                        INNER JOIN
							employeeterritories ON employeeterritories.EmployeeID = employees.EmployeeID
                        INNER JOIN
							territories ON employeeterritories.TerritoryID = territories.TerritoryID
					WHERE employees.City = 'Tacoma' AND territories.TerritoryDescription = 'Boston') > 0
        THEN
            'Si'
        ELSE 'No'
    END AS resultado;

-- Ejercicio 28
select employees.Firstname, count(orders.OrderID) from employees 
	inner join orders on employees.EmployeeID = orders.EmployeeID
    group by employees.Firstname;

-- Ejercicio 29
select ShipCity, count(*) as cantidad from orders group by ShipCity order by cantidad desc limit 3;

-- Ejercicio 30


