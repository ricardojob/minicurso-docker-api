package br.edu.ifpb.web;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.Clientes;
import br.edu.ifpb.infra.ClientesEmMemoria;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Ricardo Job
 */
@WebServlet(name = "ControladorDeClientes", urlPatterns = {"/clientes"})
public class ControladorDeClientes extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    
   private Clientes service = new ClientesEmMemoria();

    // Listar todos os clientes
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorDeClientes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> ----Minicursos----Sertão Comp</h1>");
            listarClientes(out);
            out.println("</body>");
            out.println("</html>");
        }
    }

// Criar um novo Cliente
    // @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException {
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        Cliente cliente = new Cliente(cpf,nome);
        this.service.novo(cliente);
        response.sendRedirect("clientes");
    }

    private void listarClientes(final PrintWriter out) {
        this.service
            .todos()
            .forEach(c-> 
                out.println("<p>" + c.getNome() + "</p>")
            );
    }

}
