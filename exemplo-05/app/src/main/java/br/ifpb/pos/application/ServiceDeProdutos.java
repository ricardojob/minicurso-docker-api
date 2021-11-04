/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.pos.application;

import br.ifpb.pos.domain.produto.Produto;
import br.ifpb.pos.domain.produto.Produtos;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 08:31:48
 */
@Stateless
public class ServiceDeProdutos {
    
    @Inject
    private Produtos produtos;
    
    public void criarNovoProduto(int codigo, String descricao, double valor) {
        Objects.requireNonNull(descricao,"Descrição precisa ser preenchido");
        Produto p = new Produto(
                codigo, descricao, valor
        );
        produtos.novo(p);
    }

    public List<Produto> todos() {
        return this.produtos.todosProdutos();
    }
    
    public Produto pesquisarPorcodigo(int codigo) {
        return this.produtos.pesquisarPorCodigo(codigo);
    }

    public Produto localizaPorDescricao(String descricao) {
        return this.produtos.localizarPorDescricao(descricao);
    }
    
    public Produto atualizarProduto(Produto produto) {
        return this.produtos.atualizar(produto);
    }
    
    public void removerProduto(Produto produto) {
        this.produtos.remover(produto);
    }
    
}
