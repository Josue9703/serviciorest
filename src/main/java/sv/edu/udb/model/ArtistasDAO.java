package sv.edu.udb.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sv.edu.udb.beans.Artistas;
import sv.edu.udb.beans.Discos;

public class ArtistasDAO extends AppConnection{
	
	//METODO PARA INSERTAR UN ARTISTA
	public void insertar(Artistas artistas)  throws SQLException{
		connect();
		pstmt = conn.prepareStatement("INSERT INTO artistas(nombre_artista, descripcion) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS );
		pstmt.setString(1, artistas.getNombreArtista());
		pstmt.setString(2, artistas.getDescripcion());
		
		// EJECUTAMOS LA INSERCION
	    int filasAfectadas = pstmt.executeUpdate();

	    if (filasAfectadas > 0) {
	        // INSERCION EXITOSA OBTENDREMOS EL ID AUTOGENERADO
	        ResultSet keys = pstmt.getGeneratedKeys();
	        
	        if (keys.next()) {
	            int id = keys.getInt(1);
	            artistas.setIdArtista(id);
	        } else {
	            // SINO SE GENERA EL ID LANZAR MENSAJE
	            System.out.println("No se pudieron obtener las claves generadas.");
	        }
	    }

	    close();
	}
	
	//METODO PARA ACTUALIZAR UN ARTISTA
	public void actualizar(Artistas artistas)  throws SQLException{
		connect();
		pstmt = conn.prepareStatement("UPDATE artistas SET nombre_artista = ?, descripcion = ? WHERE id_artista = ?");
		pstmt.setString(1, artistas.getNombreArtista());
		pstmt.setString(2, artistas.getDescripcion());
		pstmt.setInt(3, artistas.getIdArtista());
		pstmt.executeUpdate();
		close();
	}
	
	//METODO PARA ELIMINAR UN ARTISTA
	public void eliminar(int idArtista) throws SQLException{
		connect();
		pstmt = conn.prepareStatement("DELETE FROM artistas WHERE id_artista = ?");
		pstmt.setInt(1, idArtista);
		pstmt.executeUpdate();
		close();
	}
	
	//METODO PARA LISTAR TODOS LOS ARTISTAS
	public ArrayList<Artistas> listar() throws SQLException{
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT id_artista, nombre_artista, descripcion FROM artistas");
		ArrayList<Artistas> artistas = new ArrayList();
		
		while (resultSet.next()) {
			Artistas tmp = new Artistas();
			tmp.setIdArtista(resultSet.getInt(1));
			tmp.setNombreArtista(resultSet.getString(2));
			tmp.setDescripcion(resultSet.getString(3));
			artistas.add(tmp);
		}
		
		close();
		
		return artistas;
	}
	
	//METODO PARA LISTAR UN ARTISTA POR EL ID
	public Artistas encontrar(int idArtista)throws SQLException{
		
		Artistas artista = null;
		
		connect();
		pstmt = conn.prepareStatement("SELECT id_artista, nombre_artista, descripcion FROM artistas WHERE id_artista = ?");
		pstmt.setInt(1, idArtista);
		resultSet = pstmt.executeQuery();
		
		while(resultSet.next()) {
			artista = new Artistas();
			artista.setIdArtista(resultSet.getInt(1));
			artista.setNombreArtista(resultSet.getString(2));
			artista.setDescripcion(resultSet.getString(3));
		}
		
		close();
		
		return artista;
	}
	
	
}




