package repository.adapter.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import model.ItemEntity;
import repository.port.mongo.ItemRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class ItemRepositoryImpl implements ItemRepository {
    @Inject
    MongoClient mongoClient;

    private static final String VALUE = "value";
    private static final String ITEMS = "items";
    private static final String DATABASE = "bi_test_db";

    @Override
    public List<ItemEntity> getItemList() {
        List<ItemEntity> itemList = new ArrayList<>();
        FindIterable<ItemEntity> itemCollection = getCollection().find();
            for(ItemEntity item : itemCollection) {
                itemList.add(item);
            }
          return itemList;
    }

    private MongoCollection getCollection() {
        return mongoClient.getDatabase(DATABASE).getCollection(ITEMS, ItemEntity.class);
    }

    @Override
    public String insertItem(ItemEntity item) {
        item.setCreated(new Date());
        item.setUpdated(new Date());
        return getCollection().insertOne(item).getInsertedId().asObjectId().getValue().toString();
    }

}
