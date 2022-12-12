package bean.response;

import bean.Item;
import java.util.List;

public class UpdateItemValueResponse {
    private Item updatedItem;

    private String error;

    public Item getUpdatedItem() {
        return updatedItem;
    }

    public void setUpdatedItem(Item updatedItem) {
        this.updatedItem = updatedItem;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
