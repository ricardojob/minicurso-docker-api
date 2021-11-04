package br.ifpb.pos.domain.venda;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 11:02:29
 */
public interface Vendas {

    public Venda nova();

    public List<Venda> todas();

    public Venda localizarCom(String uuid);

    public Venda atualizar(Venda venda);
    
    public Venda cancelar(Venda venda);
    
    public Venda finalizar(Venda venda);
    
}
