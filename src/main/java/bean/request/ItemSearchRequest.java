package bean.request;

import repository.port.enums.ComparisonFilters;
import repository.port.enums.Sorting;
import repository.port.enums.SortingFields;

import java.util.List;

public class ItemSearchRequest {

    private Integer value;

    private List<String> tags;

    private SortingFields orderedBy;

    private Sorting sortingType;

    private ComparisonFilters valueComparisonFilters;
    private int page;
    private int perPage;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public SortingFields getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(SortingFields orderedBy) {
        this.orderedBy = orderedBy;
    }

    public Sorting getSortingType() {
        return sortingType;
    }

    public void setSortingType(Sorting sortingType) {
        this.sortingType = sortingType;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public ComparisonFilters getValueComparisonFilters() {
        return valueComparisonFilters;
    }

    public void setValueComparisonFilters(ComparisonFilters valueComparisonFilters) {
        this.valueComparisonFilters = valueComparisonFilters;
    }
}
