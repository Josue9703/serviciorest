package sv.edu.udb.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Discos {
	// ATRIBUTOS DE LA CLASE
	private int idDisco;
	private String nombreDisco;
	private int idArtista;
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

	public int getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
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


