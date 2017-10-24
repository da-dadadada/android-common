package osp.leobert.android.router.facade.model;

/**
 * contains the Uri info of the UiActivity,
 * works as the address of the activity，
 * helps to create a UiActivityUri
 * <p>
 * Created by leobert on 2017/9/28.
 */

public final class Address {
    private final String host;
    private final String path;

    public Address(String host, String path) {
        this.host = host;
        this.path = path;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }
}
