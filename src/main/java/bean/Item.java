package bean;

import java.util.Date;
import java.util.List;

public class Item {

    private String id;

    private int value;

    private List<String> tags;

    private Date created;

    private Date updated;

    public String getId() {
        return id;
    }

    public void setId(String itemId) {
        this.id = itemId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
