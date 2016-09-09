package zeta.android.apps.providers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import zeta.android.apps.providers.interfaces.ConnectivityProvider;

public class DefaultConnectivityProvider implements ConnectivityProvider {

    private final ConnectivityManager mConnectivityManager;

    public DefaultConnectivityProvider(Context context) {
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public boolean isConnected() {
        NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public boolean isWifiNetwork() {
        NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
    }

}
