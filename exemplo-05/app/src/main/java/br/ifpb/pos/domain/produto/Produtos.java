package br.ifpb.pos.domain.produto;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 08:31:48
 */
public interface Produtos {
    
    public void novo(Produto produto);

    public Produto localizarPorDescricao(String descricao);

    public List<Produto> todosProdutos();
    
    public Produto pesquisarPorCodigo(int codigo);
    
    public Produto atualizar(Produto produto);
    
    public void remover(Produto p);
    
}
