package com.auladb.sistemacliente.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Conversao {

    public static ArrayList<Cliente> converterClientes(List<Map<String,Object>> mapa){
        ArrayList<Cliente> clientes = new ArrayList<>();
        for(Map<String,Object> registro : mapa){
            int id = (Integer) registro.get("id");
            String nome = (String) registro.get("nome");
            String cpf = (String) registro.get("cpf");
            clientes.add(new Cliente(id,nome,cpf));
        }
        return clientes;
    }

}
