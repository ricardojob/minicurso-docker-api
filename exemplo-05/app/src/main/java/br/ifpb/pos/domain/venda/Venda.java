package br.ifpb.pos.domain.venda;

import br.ifpb.pos.domain.cliente.Cliente;
import br.ifpb.pos.domain.produto.Produto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.ws.rs.core.UriInfo;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 10:55:34
 */
@Entity
public class Venda implements Serializable {

    @Id
    private String codigo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date criadaEm;
    private BigDecimal total;

    @ManyToOne
    private Cliente cliente;

    @OneToMany
    private List<Produto> produtos;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Venda() {
        this.criadaEm = Date.from(Instant.now());
        this.codigo = UUID.randomUUID().toString();
        this.status = Status.ANDAMENTO;
    }

    public void finalizar() {
        this.status = Status.FINALIZADA;
    }

    public void calcelar() {
        this.status = Status.CANCELADA;
    }

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public VendaValue parse(UriInfo uriInfo) {
        return new VendaValue(this,uriInfo);
    }
}
