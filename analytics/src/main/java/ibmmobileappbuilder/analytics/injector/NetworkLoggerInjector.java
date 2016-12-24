package ibmmobileappbuilder.analytics.injector;

import ibmmobileappbuilder.analytics.network.LogNetworkLogger;
import ibmmobileappbuilder.analytics.network.NetworkLogger;

public class NetworkLoggerInjector {

    private static NetworkLogger instance;

    public static NetworkLogger networkLogger() {
        if (instance != null) {
            return instance;
        }
        instance = new LogNetworkLogger();
        return instance;
    }
}
