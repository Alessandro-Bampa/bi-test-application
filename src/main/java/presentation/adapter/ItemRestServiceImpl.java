package presentation.adapter;

import application.port.ItemsManager;
import bean.Item;
import bean.request.ItemSearchRequest;
import bean.request.UpdateItemValueRequest;
import bean.response.UpdateItemValueResponse;
import presentation.port.ItemRestService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ItemRestServiceImpl implements ItemRestService {

    @Inject
    ItemsManager itemsManager;

    @Override
    public List<Item> getItemList() {
        return itemsManager.getItemList();
    }

    @Override
    public String insertItem(Item itemBean){
        return itemsManager.insertItem(itemBean);
    }

    @Override
    public Item getItem(String itemId) {
        return itemsManager.getItem(itemId);
    }

    @Override
    public UpdateItemValueResponse updateItemValue(UpdateItemValueRequest request) {
        return itemsManager.updateItemValue(request);
    }

    @Override
    public List<Item> searchItems(ItemSearchRequest search) {
        return itemsManager.searchItems(search);
    }

}
