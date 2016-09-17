package zeta.android.apps.providers.interfaces;

/**
 * Interface to deliver information regarding network connectivity, while not revealing the source of that information.
 */
public interface ConnectivityProvider {
    boolean isConnected();

    boolean isWifiNetwork();
}
