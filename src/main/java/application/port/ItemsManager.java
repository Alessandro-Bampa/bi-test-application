package application.port;

import bean.Item;
import bean.request.ItemSearchRequest;
import bean.request.UpdateItemValueRequest;
import bean.response.UpdateItemValueResponse;
import model.ItemEntity;

import java.util.List;

public interface ItemsManager {

    List<Item> getItemList();

    String insertItem (Item item);

    Item getItem(String itemId);

    UpdateItemValueResponse updateItemValue(UpdateItemValueRequest request);

    List<Item> searchItems(ItemSearchRequest search);
}
