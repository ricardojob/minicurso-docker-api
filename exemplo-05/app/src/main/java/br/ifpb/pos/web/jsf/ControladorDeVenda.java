package br.ifpb.pos.web.jsf;

import br.ifpb.pos.api.client.ConsumidorDeVendas;
import br.ifpb.pos.domain.venda.VendaValue;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 11:38:19
 */
@Named
@RequestScoped
public class ControladorDeVenda {

    @Inject
    private ConsumidorDeVendas consumidor;

    private VendaValue venda = new VendaValue();

    private String codigo;

    public String ler() {
        this.venda = consumidor.localizarPorCodigo(codigo);
        this.codigo = "";
        return null;
    }

    public VendaValue getVenda() {
        return venda;
    }

    public void setVenda(VendaValue venda) {
        this.venda = venda;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
