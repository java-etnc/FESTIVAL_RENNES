package models.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModelsUtils {
    private ModelsUtils() {
    }
    public static <T> List<T> CollectionToList(Collection<T> collection) {
        return new ArrayList<>(collection);
    }
}
