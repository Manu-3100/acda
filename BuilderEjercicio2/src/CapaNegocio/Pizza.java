package CapaNegocio;

public class Pizza {
	
	private String masa;
	private String salsa;
	private String relleno;
	
	public String getMasa() {
		return masa;
	}
	
	public void setMasa(String masa) {
		this.masa = masa;
	}
		
	public void setSalsa(String salsa) {
		this.salsa = salsa;
	}
	
	public void setRelleno(String relleno) {
		this.relleno = relleno;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pizza masa = ")
				.append(masa)
				.append(", salsa = ")
				.append(salsa)
				.append(", relleno = ")
				.append(relleno);
		return builder.toString();
	}
	
	
	
	
	
	
	
}
