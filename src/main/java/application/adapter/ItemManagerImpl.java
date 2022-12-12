package application.adapter;

import application.port.ItemsManager;
import bean.Item;
import bean.request.ItemSearchRequest;
import bean.request.UpdateItemValueRequest;
import bean.response.UpdateItemValueResponse;
import mapper.item.ItemMapper;
import model.ItemEntity;
import repository.port.mongo.ItemRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            for(ItemEntity ie : itemEntities) {
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

    @Override
    public Item getItem(String itemId){
        try {
            ItemEntity itemEntity = itemRepository.getItem(itemId);
            return itemMapper.mapEntityToBean(itemEntity);
        } catch (Exception e) {
            logger.error("catching exception", e);
        }
        return null;
    }

    @Override
    public UpdateItemValueResponse updateItemValue(UpdateItemValueRequest request) {
        UpdateItemValueResponse response = new UpdateItemValueResponse();
        try {
            Optional<ItemEntity> updatedEntity = itemRepository.updateItemValue(request);
            updatedEntity.orElseThrow(() -> new RuntimeException("error on update of items entity"));
            response.setUpdatedItem(itemMapper.mapEntityToBean(updatedEntity.get()));
        } catch (RuntimeException e) {
            logger.error("catching exception", e);
            response.setError(e.getMessage());
        }

        return response;
    }

    @Override
    public List<Item> searchItems(ItemSearchRequest search) {
        return itemRepository.searchItems(search).stream()
                .map(itemEntity -> itemMapper.mapEntityToBean(itemEntity)).collect(Collectors.toList());
    }
}