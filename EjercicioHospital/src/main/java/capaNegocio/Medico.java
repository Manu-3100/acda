package capaNegocio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class Medico {

	
	public static ArrayList<Paciente> estadisticasMedico(int id_medico){
		
		boolean res = false;
		Connection con = null;
		try (CallableStatement cstmt = con.prepareCall("{ pacientesPorMedico(?, ?)}")){
			cstmt.setInt(1, id_medico);
			cstmt.registerOutParameter(1, Types.BOOLEAN);
			cstmt.execute();

			res = cstmt.getBoolean(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return res;
		
		return null;
	}
	
	
}
