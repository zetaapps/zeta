package zeta.apps.flickr.models.common;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Transformers {

    public static <F, T> List<T> transformList(@Nullable List<F> fromList,
                                               ITransformer<F, T> transformer) {
        return transformList(fromList, null, transformer, null);
    }

    public static <F, T> List<T> transformList(@Nullable List<F> fromList,
                                               Predicate<F> fromTypeInclusionFilter,
                                               ITransformer<F, T> transformer) {
        return transformList(fromList, fromTypeInclusionFilter, transformer, null);
    }

    public static <F, T> List<T> transformList(@Nullable List<F> fromList,
                                               ITransformer<F, T> transformer,
                                               Predicate<T> toTypeInclusionFilter) {
        return transformList(fromList, null, transformer, toTypeInclusionFilter);
    }

    public static <F, T> List<T> transformList(@Nullable List<F> fromList,
                                               @Nullable Predicate<F> fromTypeInclusionFilter,
                                               ITransformer<F, T> transformer,
                                               @Nullable Predicate<T> toTypeInclusionFilter) {
        if (fromList == null || fromList.isEmpty()) {
            return Collections.emptyList();
        }

        List<T> toList = new ArrayList<>(fromList.size());
        for (int i = 0; i < fromList.size(); i++) {
            F fromItem = fromList.get(i);

            if (fromTypeInclusionFilter != null && !fromTypeInclusionFilter.test(fromItem)) {
                continue;
            }

            T transformed = transformer.transform(fromItem);
            if (toTypeInclusionFilter == null || toTypeInclusionFilter.test(transformed)) {
                toList.add(transformed);
            }
        }
        return toList;
    }

    public static <T> List<T> filter(@Nullable List<T> toFilter, Predicate<T> predicate) {
        if (toFilter == null || toFilter.isEmpty()) {
            return Collections.emptyList();
        }

        int size = toFilter.size();
        List<T> filtered = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            T item = toFilter.get(i);
            if (predicate.test(item)) {
                filtered.add(item);
            }
        }

        return filtered;
    }

    @Nullable
    public static <T> T findFirstMatch(List<T> items, Predicate<T> predicate) {
        int size = items.size();
        for (int j = 0; j < size; j++) {
            T item = items.get(j);
            if (predicate.test(item)) {
                return item;
            }
        }
        return null;
    }

    public static <T> boolean allMatch(List<T> items, Predicate<T> predicate) {
        int size = items.size();
        for (int j = 0; j < size; j++) {
            T item = items.get(j);
            if (!predicate.test(item)) {
                return false;
            }
        }
        return true;
    }

}
