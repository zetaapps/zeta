package zeta.apps.flickr.models.common;

public interface ITransformer<T, R> {
    R transform(T t);
}
