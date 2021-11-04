package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.Clientes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClientesEmMemoria implements Clientes {

    private List<Cliente> clientes = new ArrayList<>();

    public ClientesEmMemoria() {
        this.clientes.addAll(
            Arrays.asList(
                new Cliente(1,"123","Maria"),
                // new Cliente(2,"124","João"),
                new Cliente(3,"127","Ana"),
                // new Cliente(4,"128","José"),
                // new Cliente(5,"129","Job"),
                // new Cliente(6,"120","Will"),
                new Cliente(7,"122","Mey")
            )
        );
    }

    @Override
    public void novo(Cliente cliente) {
        this.clientes.add(cliente);
    }

    @Override
    public List<Cliente> todos() {
        return Collections
            .unmodifiableList(
                this.clientes
            );
    }
}
