package Pojos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Organizacion {

	private String abbreviation;
	private String name;
	private String city;
	private LocalDate Established;

	public Organizacion() {
	}

	public Organizacion(String abbreviation, String name, String city, LocalDate established) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.city = city;
		Established = established;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDate getEstablished() {
		return Established;
	}

	public void setEstablished(LocalDate established) {
		Established = established;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Organizacion:");
		builder.append(" abbreviation=");
		builder.append(abbreviation);
		builder.append(", name=");
		builder.append(name);
		builder.append(", city=");
		builder.append(city);
		builder.append(", Established=");
		builder.append(Established);
		return builder.toString();
	}

	public static Organizacion nuevaOrganizacion(String abrOrg1, String abrOrg2) {

		Connection con = null;

		Organizacion org1 = getOrganizationByAbbreviation(abrOrg1);
		Organizacion org2 = getOrganizationByAbbreviation(abrOrg2);
		Organizacion orgRes = null;

		Set<Country> countries = getCountries(abrOrg1, abrOrg2);

		String abbr = org1.abbreviation + org2.abbreviation;
		String name = org1.name + org2.name;

		int maxAbbrSize = getColumnSize("Abbreviation");
		int maxNameSize = getColumnSize("Name");
		
		if (maxAbbrSize < abbr.length())
			abbr = abbr.substring(0, maxAbbrSize);
		
		if (maxNameSize < name.length())
			name = name.substring(0, maxNameSize);
		
		try {
			orgRes = new Organizacion(abbr, name, org1.getCity(), LocalDate.now());
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mundial", "root", "");
			PreparedStatement pstmt = con.prepareStatement("""
					Insert into Organization
									(Abbreviation, Name, City, Established)
							values
									(?, ?, ?, ?)
					""");
			con.setAutoCommit(false);

			pstmt.setString(1, orgRes.abbreviation);
			pstmt.setString(2, orgRes.name);
			pstmt.setString(3, orgRes.city);
			pstmt.setDate(4, Date.valueOf(orgRes.Established));

			pstmt.executeUpdate();

			insertCountriesinIsmember(con, countries, abbr);
			deleteCountry(con, abrOrg1);
			deleteCountry(con, abrOrg2);

		} catch (SQLException e) {
			orgRes = null;
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
				e1.printStackTrace();
			}
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true);
				con.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return orgRes;
	}

	public static void deleteIsmember(Connection con, String org) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement("""
				Delete from ismember where organization = ?
				""");

		pstmt.setString(1, org);
		pstmt.execute();
	}

	public static void deleteCountry(Connection con, String org) throws SQLException {

		deleteIsmember(con, org);

		PreparedStatement pstmt = con.prepareStatement("""
				Delete from Organization where Abbreviation = ?
				""");

		pstmt.setString(1, org);
		pstmt.execute();

	}

	public static void insertCountriesinIsmember(Connection con, Set<Country> countries, String abbr)
			throws SQLException {
		PreparedStatement pstmt = con.prepareStatement("""
				Insert into ismember
								(Country, Organization, Type)
						values
								(?, ?, ?)
				""");
		for (Country c : countries) {
			pstmt.setString(1, c.getCode());
			pstmt.setString(2, abbr);
			pstmt.setString(3, "member");
			pstmt.executeUpdate();
		}
	}

	public static Set<Country> getCountries(String org1, String org2) {
		Set<Country> res = new HashSet();

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mundial", "root", "");
				PreparedStatement pstmt = con.prepareStatement("""
						Select * from country c
								inner join ismember i on c.Code = i.Country
								where
									i.Organization = ?
									or
								 	i.Organization = ?
						group by c.name;
						""");) {

			pstmt.setString(1, org1);
			pstmt.setString(2, org2);
			pstmt.execute();

			ResultSet rs = pstmt.getResultSet();
			while (rs.next()) {
				res.add(new Country(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5)));
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return res;

	}

	public static Organizacion getOrganizationByAbbreviation(String abbr) {

		Organizacion org = null;

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mundial", "root", "");
				CallableStatement cstmt = con.prepareCall("{call GetOrganizationByAbbreviation(?)}")) {

			cstmt.setString(1, abbr);
			cstmt.execute();

			ResultSet rs = cstmt.getResultSet();
			if (rs.next()) {
				org = new Organizacion(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate());
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return org;

	}

	public static int getColumnSize(String column) {
		int size = 0;

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mundial", "root", "");) {
			DatabaseMetaData dbMeta = con.getMetaData();
			ResultSet rs = dbMeta.getColumns(null, "mundial", "Organization", column);

			if (rs.next()) {
				size = rs.getInt(7);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return size;
	}
}
