package com.auladb.sistemacliente;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.auladb.sistemacliente.model.Cliente;
import com.auladb.sistemacliente.model.ClienteService;

@Controller
public class MainController {

    @Autowired
    private ApplicationContext context;

    public String index(){
        return "index";
    }

    @GetMapping("/cliente")
    public String cliente(Model model){
        model.addAttribute("cli", new Cliente());
        return "formCadastro";
    }

    @PostMapping("/cliente")
    public String cliente(Model model, @ModelAttribute Cliente cli){
        ClienteService cs = context.getBean(ClienteService.class);
        cs.inserirCliente(cli);
        return "sucesso";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        ClienteService cs = context.getBean(ClienteService.class);
        ArrayList<Cliente> clientes = cs.listarClientes();
        model.addAttribute("clientes", clientes);
        return "listarCliente";
    }

    @GetMapping("/upd/cliente/{id}")
    public String clienteUpd(Model model, @PathVariable int id){
        ClienteService cs = context.getBean(ClienteService.class);
        Cliente cli = cs.obterCliente(id);
        model.addAttribute("cli", cli);
        model.addAttribute("id", id);
        return "formCadastroUpd";
    }

    @PostMapping("/upd/cliente/{id}")
    public String clienteUpd(Model model, 
                            @ModelAttribute Cliente cli, 
                            @PathVariable int id){
        ClienteService cs = context.getBean(ClienteService.class);
        cs.atualizarCliente(id, cli);       
        return "redirect:/listar";
    }

    @PostMapping("/del/cliente/{id}")
    public String deletar(@PathVariable int id){
        ClienteService cs = context.getBean(ClienteService.class);
        cs.deletar(id);
        return "redirect:/listar";
    }
}
