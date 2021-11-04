package br.ifpb.pos.application;

import br.ifpb.pos.domain.cliente.Cliente;
import br.ifpb.pos.domain.cliente.Clientes;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 08:26:38
 */
@Stateless
public class ServiceDeClientes {

    @Inject
    private Clientes clientes;

    public void criarNovoCliente(String cpf,String nome,String email) {
        // validando os parametros
        Objects.requireNonNull(cpf,"CPF precisa ser preenchido");
        //criando o objeto
        Cliente cliente = new Cliente(
            cpf,email,nome
        );
        //executando a lógica de negócio
        clientes.novo(cliente);
    }

    public List<Cliente> clientes() {
        return this.clientes.todos();
    }

    public Cliente localizaClientePorCPF(String cpf) {
        Cliente cliente = this.clientes.localizarPorCpf(cpf);

        if (cliente == null) {
            return Cliente.empty();
        }
        return cliente;
    }

    public void removerCliente(String cpf) {
        Objects.requireNonNull(cpf,"CPF precisa ser preenchido");
        Cliente cliente = localizaClientePorCPF(cpf);
        if (cliente.isEmpty()) {
            return;
        }
        this.clientes.remover(cliente);
    }

    public Cliente atualizar(String cpf,Cliente cliente) {
        Objects.requireNonNull(cpf,"CPF precisa ser preenchido");
        Cliente resposta = localizaClientePorCPF(cpf);
        
        if (resposta.isEmpty()) {
            return Cliente.empty();
        }
//        if (!cpf.equals(cliente.getCpf())) {
//            return Cliente.empty();
//        }
        cliente.setCpf(cpf);
        this.clientes.atualizar(cliente);
        // recurso atualizado
        return localizaClientePorCPF(cpf);
    }
}
