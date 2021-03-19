package com.br.repositorio;

import com.br.entity.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoriaRepositorio implements PanacheRepository<Categoria> {

    public Categoria findByName(String nome) {
        return find("nome", nome).firstResult();
    }
}
