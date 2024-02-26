package sv.edu.udb.serviciorest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sv.edu.udb.beans.Artistas;
import sv.edu.udb.model.ArtistasDAO;

	@Path("artistas")
	public class ArtistasRest {
		ArtistasDAO artistasDAO = new ArtistasDAO();
	
/////////// METODO PARA LISTAR TODOS LOS ARTISTAS/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getArtistas() throws SQLException {
	
			List<Artistas> artistas = artistasDAO.listar();
			return Response.status(200).entity(artistas).build();
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
///////////METODO PARA BUSCAR POR IDARTISTA////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("{idArtista}")
		public Response getArtistasById(@PathParam("idArtista") int idArtista) throws SQLException{
			
			Artistas artista = artistasDAO.encontrar(idArtista);
			if(artista == null) {
				return Response.status(404).header("Acces-Control-Allow-Origin", "*").entity("404 Not Found: Artista no encontrado").build();
			}
			
			return Response.status(200).header("Acces-Control-Allow-Origin", "*").entity(artista).build();
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

		
		
//////////METODO PARA BUSCAR TODOS LOS DISCOS QUE TENGA UN ARTISTA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

		
		
///////////METODO PARA INSERTAR UN ARTISTA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		public Response insertarArtista(@FormParam("nombreArtista") String nombreArtista, @FormParam("descripcion") String descripcion) throws SQLException{
			
		    if (nombreArtista == null || nombreArtista.trim().isEmpty()) {
		        return Response.status(400).header("Acces-Control-Allow-Origin", "*").entity("400 Bad Request: El nombre del artista no puede ser vacío.").build();
		    }
			
			Artistas artista = new Artistas();
			
			artista.setNombreArtista(nombreArtista);
			artista.setDescripcion(descripcion);
			artistasDAO.insertar(artista);
			
			return Response.status(201).header("Acces-Control-Allow-Origin", "*").entity(artista).build();
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

		
		
///////////METODO PARA ACTUALIZAR ARTISTA///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Path("{idArtista}")
		public Response actualizarArtista(@PathParam("idArtista") int idArtista, @FormParam("nombreArtista") String nombreArtista, @FormParam("descripcion") String descripcion)throws SQLException{
			
			Artistas artista = artistasDAO.encontrar(idArtista);
			
			if(artista == null) {
				return Response.status(404).header("Acces-Control-Allow-Origin", "*").entity("404 Not found").build();
			}
			artista.setNombreArtista(nombreArtista);
			artista.setDescripcion(descripcion);
			artistasDAO.actualizar(artista);
			
			return Response.status(204).header("Acces-Control-Allow-Origin", "*").entity(artista).build();
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		
		
///////////METODO PARA ELIMINAR UN ARTISTA//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		@DELETE
		@Produces(MediaType.APPLICATION_JSON)
		@Path("{idArtista}")
		public Response eliminarArtista(@PathParam("idArtista") int idArtista) throws SQLException{
			Artistas artista = artistasDAO.encontrar(idArtista);
			
			if(artista == null) {
				return Response.status(404).header("Acces-Control-Allow-Origin", "*").entity("404 Not found").build();
			}
			
			artistasDAO.eliminar(idArtista);
			
			return Response.status(204).header("Acces-Control-Allow-Origin", "*").entity(artista).build();
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
