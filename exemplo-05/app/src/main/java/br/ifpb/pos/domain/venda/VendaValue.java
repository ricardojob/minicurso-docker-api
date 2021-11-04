package br.ifpb.pos.domain.venda;

import br.ifpb.pos.api.Link;
import br.ifpb.pos.api.ResoucesClientes;
import br.ifpb.pos.api.ResoucesProdutos;
import br.ifpb.pos.api.ResourceVendas;
import br.ifpb.pos.domain.cliente.Cliente;
import br.ifpb.pos.domain.produto.Produto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.UriInfo;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 10:52:57
 */
public class VendaValue {

    private String codigo;
    private Date criadaEm;
    private BigDecimal total;
    private Link cliente;
    private List<Link> produtos; // List<Link> produtos 

    private List<Link> acoes;
//    private Link cancelar;
//    private Link finalizar;

    public VendaValue() {
    }

    public VendaValue(Venda venda, UriInfo uriInfo) {
        this.codigo = venda.getCodigo();
        this.criadaEm = venda.getCriadaEm();
        this.total = venda.getTotal();
        this.cliente = criarCliente(venda,uriInfo);
        this.produtos = criarProdutos(venda,uriInfo);
        this.acoes = new ArrayList<>();
        this.acoes.add(criarAcaoDeCancelar(venda,uriInfo));
        this.acoes.add(criarAcaoDeFinalizar(venda,uriInfo));
    }

    private Link criarCliente(Venda venda,UriInfo uriInfo) {
        Cliente clienteRetono = venda.getCliente();
        String uri = uriInfo.getBaseUriBuilder() // ../api/
            .path(ResoucesClientes.class) // ../api/clientes
            .path(clienteRetono.getCpf()) // ../api/clientes/123
            .build()
            .toString();
        return new Link(uri,clienteRetono.getNome());
    }

    private List<Link> criarProdutos(Venda venda,final UriInfo uriInfo) {
        return venda.getProdutos()
            .stream()
            .map(produto -> criarProduto(produto,uriInfo))
            .collect(Collectors.toList());
    }

    private Link criarProduto(Produto produto,UriInfo uriInfo) {
        String uri = uriInfo.getBaseUriBuilder() // ../api/
            .path(ResoucesProdutos.class) // ../api/produtos
            .path(// ../api/produtos/123
                String.valueOf(
                    produto.getCodigo()
                )
            ).build()
            .toString();
        return new Link(uri,produto.getDescricao());
    }

    private Link criarAcaoDeCancelar(Venda venda,UriInfo uriInfo) {
        String uri = uriInfo.getBaseUriBuilder() // ../api/
            .path(ResourceVendas.class) // ../api/vendas
            .path(venda.getCodigo())// ../api/vendas/123
            .build()
            .toString();
        return new Link(uri,"cancelar");
    }

    private Link criarAcaoDeFinalizar(Venda venda,UriInfo uriInfo) {
        String uri = uriInfo.getBaseUriBuilder() // ../api/
            .path(ResourceVendas.class) // ../api/vendas
            .path(venda.getCodigo())// ../api/vendas/123
            .path("finalizar")// ../api/vendas/123/finalizar
            .build()
            .toString();
        return new Link(uri,"finalizar");
    }

    public List<Link> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<Link> acoes) {
        this.acoes = acoes;
    }

//    public Link getFinalizar() {
//        return finalizar;
//    }
//
//    public void setFinalizar(Link finalizar) {
//        this.finalizar = finalizar;
//    }
//
//    public Link getCancelar() {
//        return cancelar;
//    }
//
//    public void setCancelar(Link cancelar) {
//        this.cancelar = cancelar;
//    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(Date criadaEm) {
        this.criadaEm = criadaEm;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Link getCliente() {
        return cliente;
    }

    public void setCliente(Link cliente) {
        this.cliente = cliente;
    }

    public List<Link> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Link> produtos) {
        this.produtos = produtos;
    }

}
