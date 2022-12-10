package application.adapter;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/api")
public class ItemService {

    @Inject
    MongoClient mongoClient;

    @Path("/ping")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "pong";
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MongoCollection getItems() {
        return this.getCollection();
    }

    private MongoCollection getCollection() {
        return mongoClient.getDatabase("bi_test_db").getCollection("items");
    }
}