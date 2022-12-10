package application.port;

import bean.Item;
import bean.response.ItemResponse;

import java.util.List;

public interface ItemsManager {

    List<Item> getItemList();

    String insertItem (Item item);
}
