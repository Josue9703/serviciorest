package sv.edu.udb.beans;

public class DiscoConNombre {

	// ATRIBUTOS DE LA CLASE
	private int idDisco;
	private String nombreDisco;
	private String nombreArtista;
	private int numeroCanciones;
	private double precio;

	
	// GETTERS Y SETTERS DE LOS ATRIBUTOS
	public int getIdDisco() {
		return idDisco;
	}

	public void setIdDisco(int idDisco) {
		this.idDisco = idDisco;
	}

	public String getNombreDisco() {
		return nombreDisco;
	}

	public void setNombreDisco(String nombreDisco) {
		this.nombreDisco = nombreDisco;
	}

	public String getNombreArtista() {
		return nombreArtista;
	}

	public void setNombreArtista(String nombreArtista) {
		this.nombreArtista = nombreArtista;
	}

	public int getNumeroCanciones() {
		return numeroCanciones;
	}

	public void setNumeroCanciones(int numeroCanciones) {
		this.numeroCanciones = numeroCanciones;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
