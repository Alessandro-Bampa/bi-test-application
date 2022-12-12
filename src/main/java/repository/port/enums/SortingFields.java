package repository.port.enums;

import java.util.Arrays;
import java.util.List;

public enum SortingFields {
    VALUE("value"),
    UPDATED("updated");

    private String value;

    SortingFields(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static SortingFields fromStringValue(String value){
        if (value==null){
            return null;
        }

        List<SortingFields> mConfigTypes= Arrays.asList(SortingFields.values());
        for (SortingFields field : mConfigTypes){
            if (field.getValue().equals(value)){
                return field;
            }
        }

        return null;
    }


}
