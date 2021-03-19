package com.br.resource;

import com.br.entity.Produto;
import com.br.servico.ProdutoServico;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Tag(name = "Produto")
@Path("/categoria/{codigoCategoria}/produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoControlador {

    private ProdutoServico produtoServico;

    public ProdutoControlador(ProdutoServico produtoServico) {
        this.produtoServico = produtoServico;
    }

    @Operation(description = "Listar", operationId = "listarTodos")
    @GET
    public List<Produto> listarTodos(@PathParam Long codigoCategoria) {
        return produtoServico.listarTodos(codigoCategoria);
    }

    @Operation(description = "Listar por código", operationId = "buscarPorCodigo")
    @GET
    @Path("/{codigo}")
    public Response buscarPorCodigo(@PathParam Long codigoCategoria, @PathParam Long codigo) {
        Optional<Produto> produto = produtoServico.buscarPorCodigo(codigo, codigoCategoria);
        return produto.isPresent() ? Response.ok(produto.get()).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

//    @ApiOperation(value = "Salvar", nickname = "salvarProduto")
//    @PostMapping
//    public ResponseEntity<ProdutoResponseDTO> salvar(@PathVariable Long codigoCategoria, @Valid @RequestBody ProdutoRequestDTO produto) {
//        Produto produtoSalvo = produtoServico.salvar(codigoCategoria, produto.converterParaEntidade(codigoCategoria));
//        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.converterParaProdutoDTO(produtoSalvo));
//    }
//
//    @ApiOperation(value = "Atualizar", nickname = "atualizarProduto")
//    @PutMapping("/{codigoProduto}")
//    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto,
//                                                        @Valid @RequestBody ProdutoRequestDTO produto) {
//        Produto produtoAtualizado = produtoServico.atualizar(codigoCategoria, codigoProduto, produto.converterParaEntidade(codigoCategoria, codigoProduto));
//        return ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produtoAtualizado));
//    }
//
//    @ApiOperation(value = "Deletar", nickname = "deletarProduto")
//    @DeleteMapping("/{codigoProduto}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deletar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto) {
//        produtoServico.deletar(codigoCategoria, codigoProduto);
//    }
}
