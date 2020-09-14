package com.ruoyi.framework.ehcache;

import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.jcache.ConfigSettings;
import org.hibernate.cache.jcache.internal.JCacheRegionFactory;

import javax.cache.CacheManager;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.net.URL;
import java.util.Map;

/**
 * 解决hibernate.properties中不能配置ehcache.xml所在路径为相对路径的问题
 */
public class MyJCacheRegionFactory extends JCacheRegionFactory {

    @Override
    protected CacheManager resolveCacheManager(SessionFactoryOptions settings, Map properties) {
        final Object explicitCacheManager = properties.get( ConfigSettings.CACHE_MANAGER );
        if ( explicitCacheManager != null ) {
            return useExplicitCacheManager( settings, explicitCacheManager );
        }

        final CachingProvider cachingProvider = getCachingProvider( properties );
        final CacheManager cacheManager;
        final URI cacheManagerUri = getUri( properties );
        // ***** begin patch ******
        URI uri = cacheManagerUri;
        URL url = null;
        try {
            uri.toURL();
        } catch (Exception e) {
            try {
                url = getClassLoader(cachingProvider).getResource(cacheManagerUri.toString());
                uri = url.toURI();
            } catch (Exception e1) {
                throw new IllegalArgumentException("Resource not found: " + uri, e1);
            }
        }
        // ****** end patch ******
        if ( cacheManagerUri != null ) {
            cacheManager = cachingProvider.getCacheManager( uri, getClassLoader( cachingProvider ));
        }
        else {
            cacheManager = cachingProvider.getCacheManager();
        }
        return cacheManager;
    }

    private CacheManager useExplicitCacheManager(SessionFactoryOptions settings, Object setting) {
        if ( setting instanceof CacheManager ) {
            return (CacheManager) setting;
        }

        final Class<? extends CacheManager> cacheManagerClass;
        if ( setting instanceof Class ) {
            cacheManagerClass = (Class<? extends CacheManager>) setting;
        }
        else {
            cacheManagerClass = settings.getServiceRegistry().getService( ClassLoaderService.class )
                    .classForName( setting.toString() );
        }

        try {
            return cacheManagerClass.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            throw new CacheException( "Could not use explicit CacheManager : " + setting );
        }
    }
}
