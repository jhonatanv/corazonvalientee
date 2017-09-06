package co.org.smart.corazonvaliente.seguridad;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import co.org.smart.corazonvaliente.rest.LoginRest;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;

@Provider
@Secured
public class FiltroRest implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext ctxReq) throws IOException {
		String token=ctxReq.getHeaderString("Authorization");
		
		//TODO: revisar el usuario del token y el permiso de acceso al servicio
		if(!LoginRest.tokens.containsKey(token)){
			RespuestaDTO dto = new RespuestaDTO(null, "No Autorizado", "-3");
			Response res=Response.status(401).entity(dto).build();
			ctxReq.abortWith(res);
		}
	}

}
