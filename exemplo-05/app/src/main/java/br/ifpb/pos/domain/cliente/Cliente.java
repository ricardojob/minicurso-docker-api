package br.ifpb.pos.domain.cliente;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/11/2021, 08:19:31
 */
@Entity
@XmlRootElement
public class Cliente implements Serializable {

    @Id
    private String cpf;
    private String email;
    private String nome;

    public Cliente() {
    }

    public Cliente(String cpf,String email,String nome) {
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
    }

    public static Cliente empty() {
        return new Cliente("cpf","email","nome");
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isEmpty() {
        return this.equals(
            Cliente.empty()
        );
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.cpf);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.cpf,other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.email,other.email)) {
            return false;
        }
        if (!Objects.equals(this.nome,other.nome)) {
            return false;
        }
        return true;
    }
    

}
