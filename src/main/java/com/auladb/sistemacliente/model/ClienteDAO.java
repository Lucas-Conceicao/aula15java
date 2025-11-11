package com.auladb.sistemacliente.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class ClienteDAO {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirCliente(Cliente cliente){
        String sql = "INSERT INTO Cliente(nome,cpf) VALUES(?,?)";
        Object[] obj = new Object[2];
        obj[0] = cliente.getNome();
        obj[1] = cliente.getCpf();
        jdbc.update(sql, obj);
    }

    public ArrayList<Cliente> listar(){
        String sql = "SELECT * FROM Cliente";
        List<Map<String,Object>> mapa = jdbc.queryForList(sql);
        return Conversao.converterClientes(mapa);
    }

    public Cliente obterCliente(int id){
        String sql = "SELECT * FROM Cliente where id=?";
        Map<String,Object> mapa = jdbc.queryForMap(sql, id);
        int idCliente = (Integer) mapa.get("id");
        String nome = (String) mapa.get("nome");
        String cpf = (String) mapa.get("cpf");
        Cliente cli = new Cliente(idCliente,nome,cpf);
        return cli;
    }

    public void atualizarCliente(int id, Cliente cliente){
        String sql = "UPDATE Cliente SET nome = ?, cpf = ? WHERE id = ?";
        Object[] obj = new Object[3];
        obj[0] = cliente.getNome();
        obj[1] = cliente.getCpf();
        obj[2] = cliente.getId();
        jdbc.update(sql, obj);
    }

    public void deletar(int id){
        String sql = "DELETE FROM Cliente WHERE id=?";
        jdbc.update(sql, id);
    }
}
