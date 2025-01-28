package capaNegocio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class Medico {
	
	public static List<Paciente> estadisticasMedico(int id_medico){
		
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Hospital", "root", "");
				CallableStatement cstmt = con.prepareCall("{call pacientesPorMedico(?)}") ){
			cstmt.setInt(1, id_medico);
			cstmt.execute();
			ResultSet rs = cstmt.getResultSet();
			while (rs.next()) {
				pacientes.add(
						new Paciente(
									rs.getInt(1),
									rs.getString(2),
									rs.getDate(3).toLocalDate())
						);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return (pacientes.isEmpty())? pacientes : null;
	}
	
	
}
