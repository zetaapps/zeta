package zeta.android.apps.network.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import zeta.android.apps.network.exception.ZetaNoNetworkConnectivityException;
import zeta.android.apps.providers.interfaces.ConnectivityProvider;

public class ConnectivityCheckInterceptor implements Interceptor {
    private ConnectivityProvider connectivityProvider;

    public ConnectivityCheckInterceptor(ConnectivityProvider connectivityProvider) {
        this.connectivityProvider = connectivityProvider;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!connectivityProvider.isConnected()) {
            throw new ZetaNoNetworkConnectivityException();
        }
        Request request = chain.request();
        return chain.proceed(request);
    }
}
