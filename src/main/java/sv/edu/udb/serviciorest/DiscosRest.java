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

import sv.edu.udb.beans.DiscoConNombre;
import sv.edu.udb.beans.Discos;
import sv.edu.udb.model.ArtistasDAO;
import sv.edu.udb.model.DiscosDAO;

@Path("discos")
public class DiscosRest {

	DiscosDAO discosDAO = new DiscosDAO();
	ArtistasDAO artistasDAO = new ArtistasDAO();
	

////// METODO PARA LISTAR TODOS LOS DISCOS/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDiscos() throws SQLException {
		List<DiscoConNombre> discos = discosDAO.listar();
		return Response.status(200).entity(discos).build();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	

	
	
////// METODO PARA BUSCAR POR IDDISCO//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idDisco}")
	public Response getDiscosById(@PathParam("idDisco") int idDisco) throws SQLException {

		Discos disco = discosDAO.encontrar(idDisco);
		if (disco == null) {
			return Response.status(404).header("Acces-Control-Allow-Origin", "*").entity("404 Not Found: Disco no encontrado").build();
		}

		return Response.status(200).header("Acces-Control-Allow-Origin", "*").entity(disco).build();
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	
	
////// METODO PARA INSERTAR UN DISCO////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertarDisco(@FormParam("nombreDisco") String nombreDisco, @FormParam("idArtista") int idArtista, @FormParam("numeroCanciones") int numeroCanciones, @FormParam("precio") double precio) throws SQLException {

		if(artistasDAO.encontrar(idArtista)==null){
			 return Response.status(400).header("Access-Control-Allow-Origin", "*").entity("400 Bad Request: idArtista no corresponde a ninguna existencia").build();
			 }
		
		if (nombreDisco == null || nombreDisco.trim().isEmpty()) {
			return Response.status(400).header("Acces-Control-Allow-Origin", "*").entity("400 Bad Request: El nombre del disco no puede ser vacío.").build();
		}

		Discos disco = new Discos();

		disco.setNombreDisco(nombreDisco);
		disco.setIdArtista(idArtista);
		disco.setNumeroCanciones(numeroCanciones);
		disco.setPrecio(precio);
		discosDAO.insertar(disco);

		return Response.status(201).header("Acces-Control-Allow-Origin", "*").entity(disco).build();
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	

	
	
///////METODO PARA ACTUALIZAR UN DISCO////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idDisco}")
	public Response actualizarDisco(@PathParam("idDisco") int idDisco, @FormParam("nombreDisco") String nombreDisco, @FormParam("idArtista") int idArtista, @FormParam("numeroCanciones") int numeroCanciones, @FormParam("precio") double precio)throws SQLException{
				
		Discos disco = discosDAO.encontrar(idDisco);
		
		if(artistasDAO.encontrar(idArtista)==null){
			 return Response.status(400).header("Access-Control-Allow-Origin", "*").entity("400 Bad Request: idArtista no corresponde a ninguna existencia").build();
			 }
		
		if(disco == null) {
			return Response.status(404).header("Acces-Control-Allow-Origin", "*").entity("404 Not found").build();
		}
		
			disco.setNombreDisco(nombreDisco);
			disco.setIdArtista(idArtista);
			disco.setNumeroCanciones(numeroCanciones);
			disco.setPrecio(precio);
			discosDAO.actualizar(disco);
				
			return Response.status(204).header("Acces-Control-Allow-Origin", "*").entity(disco).build();
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	


	
	
//////METODO PARA ELIMINAR UN DISCO///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idDisco}")
	public Response eliminarDisco(@PathParam("idDisco") int idDisco) throws SQLException{
		Discos disco = discosDAO.encontrar(idDisco);
				
		if(disco == null) {
			return Response.status(404).header("Acces-Control-Allow-Origin", "*").entity("404 Not found").build();
		}
				
		discosDAO.eliminar(idDisco);
				
		return Response.status(204).header("Acces-Control-Allow-Origin", "*").entity(disco).build();
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


