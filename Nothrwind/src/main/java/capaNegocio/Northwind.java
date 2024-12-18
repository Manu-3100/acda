package capaNegocio;

import java.sql.Statement;
import java.sql.Types;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Northwind {

	// Ejercicio 1. Obtención de clientes a partir del país y del importe mínimo de
	// sus compras
	public static List<Customer> getCustomersByCountry(String pais, double importeMinimo) {

		List<Customer> customers = new ArrayList<Customer>();

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
				PreparedStatement pstmt = con.prepareStatement("Select companyName, contactName, sum(Total) "
						+ "from customers " + "inner join orders on customers.CustomerID = orders.CustomerID "
						+ "inner join orderdetails on orders.OrderID = orderdetails.OrderID " + "where country=? "
						+ "group by CompanyName " + "having sum(Total) > ?;");) {

			pstmt.setString(1, pais);
			pstmt.setDouble(2, importeMinimo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				customers.add(new Customer(rs.getString(1), rs.getString("contactName")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return (customers.isEmpty()) ? null : customers;
	}

	// No forma parte del boletín. Código de prueba para recuperar una clave
	// autogenerada
	public static int addAlumno(String nombre) {

		int res = 0;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO alumnos " + "(nombre) " + "VALUES " + "( ? );",
						java.sql.Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, nombre);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			res = rs.getInt(1);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}

	// No forma parte del boletín.
	public static void removeAlumno() {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
				PreparedStatement pstmt = con.prepareStatement("Delete from alumnos");) {
			System.out.println(pstmt.executeUpdate());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	// Ejercicio 2

	public static int getNumerosEmpleadosPorPais(String pais) {
		int res = -1;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
				CallableStatement cstmt = con.prepareCall("{call EmpleadosPorLugarTrabajo(?, ?)}");) {

			cstmt.setString(1, pais);
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.execute();

			res = cstmt.getInt(2);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}

	// Ejercicio 3
	public static int modifyEmployee(int id, String firstName) {
		int res = 1;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
				PreparedStatement pstmt = con
						.prepareStatement("UPDATE employees set FirstName = ? where EmployeeID = ?");) {

			pstmt.setString(1, firstName);
			pstmt.setInt(2, id);

			if (pstmt.executeUpdate() == 0)
				res = -1;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}
	// Ejercicio 4

	// Ejercicio 5
	public static int addEmployee(String nombre, String apellidos) {
		int res = 0;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO employees" + "(LastName, FirstName) " + "VALUES " + "( ? , ? );",
						Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, apellidos);
			pstmt.setString(2, nombre);
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			res = rs.getInt(1);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}

	public static int addEmployee2(Employee empleado) {
		int res = 0;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO employees"
						+ "(LastName, FirstName, TitleOfCourtesy, City) " + "VALUES " + "( ? , ? , ? , ?);",
						Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, empleado.getLastName());
			pstmt.setString(2, empleado.getFirstName());
			pstmt.setString(3, empleado.getTitleOfCourtesy());
			pstmt.setString(4, empleado.getCity());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			res = rs.getInt(1);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}

	// Ejercicio 7.
	// Crear un método que reciba el código de un proveedor y devuelva una lista con
	// todos sus
	// productos en forma de objetos.
	public static List<Product> getProducts(int id) {

		List<Product> res = new ArrayList<Product>();

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
				PreparedStatement pstmt = con
						.prepareStatement("Select ProductName, UnitPrice from products where SupplierID = ?")) {

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				res.add(new Product(rs.getString(1), rs.getDouble(2)));
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return res;
	}

	// Ejercicio 8
	// Crear un método que reciba el código de un empleado y lo de de baja.
	public static void removeEmployee(int id) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
				PreparedStatement pstmt = con.prepareStatement("DELETE from Employees where EmployeeID = ?")) {

			pstmt.setInt(1, id);
			pstmt.execute();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static int removeEmployeeConNumeroFilas(int id) {
		int filas = -1;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
				CallableStatement cstmt = con.prepareCall("{?=call DeleteEmpleados(?)}")) {
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2, id);
			
			cstmt.execute();
			filas = cstmt.getInt(1);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return filas;
	}

}