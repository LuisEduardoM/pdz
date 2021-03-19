package com.br.servico;

import com.br.entity.Produto;
import com.br.repositorio.ProdutoRepositorio;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoServico {

    private ProdutoRepositorio produtoRepositorio;
    private CategoriaServico categoriaServico;

    public ProdutoServico(ProdutoRepositorio produtoRepositorio, CategoriaServico categoriaServico) {
        this.produtoRepositorio = produtoRepositorio;
        this.categoriaServico = categoriaServico;
    }

    public List<Produto> listarTodos(Long codigoCategoria) {
        return produtoRepositorio.findByCategoriaCodigo(codigoCategoria);
    }

    public Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria) {
        return produtoRepositorio.buscarPorCodigo(codigo, codigoCategoria);
    }

//	public Produto salvar(Long codigoCategoria, Produto produto) {
//		validarCategoriaDoProdutoExiste(codigoCategoria);
//		validarProdutoDuplicado(produto);
//		return produtoRepositorio.save(produto);
//	}
//
//	public Produto atualizar(Long codigoCategoria, Long codigoProduto, Produto produto) {
//		Produto produtoSalvar = validarProdutoExiste(codigoProduto, codigoCategoria);
//		validarCategoriaDoProdutoExiste(codigoCategoria);
//		validarProdutoDuplicado(produto);
//		BeanUtils.copyProperties(produto, produtoSalvar, "codigo");
//		return produtoRepositorio.save(produtoSalvar);
//	}
//
//	public void deletar(Long codigoCategoria, Long codigoProduto) {
//		Produto produto = validarProdutoExiste(codigoProduto, codigoCategoria);
//		produtoRepositorio.delete(produto);
//	}
//
//	protected Produto validarProdutoExiste(Long codigoProduto) {
//		Optional<Produto> produto = produtoRepositorio.findById(codigoProduto);
//		if(produto.isEmpty()) {
//			throw new RegraNegocioException(String.format("Produto de código %s não encontrado", codigoProduto));
//		}
//		return produto.get();
//	}
//
//	private Produto validarProdutoExiste(Long codigoProduto, Long codigoCategoria) {
//		Optional<Produto> produto = buscarPorCodigo(codigoProduto, codigoCategoria);
//		if(produto.isEmpty()) {
//			throw new EmptyResultDataAccessException(1);
//		}
//		return produto.get();
//	}
//
//
//	private void validarProdutoDuplicado(Produto produto) {
//		Optional<Produto> produtoPorDescricao = produtoRepositorio.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao());
//		if(produtoPorDescricao.isPresent() && produtoPorDescricao.get().getCodigo() != produto.getCodigo()) {
//			throw new RegraNegocioException(String.format("O produto %s já está cadastrado", produto.getDescricao()));
//		}
//	}
//
//	private void validarCategoriaDoProdutoExiste(Long codigoCategoria) {
//		if(codigoCategoria == null) {
//			throw new RegraNegocioException("A categoria não pode ser nula");
//		}
//
//		if(categoriaServico.buscarPorCodigo(codigoCategoria).isEmpty()) {
//			throw new RegraNegocioException(String.format("A categoria de código %s informada não existe no cadastro", codigoCategoria));
//		}
//	}
}
