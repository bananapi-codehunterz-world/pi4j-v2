package com.pi4j.platform;

/*-
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  Pi4J
 * PROJECT       :  Pi4J :: LIBRARY  :: Java Library (API)
 * FILENAME      :  PlatformBase.java
 *
 * This file is part of the Pi4J project. More information about
 * this project can be found here:  https://pi4j.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2012 - 2019 Pi4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.pi4j.config.Config;
import com.pi4j.config.ConfigBuilder;
import com.pi4j.context.Context;
import com.pi4j.exception.InitializeException;
import com.pi4j.exception.Pi4JException;
import com.pi4j.exception.ShutdownException;
import com.pi4j.extension.ExtensionBase;
import com.pi4j.io.IO;
import com.pi4j.io.IOType;
import com.pi4j.provider.Provider;
import com.pi4j.provider.exception.ProviderException;
import com.pi4j.provider.exception.ProviderInterfaceException;
import com.pi4j.provider.exception.ProviderNotFoundException;
import com.pi4j.provider.impl.ProviderProxyHandler;
import com.pi4j.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Abstract PlatformBase class.</p>
 *
 * @author Robert Savage (<a href="http://www.savagehomeautomation.com">http://www.savagehomeautomation.com</a>)
 * @version $Id: $Id
 */
public abstract class PlatformBase<PLATFORM extends Platform>
        extends ExtensionBase<Platform>
        implements Platform {

    protected Context context = null;
    protected Map<IOType, Provider> providers = new ConcurrentHashMap<>();
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>Constructor for PlatformBase.</p>
     */
    public PlatformBase(){
        super();
    }

    /**
     * <p>Constructor for PlatformBase.</p>
     *
     * @param id a {@link java.lang.String} object.
     */
    public PlatformBase(String id){
        super(id);
    }

    /**
     * <p>Constructor for PlatformBase.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @param name a {@link java.lang.String} object.
     */
    public PlatformBase(String id, String name){
        super(id, name);
    }

    /**
     * <p>Constructor for PlatformBase.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @param name a {@link java.lang.String} object.
     * @param description a {@link java.lang.String} object.
     */
    public PlatformBase(String id, String name, String description){
        super(id, name, description);
    }

    /** {@inheritDoc} */
    @Override
    public Map<IOType, Provider> providers() {
        return Collections.unmodifiableMap(this.providers);
    }

    /** {@inheritDoc} */
    @Override
    public <T extends Provider> T provider(Class<T> providerClass) throws ProviderNotFoundException, ProviderInterfaceException {

        if(!providerClass.isInterface()){
            logger.warn("Provider type [" + providerClass.getName() + "] requested; this is not an 'Interface'" +
                    " and may not return a valid provider or may not be able to cast to the concrete class.");
        }

        for(Provider p : providers.values()){
            if(providerClass.isAssignableFrom(p.getClass())){
                return (T)p;
            }

            // check for Proxied provider instances, if a Proxy, then also check the underlying handlers source class
            if (Proxy.isProxyClass(p.getClass())) {
                if(Proxy.getInvocationHandler(p).getClass().isAssignableFrom(ProviderProxyHandler.class)){
                    ProviderProxyHandler pp = (ProviderProxyHandler) Proxy.getInvocationHandler(p);
                    if(providerClass.isAssignableFrom(pp.provider().getClass())){
                        return (T) p;
                    }
                }
            }
        }

        if(providerClass.isInterface()){
            throw new ProviderNotFoundException(providerClass);
        } else {
            throw new ProviderInterfaceException(providerClass);
        }
    }

    /** {@inheritDoc} */
    @Override
    public <T extends IO>T create(String id) throws Exception {
        if(this.context == null) throw new Pi4JException("Unable to create IO instance; this platform has not been 'iniitalized()' with a Pi4J context.");
        Map<String,String> keys = PropertiesUtil.subKeys(this.context.properties().all(), id);
        Provider provider = null;

        // create by explicit IO provider
        if(keys.containsKey("provider")){
            String providerId = keys.get("provider");
            provider = providers().get(providerId);
            if(provider == null) {
                throw new ProviderNotFoundException(providerId);
            }
        }

        // create by IO TYPE
        // (use platform provider if one if available for this IO type)
        else if(keys.containsKey("type")){
            String type = keys.get("type");
            IOType ioType = IOType.valueOf(type);
            provider = provider(ioType);
            if(provider == null) {
                throw new ProviderNotFoundException(type);
            }
        }

        // create IO instance
        ConfigBuilder builder = provider.type().newConfigBuilder();
        builder.load(keys);
        return (T)provider.create((Config) builder.build());
    }

    /** {@inheritDoc} */
    @Override
    public <T extends IO>T create(String id, IOType ioType) throws Exception {
        if(this.context == null) throw new Pi4JException("Unable to create IO instance; this platform has not been 'iniitalized()' with a Pi4J context.");
        Map<String,String> keys = PropertiesUtil.subKeys(this.context.properties().all(), id);
        Provider provider = null;

        // create by explicit IO provider
        if(keys.containsKey("provider")){
            String providerId = keys.get("provider");
            provider = providers().get(providerId);
            if(provider == null) {
                throw new ProviderNotFoundException(providerId);
            }
        }

        // create by IO TYPE
        // (use platform provider if one if available for this IO type)
        provider = provider(ioType);
        if(provider == null) {
            throw new ProviderNotFoundException(ioType);
        }

        // create IO instance
        ConfigBuilder builder = provider.type().newConfigBuilder();
        builder.load(keys);
        return (T)provider.create((Config) builder.build());
    }

    /** {@inheritDoc} */
    @Override
    public abstract int weight();

    /** {@inheritDoc} */
    @Override
    public abstract boolean enabled(Context context);

    /** {@inheritDoc} */
    @Override
    public PLATFORM initialize(Context context) throws InitializeException {
        this.context = context;
        String[] provIds = getProviders();
        for (String provId : provIds) {
            try {
                addProvider(context, provId);
            } catch (ProviderException e) {
                throw new InitializeException(e.getMessage());
            }
        }
        return (PLATFORM) this;
    }

    /** {@inheritDoc} */
    @Override
    public PLATFORM shutdown(Context context) throws ShutdownException {
        return (PLATFORM)this;
    }

    /**
     * <p>Getter for the field <code>providers</code>.</p>
     *
     * @return an array of {@link java.lang.String} objects.
     */
    protected abstract String[] getProviders();

    /**
     * <p>addProvider.</p>
     *
     * @param context a {@link com.pi4j.context.Context} object.
     * @param providerId a {@link java.lang.String} object.
     * @throws com.pi4j.provider.exception.ProviderException if any.
     */
    protected void addProvider(Context context, String providerId) throws ProviderException {
        var provider = context.providers().get(providerId);
        this.providers.put(IOType.getByProviderClass(provider.getClass()), provider);
    }
}
