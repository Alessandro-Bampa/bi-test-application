package mapper.item;

import bean.Item;
import model.ItemEntity;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemMapper {
    
    public ItemEntity mapBeanToEntity(Item bean){
           ItemEntity entity = new ItemEntity();
           entity.setValue(bean.getValue());
           entity.setCreated(bean.getCreated());
           entity.setUpdated(bean.getUpdated());
           entity.setTags(bean.getTags());
           return entity;
    }

    public Item mapEntityToBean(ItemEntity entity){
        Item bean = new Item();
        bean.setId(entity.getId().toString());
        bean.setValue(entity.getValue());
        bean.setCreated(entity.getCreated());
        bean.setUpdated(entity.getUpdated());
        bean.setTags(entity.getTags());
        return  bean;
    }
}
