package br.edu.ifpb.domain;

import br.edu.ifpb.domain.Cliente;
import java.util.List;

public interface Clientes {

    void novo(Cliente cliente);

    List<Cliente> todos();

}
