package com.br.resource;

import com.br.dto.CategoriaRequestDTO;
import com.br.dto.CategoriaResponseDTO;
import com.br.entity.Categoria;
import com.br.servico.CategoriaServico;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.ResponseObject;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Tag(name = "Categoria")
@Path("/categoria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaControlador {

    private CategoriaServico categoriaServico;

    public CategoriaControlador(CategoriaServico categoriaServico) {
        this.categoriaServico = categoriaServico;
    }

    @Operation(description = "Listar", operationId = "listarTodas")
    @GET
    public List<Categoria> listarTodas() {
        return categoriaServico.listarTodas();
    }

    @Operation(description = "Listar por código", operationId = "buscarPorId")
    @GET
    @Path("/{codigo}")
    public Response buscarPorId(@PathParam Long codigo) {
        Optional<Categoria> categoria = categoriaServico.buscarPorCodigo(codigo);
        return categoria.isPresent()
                ? Response.ok(categoria.get()).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @Operation(description = "Salvar", operationId = "salvarCategoria")
	@POST
	public Response salvar(@Valid CategoriaRequestDTO categoriaDto) {
		Categoria categoriaSalva = categoriaServico.salvar(categoriaDto.converterParaEntidade());
		return Response.status(Response.Status.CREATED).entity(CategoriaResponseDTO.converterParaCategoriaDTO(categoriaSalva)).build();
	}

    @Operation(description = "Atualizar", operationId = "atualizarCategoria")
    @PUT
	@Path("/{codigo}")
	public Response atualizar(@PathParam Long codigo, @Valid CategoriaRequestDTO categoriaDto) {
		Categoria categoriaAtualizada = categoriaServico.atualizar(codigo, categoriaDto.converterParaEntidade(codigo));
		return Response.ok(CategoriaResponseDTO.converterParaCategoriaDTO(categoriaAtualizada)).build();
	}

    @Operation(description = "Deletar", operationId = "deletarCategoria")
    @DELETE
	@Path("/{codigo}")
	public Response delete(@PathParam Long codigo) {
		categoriaServico.deletar(codigo);
		return Response.noContent().build();
	}
}
