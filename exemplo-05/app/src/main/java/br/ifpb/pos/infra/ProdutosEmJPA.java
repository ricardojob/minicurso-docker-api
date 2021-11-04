package br.ifpb.pos.infra;

import br.ifpb.pos.domain.produto.Produtos;
import br.ifpb.pos.domain.produto.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 08:31:48
 */
@Stateless
public class ProdutosEmJPA implements Produtos {
    
     @PersistenceContext
    private EntityManager em;
    
     @Override
    public void novo(Produto produto){
        em.persist(produto);
    }

     @Override
    public Produto localizarPorDescricao(String descricao){
        return em.find(Produto.class,descricao);
    }
    
    @Override
    public Produto pesquisarPorCodigo(int codigo){
        return em.find(Produto.class, codigo);
    }

    @Override
    public List<Produto> todosProdutos() {
        return em.createQuery("FROM Produto p",Produto.class)
            .getResultList();
    }
    
    @Override
    public Produto atualizar(Produto produto) {
        return em.merge(produto);
    }

    @Override
    public void remover(Produto produto) {
        em.remove(produto);
    }
    
}
