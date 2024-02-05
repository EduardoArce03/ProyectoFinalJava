package services;

import java.util.List;

import business.GestionCarrito;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.Carrito;
import modelo.Persona;
import modelo.Producto;

@Path("carrito")
public class CarritoServices {
	@Inject
	private GestionCarrito gestionCarrito;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("cliente")
	public Response obtenerCarritoPersona(Persona persona) {
		Carrito carrito = gestionCarrito.obtenerCarritoPersona(persona.getCorreo());
		System.out.println(persona.getCorreo());
		if (carrito != null) {
			
			
			return Response.ok(carrito).build();
		}else {
			ErrorMessage em = new ErrorMessage(88, "No se encuentra el cliente en el carrito");
			return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(em)
                    .build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getClientes(){
		List <Carrito> lista = gestionCarrito.getAll();
		if(lista.size()>0) {
			return Response.ok(lista).build();
		}
		ErrorMessage em = new ErrorMessage(6, "No se registran carritos");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(em)
				.build();
	}
	
	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("agregar-producto/{codigoCarrito}")
	public Response agregarProductoACarrito(Producto producto, @PathParam("codigoCarrito") int codigoCarrito) {
	    try {
	        Producto productoAgregado = gestionCarrito.agregarProductos(producto, codigoCarrito);

	        if (productoAgregado != null) {
	            return Response.ok(productoAgregado).build();
	        } else {
	            ErrorMessage em = new ErrorMessage(88, "No se encuentra el carrito");
	            return Response.status(Response.Status.NOT_FOUND).entity(em).build();
	        }
	    } catch (Exception e) {
	        ErrorMessage em = new ErrorMessage(500, "Error interno del servidor");
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(em).build();
	    }
	}*/

	
}