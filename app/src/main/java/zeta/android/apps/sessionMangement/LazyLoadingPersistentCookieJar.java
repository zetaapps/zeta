package zeta.android.apps.sessionMangement;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.cache.CookieCache;
import com.franmontiel.persistentcookiejar.persistence.CookiePersistor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

@ParametersAreNonnullByDefault
public class LazyLoadingPersistentCookieJar implements ClearableCookieJar {

    private CookieCache cache;
    private boolean isCacheLoaded;
    private CookiePersistor persistor;

    public LazyLoadingPersistentCookieJar(CookieCache cache, CookiePersistor persistor) {
        this.cache = cache;
        this.persistor = persistor;
    }

    private static boolean isCookieExpired(Cookie cookie) {
        return cookie.expiresAt() < System.currentTimeMillis();
    }

    @Override
    synchronized public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        // NOTE: this MUST be called first!
        loadCacheIfNeeded();

        cookies = preProcessCookies(cookies);

        cache.addAll(cookies);
        persistor.saveAll(cookies);
    }

    @Override
    synchronized public List<Cookie> loadForRequest(HttpUrl url) {
        // NOTE: this MUST be called first!
        loadCacheIfNeeded();

        List<Cookie> removedCookies = new ArrayList<>();
        List<Cookie> validCookies = new ArrayList<>();

        for (Iterator<Cookie> it = cache.iterator(); it.hasNext(); ) {
            Cookie currentCookie = it.next();

            if (isCookieExpired(currentCookie)) {
                removedCookies.add(currentCookie);
                it.remove();

            } else if (currentCookie.matches(url)) {
                validCookies.add(currentCookie);
            }
        }

        persistor.removeAll(removedCookies);

        return validCookies;
    }

    public synchronized void clear() {
        cache.clear();
        persistor.clear();
    }

    private void loadCacheIfNeeded() {
        if (isCacheLoaded) {
            return;
        }
        isCacheLoaded = true;
        this.cache.addAll(persistor.loadAll());
    }

    private List<Cookie> preProcessCookies(List<Cookie> cookies) {
        // make a modifiable list of cookies
        List<Cookie> validCookies = new ArrayList<>(cookies);
        List<Cookie> expiredCookies = new ArrayList<>(cookies.size());
        for (int i = 0; i < cookies.size(); i++) {
            Cookie cookie = cookies.get(i);
            if (cookie.persistent() && cookie.expiresAt() < System.currentTimeMillis()) {
                //remove the expired cookie from the list valid cookies
                validCookies.remove(cookie);
                // add the expired cookie to a list, later to be removed from the persistor
                expiredCookies.add(cookie);
            }
        }

        // remove the list of expired cookies
        this.persistor.removeAll(expiredCookies);

        // proceed with the super class implementation, add all the valid cookies to the cache
        // and save them to the persistor
        return validCookies;
    }
}
