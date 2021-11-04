package br.ifpb.pos.infra;

import br.ifpb.pos.domain.venda.Status;
import br.ifpb.pos.domain.venda.Venda;
import br.ifpb.pos.domain.venda.Vendas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 11:03:12
 */
@Stateless
public class VendasEmJPA implements Vendas {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Venda nova() {
        Venda venda = new Venda();
        em.persist(venda);
        return venda;
    }

    @Override
    public List<Venda> todas() {
        return em.createQuery("FROM Venda v WHERE v.status<>:status",Venda.class)
            .setParameter("status",Status.CANCELADA)
            .getResultList();
    }

    @Override
    public Venda localizarCom(String uuid) {
        return em.find(Venda.class,uuid);
    }

    @Override
    public Venda atualizar(Venda venda) {
        return em.merge(venda);
    }

    @Override
    public Venda cancelar(Venda venda) {
        venda.setStatus(Status.CANCELADA);
        return em.merge(venda);
    }
    
    @Override
    public Venda finalizar(Venda venda) {
        venda.setStatus(Status.FINALIZADA);
        return em.merge(venda);
    }

}
