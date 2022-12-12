package repository.adapter.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import repository.port.CollectionNames;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jboss.logging.Logger;
import repository.port.enums.Sorting;

import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public abstract class AbstractRepositoryMongo<T> {

    private static final Logger log = Logger.getLogger(AbstractRepositoryMongo.class);
    @Inject
    MongoClient mongoClient;
    protected static String defaultCollection = CollectionNames.defaultCollection;
    private final Class<T> entityType;

    private static final String DATABASE = "bi_test_db";

    public AbstractRepositoryMongo() {
        Class clazz = getClass();
        while (!clazz.getSuperclass().getName().equals(AbstractRepositoryMongo.class.getName())) {
            clazz = clazz.getSuperclass();
        }
        entityType = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public String collectionName() {
        return defaultCollection;
    }

    protected Class<T> getEntityType() {
        return entityType;
    }

    public MongoCollection<T> getCollection() {
        return mongoClient.getDatabase(DATABASE).getCollection(collectionName(), getEntityType());
    }

    public Optional<T> fetch(Bson filter) {
        return Optional.ofNullable(getCollection().find(filter, getEntityType()).first());
    }

    public Optional<T> updateOne(Bson filter, Bson update) {
        UpdateResult result =  getCollection().updateOne(filter, update);
        if (result.getModifiedCount() == 0 && result.getMatchedCount() > 0){
            log.warn("unexpected unmodified entity");
        }
        return fetch(filter);
    }

    public List<T> pageFetchListSorted(Bson filter, int page, int perPage, Sorting sortOrder, String sortField) {
        FindIterable<T> ts;
        if (Sorting.ASCENDING.equals(sortOrder)) {
            ts = getCollection().find(filter, getEntityType()).sort(ascending(sortField)).skip(page * perPage).limit(perPage);
        } else {
            ts = getCollection().find(filter, getEntityType()).sort(descending(sortField)).skip(page * perPage).limit(perPage);
        }
        List<T> res = new ArrayList<>();

        for (T t : ts) {
            res.add(t);
        }

        return res;
    }
}
