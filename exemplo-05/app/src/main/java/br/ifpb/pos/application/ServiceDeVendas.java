package br.ifpb.pos.application;

import br.ifpb.pos.domain.cliente.Cliente;
import br.ifpb.pos.domain.cliente.Clientes;
import br.ifpb.pos.domain.produto.Produto;
import br.ifpb.pos.domain.produto.Produtos;
import br.ifpb.pos.domain.venda.Venda;
import br.ifpb.pos.domain.venda.Vendas;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 11:05:18
 */
@Stateless
public class ServiceDeVendas {

    @Inject
    private Vendas vendas;

    @Inject
    private Clientes clientes;

    @Inject
    private Produtos produtos;

    public Venda novaVenda() {
        return this.vendas.nova();
    }

    public List<Venda> vendas() {
        return this.vendas.todas();
    }

    public Venda localizaVendaPorUUID(String uuid) {
        return this.vendas.localizarCom(uuid);
    }

    public Venda adicionarClienteAVenda(String uuid,String cpf) {
        Venda venda = this.vendas.localizarCom(uuid);
        Cliente cliente = this.clientes.localizarPorCpf(cpf);
        venda.setCliente(cliente);
        return this.vendas.atualizar(venda);
    }

    public void cancelarVenda(String uuid) {
        Objects.requireNonNull(uuid,"Venda inexistente");
        Venda venda = this.vendas.localizarCom(uuid);
        venda.calcelar();
        this.vendas.atualizar(venda);
    }

    public Venda adicionarProdutoAVenda(String uuid,double preco) {
        Venda venda = this.vendas.localizarCom(uuid);
        return venda;
    }

    public Venda adicionarProdutoAVenda(String uuid,int codigo) {
        Venda venda = this.vendas.localizarCom(uuid);
        Produto p = produtos.pesquisarPorCodigo(codigo);
        venda.setProdutos(
            this.adicionarProdutoLista(venda,p)
        );
        return this.vendas.atualizar(venda);
    }

    public List<Produto> adicionarProdutoLista(Venda venda,Produto produto) {
        List<Produto> listaProdutos = venda.getProdutos();
        listaProdutos.add(produto);
        return listaProdutos;
    }

//    public Venda cancelarVenda(String uuid) {
//        Venda venda = this.vendas.localizarCom(uuid);
//        return this.vendas.cancelar(venda);
//    }
    public Venda finalizarVenda(String uuid) {
        Venda venda = this.vendas.localizarCom(uuid);
        return this.vendas.finalizar(venda);
    }

}
