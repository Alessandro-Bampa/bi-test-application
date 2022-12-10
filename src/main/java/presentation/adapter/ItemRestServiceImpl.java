package presentation.adapter;

import application.port.ItemsManager;
import bean.Item;
import bean.response.ItemResponse;
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

}
