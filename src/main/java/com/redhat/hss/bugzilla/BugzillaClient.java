package com.redhat.hss.bugzilla;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.engineering.xmlrpc.XmlRpcClient;
import com.redhat.hss.uitl.PropertiesUtils;

/**
 * Copyright 2010 JBoss Inc
 *
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
 */

/**
 * @author xiabai
 * 
 */
public abstract class BugzillaClient<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BugzillaClient.class);
    private XmlRpcClient client;

    private final Semaphore loginSemaphore = new Semaphore(1);

    private final ThreadLocal<String> token = new ThreadLocal<String>();

    /**
     * Connects to the given host.
     * 
     * @param host A {@code String} which can be parsed as a URL, pointing to the Bugzilla base.
     */
    public abstract void connectTo(String host);

    /**
     * Connects to the given host.
     * 
     * @param host A {@link URI} which points to the Bugzilla base.
     */
    public abstract void connectTo(URI host);

    /**
     * Connects to the given host, providing HTTP Basic authentication credentials. Note that this does not log you in with
     * Bugzilla itself.
     * 
     * @param host A {@code String} which can be parsed as a URL, pointing to the Bugzilla base.
     * @param httpUser A {@code String} representing an HTTP Basic username.
     * @param httpPass A {@code String} representing an HTTP Basic password.
     */
    public abstract void connectTo(String host, String httpUser, String httpPass);

    /**
     * Connects to the given host, providing HTTP Basic authentication credentials. Note that this does not log you in with
     * Bugzilla itself.
     * 
     * @param host A {@link URI} which points to the Bugzilla base.
     * @param httpUser A {@code String} representing an HTTP Basic username.
     * @param httpPass A {@code String} representing an HTTP Basic password.
     */
    public abstract void connectTo(URI host, String httpUser, String httpPass);

    public abstract T queryObject(T t);

    public abstract List<T> queryList(T t);

    public String login(String userName, String password) {
        LOGGER.debug("Login into Bugzilla");
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("login", userName);
        params.put("password", password);
        String method = PropertiesUtils.getValue("BZ_USER_LOGIN").toString();
        Map result;
        try {
            result = client.method(method).parameter(params).execute();
        } catch (XmlRpcException e) {
            throw new BugzillaException(e.getMessage(), e);
        }
        LOGGER.debug("Login result: {}", result);
        if (result == null || result.isEmpty()) {
            throw new BugzillaException("Failed to login into Bugzilla");
        }
        String tokenString = result.get("token").toString();
        token.set(tokenString);
        return tokenString;
    }

    public T excute(T t) {
        return null;
    }

    public List<T> listExcute(T t) {
        return null;
    }
}
