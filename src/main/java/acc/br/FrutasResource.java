package acc.br;

import acc.br.model.CriarNovaFrutaDTO;
import acc.br.model.Fruta;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/frutas")
public class FrutasResource {
  private static final Logger logger = Logger.getLogger(FrutasResource.class);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Fruta> list() {
    return Fruta.listAll();
  }

  @POST
  @Transactional
  public void novaFruta(CriarNovaFrutaDTO data) {
    Fruta fruta = new Fruta();
    fruta.nome = data.nome();
    fruta.qtd = data.qtd();
    fruta.persist();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public void deletarFruta(@PathParam("id") int id) {
    boolean isSuccessful = Fruta.deleteById(id);
    if (isSuccessful) {
      logger.info("Fruta " + id + " deletada com sucesso.");
    } else {
      logger.error("Ocorreu um erro ao deletar a fruta " + id);
    }
  }
}
