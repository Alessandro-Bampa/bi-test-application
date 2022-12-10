package repository.port.mongo;

import model.ItemEntity;

import java.util.List;

public interface ItemRepository {

    List<ItemEntity> getItemList();

    String insertItem(ItemEntity item);
}
