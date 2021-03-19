package com.br.repositorio;

import com.br.entity.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoRepositorio implements PanacheRepository<Produto> {

    public List<Produto> findByCategoriaCodigo(Long codigoCategoria) {
        return find("Select prod" +
                " from Produto prod" +
                " where prod.cateogoria.codigo = ?", codigoCategoria).list();
    }

    public Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria) {
        return find("Select prod"
                + " from Produto prod"
                + " where prod.codigo = :codigo"
                + "   and prod.categoria.codigo = :codigoCategoria", codigo, codigoCategoria).firstResultOptional();
    }

    public Optional<Produto> findByCategoriaCodigoAndDescricao(Long codigoCategoria, String descricao) {
        return find("select prod from Produto where prod.categoria.codigo = :codigoCategoria and prod.descricao", codigoCategoria, descricao).firstResultOptional();
    }
}
