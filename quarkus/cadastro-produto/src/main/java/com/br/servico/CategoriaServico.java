package com.br.servico;

import com.br.entity.Categoria;
import com.br.repositorio.CategoriaRepositorio;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CategoriaServico {

    private CategoriaRepositorio categoriaRepositorio;

    public CategoriaServico(CategoriaRepositorio categoriaRepositorio) {
        this.categoriaRepositorio = categoriaRepositorio;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepositorio.listAll();
    }

    public Optional<Categoria> buscarPorCodigo(Long codigo) {
        return categoriaRepositorio.findByIdOptional(codigo);
    }

    @Transactional
	public Categoria salvar(Categoria categoria) {
		categoriaRepositorio.persist(categoria);
		return categoria;
	}

    @Transactional
	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaSalvar = validarCategoriaExiste(codigo);
		categoriaSalvar.setNome(categoria.getNome());
		categoriaRepositorio.persist(categoriaSalvar);
		return categoriaSalvar;
	}

    @Transactional
	public void deletar(Long codigo) {
		categoriaRepositorio.deleteById(codigo);
	}

	private Categoria validarCategoriaExiste(Long codigo) {
		Optional<Categoria> categoria = buscarPorCodigo(codigo);
		if (categoria.isEmpty()) {
            throw new NotFoundException("Categoria não existe");
		}
		return categoria.get();
	}
//
//	private void validarCategoriaDuplicada(Categoria categoria) {
//		Categoria categoriaEncontrada = categoriaRepositorio.findByNome(categoria.getNome());
//		if (categoriaEncontrada != null && categoriaEncontrada.getCodigo() != categoria.getCodigo()) {
//			throw new RegraNegocioException(
//					String.format("A categoria %s já esta cadastrada", categoria.getNome().toUpperCase()));
//		}
//	}
}
