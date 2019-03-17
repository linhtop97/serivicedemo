package vn.com.misa.servicedemo.Utils;

/**
 * Created by Nguyen Ba Linh on 17/03/2019.
 */
public interface DataCallback<T> {
    void onDataSuccess(T data);

    void onDataNotAvailable();
}
