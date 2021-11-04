package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Clientes;
import br.edu.ifpb.domain.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientesEmJDBC implements Clientes {

    private Connection connection;

    public ClientesEmJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                "jdbc:postgresql://host-banco:5432/clientes",
                "job","123"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }

    }

    @Override
    public void novo(Cliente cliente) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO clientes (cpf, nome) VALUES(?,?) "
            );
            statement.setString(1,cliente.getCpf());
            statement.setString(2,cliente.getNome());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public List<Cliente> todos() {
        try {
            List<Cliente> lista = new ArrayList<>();
            ResultSet result = connection.prepareStatement(
                    "SELECT * FROM clientes"
            ).executeQuery();
            while (result.next()) {
                lista.add(
                    criarCliente(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
//            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return Collections.EMPTY_LIST;
        }
    }

    private Cliente criarCliente(ResultSet result) throws SQLException {
        String nome = result.getString("nome");
        String cpf = result.getString("cpf");
        int id = result.getInt("id");
        return new Cliente(id,cpf,nome);
    }
}
