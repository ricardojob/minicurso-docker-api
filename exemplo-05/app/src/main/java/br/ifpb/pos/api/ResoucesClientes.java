package br.ifpb.pos.api;

import br.ifpb.pos.application.ServiceDeClientes;
import br.ifpb.pos.domain.cliente.Cliente;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 08:31:48
 */
@Stateless
// localhos:8080/app/api/clientes
@Path("clientes")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class ResoucesClientes {

    @Inject
    private ServiceDeClientes service;

    @Context
    private UriInfo uriInfo;

    @GET
    public Response todosOsClientes() {
        List<Cliente> clientes = this.service.clientes();

        GenericEntity<List<Cliente>> entity = new GenericEntity<List<Cliente>>(
            clientes) {
        };
        return Response.ok()
            .entity(entity)
            .build();
    }

    // localhos:8080/app/api/clientes/123
    @GET
    @Path("{cpf}")
    public Response clienteCPF(@PathParam("cpf") String cpf) {
//        Cliente cliente = new Cliente("123","eamil@com","kiko");
        Cliente cliente = this.service.localizaClientePorCPF(cpf);
        return Response.ok()
            .entity(cliente)
            .build();
    }

    @POST
    public Response novo(JsonObject object) {

        this.service.criarNovoCliente(
            object.getString("cpf"),
            object.getString("email"),
            object.getString("nome")
        );
//        String path = "http://localhost:8080/app/api/clientes/" + object.getString("cpf");
//        URI uri = URI.create(path);
        URI uri = uriInfo.getAbsolutePathBuilder()
            .path(object.getString("cpf"))
            .build();

        return Response.created(uri)
            .build();
    }

    // localhos:8080/app/api/clientes/123 -> DELETE
    @DELETE
    @Path("{cpf}")
    public Response remover(@PathParam("cpf") String cpf) {
        this.service.removerCliente(cpf);
        return Response.noContent() // 204 
            .build();
    }

    // localhos:8080/app/api/clientes/123 -> PUT
    @PUT
    @Path("{cpf}")
    public Response atualizar(@PathParam("cpf") String cpf,Cliente cliente) {

        Cliente resposta = this.service.atualizar(cpf,cliente);
        if (resposta.isEmpty()) {
            return Response.notModified()
                .build();
        } else {
            return Response.ok()
                .entity(resposta)
                .build();
        }
    }
}
