package zeta.android.apps.network;

import java.io.IOException;

public class ZetaNoNetworkConnectivityException extends IOException {

    public ZetaNoNetworkConnectivityException() {
        super();
    }

    @Override
    public String getMessage() {
        return "No network connectivity found";
    }

}
