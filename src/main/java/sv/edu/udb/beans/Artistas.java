package sv.edu.udb.beans;

public class Artistas {

	// ATRIBUTOS DE LA CLASE
	private int idArtista;
	private String nombreArtista;
	private String descripcion;
	private int idDisco;
	private Discos disco;

	// GETTERS Y SETTERS DE LOS ATRIBUTOS
	public int getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
	}

	public String getNombreArtista() {
		return nombreArtista;
	}

	public void setNombreArtista(String nombreArtista) {
		this.nombreArtista = nombreArtista;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
