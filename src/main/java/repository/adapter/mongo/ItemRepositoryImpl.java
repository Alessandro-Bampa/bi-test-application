package repository.adapter.mongo;

import bean.request.ItemSearchRequest;
import bean.request.UpdateItemValueRequest;
import com.mongodb.client.MongoClient;
import model.ItemEntity;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import repository.port.CollectionNames;
import repository.port.mongo.ItemRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

@ApplicationScoped
public class ItemRepositoryImpl extends AbstractRepositoryMongo<ItemEntity> implements ItemRepository {
    @Inject
    MongoClient mongoClient;

    private static final String _ID = "_id";
    private static final String UPDATED_FIELD = "updated";
    private static final String VALUE_FIELD = "value";
    private static final String TAGS_FIELD = "tags";
    private static final String FAKE_FIELD = "fakeField";





    @Override
    public String collectionName() {
        return CollectionNames.itemsCollection;
    }

    @Override
    public String insertItem(ItemEntity item) {
        item.setCreated(new Date());
        item.setUpdated(new Date());
        return getCollection().insertOne(item).getInsertedId().asObjectId().getValue().toString();
    }

    @Override
    public ItemEntity getItem(String itemId) {
        return fetch(eq(new ObjectId(itemId))).get();
    }

    @Override
    public Optional<ItemEntity> updateItemValue(UpdateItemValueRequest request) {
        Bson filter = eq(_ID, new ObjectId(request.getId()));
        Bson update = combine(
                set(UPDATED_FIELD, new Date()),
                set(VALUE_FIELD, request.getNewValue())
        );
        return updateOne(filter,update);
    }


    @Override
    public List<ItemEntity> searchItems(ItemSearchRequest search) {
        Bson filter;

        //not mandatory filters always true
        Bson valueFilter = eq(FAKE_FIELD, null);
        Bson tagsFilter = eq(FAKE_FIELD, null);

        if(search.getValue() != null && search.getValueComparisonFilters() != null){
            switch (search.getValueComparisonFilters()){
                case EQ:
                    valueFilter = eq(VALUE_FIELD,search.getValue());
                    break;
                case GT:
                    valueFilter = gt(VALUE_FIELD,search.getValue());
                    break;
                case LT:
                    valueFilter = lt(VALUE_FIELD,search.getValue());
                    break;
                case LTE:
                    valueFilter = lte(VALUE_FIELD,search.getValue());
                    break;
                case GTE:
                    valueFilter = gte(VALUE_FIELD,search.getValue());
                    break;
            }
        }

        //matches documents if the array field contains every element specified in the query.
        if(search.getTags() != null && !search.getTags().isEmpty()) {
            tagsFilter = all(TAGS_FIELD, search.getTags());
        }

        filter = and(valueFilter,tagsFilter);

        return pageFetchListSorted(filter,search.getPage(), search.getPerPage(), search.getSortingType(), search.getOrderedBy().getValue());
    }



}
