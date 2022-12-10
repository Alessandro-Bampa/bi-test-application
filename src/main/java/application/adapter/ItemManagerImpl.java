package application.adapter;

import application.port.ItemsManager;
import bean.Item;
import mapper.item.ItemMapper;
import model.ItemEntity;
import repository.port.mongo.ItemRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;


@ApplicationScoped
public class ItemManagerImpl implements ItemsManager {

    private static final Logger logger = Logger.getLogger(ItemManagerImpl.class);
    @Inject
    ItemRepository itemRepository;

    @Inject
    ItemMapper itemMapper;

    public List<Item> getItemList() {
        List<Item> itemList = new ArrayList<>();

        try {
            List<ItemEntity> itemEntities = itemRepository.getItemList();
            for(ItemEntity ie : itemEntities){
                Item item = itemMapper.mapEntityToBean(ie);
                itemList.add(item);
            }
        } catch (Exception e){
            logger.error("catching exception", e);
        }
        return  itemList;
    }

    @Override
    public String insertItem(Item itemBean){
        try {
            ItemEntity itemEntity = itemMapper.mapBeanToEntity(itemBean);
            return itemRepository.insertItem(itemEntity);
        } catch (Exception e) {
            logger.error("catching exception", e);
        }
        return "";
    }

}