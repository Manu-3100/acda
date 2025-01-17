package capaNegocio;

import java.sql.Statement;
import java.sql.Types;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// Ejercicio 5
	// Procedimiento Almacenado
	public static int addEmployeePA(String nombre, String apellidos) {
		int res = -1;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
				CallableStatement cstmt = con.prepareCall("{call addEmployee(?, ?, ?)}");) {

			cstmt.setString(1, nombre);
			cstmt.setString(2, apellidos);
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.executeUpdate();

			res = cstmt.getInt(3);

			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
			return res;
		}
	
	// Ejercicio 5
	// Función
	public static int addEmployeeF(String nombre, String apellidos) {
		int filas = -1;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
				CallableStatement cstmt = con.prepareCall("{?=addEmployee(?, ?}")) {
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, nombre);
			cstmt.setString(3, apellidos);
			
			cstmt.execute();
			filas = cstmt.getInt(1);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return filas;
	}
	
	// Ejercicio 6
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

			pstmt.execute();

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

	
	// Ejercicio 9
	// Igual que el anterior, pero devolviendo el número de filas afectadas
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
	
	// Ejercicio 10
	// public static List<String> actualizarCliente(){
		
		
	// }
	
	// Ejercicio 11
	
	public static Product getProduct(int productID) {
		Product producto = null;
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
				CallableStatement cstmt = con.prepareCall("{call getProduct(?)}")) {
			
			cstmt.setInt(1, productID);	
			cstmt.execute();
		
			ResultSet rs = cstmt.getResultSet();
			
			if (rs.next()) {
				producto = new Product(productID, rs.getString(1));
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return producto;
	}

	// Ejercicio 12
	public static int incrementoPrecioPorCategoria(int categoryID, double porcentaje) {
		int filasAfectadas = 0;
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
			PreparedStatement pstmt = con.prepareStatement(
						"update Products " + 
						"set unitPrice = unitPrice / (1 + ?) " +
						" where categoryID = ?");
			con.setAutoCommit(false);
			pstmt.setDouble(1, porcentaje);
			pstmt.setInt(2, categoryID);
			
			filasAfectadas = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
			System.err.println(e.getMessage());
		} finally {
			try {
				con.setAutoCommit(true);
				con.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		return filasAfectadas;
	}
	
	// Ejercicio 13
	public static void getDatosGenerales() {
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "")) {
			DatabaseMetaData metadata = con.getMetaData();
			
			System.out.println("Usuario de base de datos " + metadata.getUserName());
			System.out.println("Driver usado " + metadata.getDriverName() + "version: " + metadata.getDriverVersion());
			System.out.println("Gestor de base de datos " + metadata.getDatabaseProductName());
		
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	// ejercicio 14
	public static void getTablasYVistas() {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "")) {
			DatabaseMetaData metadata = con.getMetaData();
			
			ResultSet rs = metadata.getTables("northwind", null, null , null);
			String res;
			while (rs.next()) {
				if (rs.getString("TABLE_TYPE").equals("TABLE")) {
					res = "Tabla ";
				}
				else if (rs.getString("TABLE_TYPE").equals("VIEW")){
					res = "Vista ";
				}
				else {
					res = "otro";
				}
				res += rs.getString("TABLE_NAME");
				System.out.println(res);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	// ejercicio 15
	public static void getProcedimientosYFunciones() {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "")) {
			DatabaseMetaData metadata = con.getMetaData();
			
			ResultSet rs = metadata.getProcedures("northwind", null , null);
			String res;
			while (rs.next()) {
				
				if (rs.getShort("PROCEDURE_TYPE") == 1) {
					res = "Procedimiento -> ";
				}
				else if (rs.getShort("PROCEDURE_TYPE") == 2) {
					res = "Función -> ";
				}
				else {
					res = "desconocido";
				}
				res += rs.getString("PROCEDURE_NAME");
				System.out.println(res);	
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	// Ejercicio 16
	public static void getDatosTabla(String tabla) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "")) {
			DatabaseMetaData metadata = con.getMetaData();
			ResultSet rs = metadata.getColumns("Northwind", null, tabla, null);
			
			while (rs.next()) {
				System.out.println(
						rs.getString("COLUMN_NAME") + " " +
						rs.getString("TYPE_NAME") + " " +
						rs.getInt("COLUMN_SIZE") );
				
				if (rs.getInt("NULLABLE") == ResultSetMetaData.columnNoNulls) {
					System.out.println("No admite nulos");
				}
				else if (rs.getInt("NULLABLE") == ResultSetMetaData.columnNullable) {
					System.out.println("Si admite nulos");
				}
				else {
					System.out.println("Se desconoce si admite nulos");
				}
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	// Ejercicio 17
	public static void getPrimaryKey(String tabla) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "")) {
			DatabaseMetaData metadata = con.getMetaData();
			ResultSet rs = metadata.getPrimaryKeys("Northwind", null , null);
			
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	// Ejercicio 18
	public static void getForeingKey(String tabla) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "")) {
			DatabaseMetaData metadata = con.getMetaData();
			ResultSet rs = metadata.getExportedKeys("Northwind", null , tabla);
			
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	// Ejercicio 19
	public static void getInfoConsulta(String tabla) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Northwind", "root", "");
			 Statement stmt = con.createStatement();) {
			
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}


}






