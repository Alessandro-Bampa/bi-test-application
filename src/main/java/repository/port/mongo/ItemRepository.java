package repository.port.mongo;

import bean.request.ItemSearchRequest;
import bean.request.UpdateItemValueRequest;
import model.ItemEntity;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    String collectionName();

    String insertItem(ItemEntity item);

    ItemEntity getItem(String itemId);

    Optional<ItemEntity> updateItemValue(UpdateItemValueRequest request);

    List<ItemEntity> searchItems(ItemSearchRequest search);
}
