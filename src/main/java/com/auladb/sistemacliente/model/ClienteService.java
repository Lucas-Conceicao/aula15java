package com.auladb.sistemacliente.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteDAO cdao;

    public void inserirCliente(Cliente cliente){
        cdao.inserirCliente(cliente);
    }

    public void atualizarCliente(int id, Cliente cliente){
        cdao.atualizarCliente(id,cliente);
    }

    public ArrayList<Cliente> listarClientes(){
        return cdao.listar();
    }

    public Cliente obterCliente(int id){
        return cdao.obterCliente(id);
    }

    public void deletar(int id){
        cdao.deletar(id);
    }

}
