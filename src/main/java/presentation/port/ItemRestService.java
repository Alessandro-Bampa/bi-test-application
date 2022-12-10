package presentation.port;

import bean.Item;
import bean.response.ItemResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("item")
public interface ItemRestService {

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Item> getItemList();

    @Path("insert")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    String insertItem(Item itemBean);

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Item getItem(@PathParam("id") String id);

}
