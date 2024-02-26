package sv.edu.udb.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sv.edu.udb.beans.DiscoConNombre;
import sv.edu.udb.beans.Discos;

public class DiscosDAO extends AppConnection {

	// METODO PARA INSERTAR UN DISCO
	public void insertar(Discos discos) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("INSERT INTO discos(nombre_disco, id_artista, numero_canciones, precio) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, discos.getNombreDisco());
		pstmt.setInt(2, discos.getIdArtista());
		pstmt.setInt(3, discos.getNumeroCanciones());
		pstmt.setDouble(4, discos.getPrecio());

		// EJECUTAMOS LA INSERCION
	    int filasAfectadas = pstmt.executeUpdate();

	    if (filasAfectadas > 0) {
	        // INSERCION EXITOSA OBTENDREMOS EL ID AUTOGENERADO
	        ResultSet keys = pstmt.getGeneratedKeys();
	        
	        if (keys.next()) {
	            int id = keys.getInt(1);
	            discos.setIdDisco(id);
	        } else {
	            // SINO SE GENERA EL ID LANZAR MENSAJE
	            System.out.println("No se pudieron obtener las claves generadas.");
	        }
	    }
		close();
	}

	// METODO PARA ACTUALIZAR UN DISCO
	public void actualizar(Discos discos) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("UPDATE discos SET nombre_disco = ?, id_artista = ?, numero_canciones = ?, precio = ? WHERE id_disco = ?");
		pstmt.setString(1, discos.getNombreDisco());
		pstmt.setInt(2, discos.getIdArtista());
		pstmt.setInt(3, discos.getNumeroCanciones());
		pstmt.setDouble(4, discos.getPrecio());
		pstmt.setInt(5, discos.getIdDisco());
		pstmt.executeUpdate();
		close();
	}

	// METODO PARA ELIMINAR UN DISCO
	public void eliminar(int idDisco) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("DELETE FROM discos WHERE id_disco = ?");
		pstmt.setInt(1, idDisco);
		pstmt.executeUpdate();
		close();
	}

	// METODO PARA LISTAR TODOS LOS DISCOS
	public ArrayList<DiscoConNombre> listar() throws SQLException {
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT d.id_disco, d.nombre_disco, a.nombre_artista, d.numero_canciones, d.precio  FROM discos d JOIN artistas a ON d.id_artista = a.id_artista");
		ArrayList<DiscoConNombre> discos = new ArrayList();

		while (resultSet.next()) {
			DiscoConNombre tmp = new DiscoConNombre();
			tmp.setIdDisco(resultSet.getInt(1));
			tmp.setNombreDisco(resultSet.getString(2));
			tmp.setNombreArtista(resultSet.getString(3));
			tmp.setNumeroCanciones(resultSet.getInt(4));
			tmp.setPrecio(resultSet.getDouble(5));
			discos.add(tmp);
		}

		close();

		return discos;
	}

	// METODO PARA LISTAR UN DISCO POR EL ID
	public Discos encontrar(int idDisco) throws SQLException {

		Discos disco = null;

		connect();
		pstmt = conn.prepareStatement("SELECT d.id_disco, d.nombre_disco, d.id_artista, d.numero_canciones, d.precio  FROM discos d JOIN artistas a ON d.id_artista = a.id_artista WHERE d.id_disco = ?");
		pstmt.setInt(1, idDisco);
		resultSet = pstmt.executeQuery();

		while (resultSet.next()) {
			disco = new Discos();
			disco.setIdDisco(resultSet.getInt(1));
			disco.setNombreDisco(resultSet.getString(2));
			disco.setIdArtista(resultSet.getInt(3));
			disco.setNumeroCanciones(resultSet.getInt(4));
			disco.setPrecio(resultSet.getDouble(5));
		}
		
		close();
		
		return disco;
	}
}



