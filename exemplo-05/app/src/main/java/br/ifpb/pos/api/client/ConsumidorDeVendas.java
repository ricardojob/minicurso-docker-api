package br.ifpb.pos.api.client;

import br.ifpb.pos.domain.venda.VendaValue;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 11:30:35
 */
@Stateless
public class ConsumidorDeVendas {

    private final String uri = "http://localhost:8080/app/api/vendas";

    private Client client = ClientBuilder.newClient();
    private WebTarget root = client.target(uri);

    public VendaValue localizarPorCodigo(String codigo) { // ebb3e8d1-1286-4701-917f-6758df5ccfac
        WebTarget targetVenda = this.root
            .path("{codigo}")
            .resolveTemplate("codigo",codigo);

        Response response = targetVenda.request()
            .get();

        return response.readEntity(VendaValue.class);
    }

}
