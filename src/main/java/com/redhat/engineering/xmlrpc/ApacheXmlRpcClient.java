package com.redhat.engineering.xmlrpc;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcSunHttpTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.hss.bugzilla.exception.BZXmlRpcException;

public class ApacheXmlRpcClient implements XmlRpcClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApacheXmlRpcClient.class);

    private org.apache.xmlrpc.client.XmlRpcClient client = null;

    private final ThreadLocal<String> method = new ThreadLocal<String>();

    private final ThreadLocal<Object[]> parameters = new ThreadLocal<Object[]>();

    public ApacheXmlRpcClient() {
        client = new org.apache.xmlrpc.client.XmlRpcClient();
        final XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setEnabledForExceptions(true);
        config.setConnectionTimeout(1000 * 60 * 1);
        config.setReplyTimeout(1000 * 60 * 1);

        // Bugzilla authentication requires cookies.
        // FIXME: this affects the cookie handler for the whole application;
        // we should try to make it something less drastic...
        CookieHandler ch = CookieHandler.getDefault();
        if (ch == null) {
            CookieHandler.setDefault(new CookieManager());
        }

        client.setTransportFactory(new XmlRpcSunHttpTransportFactory(client));
        client.setConfig(config);
        client.setTypeFactory(new TypeFactory(client));
    }

    @Override
    public XmlRpcClient url(String url) {
        try {
            ((XmlRpcClientConfigImpl) client.getConfig()).setServerURL(new URL(url));
            return this;
        } catch (MalformedURLException ex) {
            LOGGER.error("Error setting XML-RPC URL", ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public XmlRpcClient password(String password) {
        ((XmlRpcClientConfigImpl) client.getConfig()).setBasicPassword(password);
        return this;
    }

    @Override
    public XmlRpcClient user(String user) {
        ((XmlRpcClientConfigImpl) client.getConfig()).setBasicUserName(user);
        return this;
    }

    @Override
    public XmlRpcClient method(String method) {
        this.method.set(method);
        this.parameters.set(null);
        return this;
    }

    @Override
    public XmlRpcClient parameter(Object value) {
        Object[] param = this.parameters.get();
        if (param == null) {
            param = new Object[] {};
        }
        param = ArrayUtils.add(param, value);
        this.parameters.set(param);
        return this;
    }

    /**
     * Executes the XML-RPC call
     * 
     * @return An object with response
     * @throws XmlRpcException
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T execute() throws BZXmlRpcException {
        String method = this.method.get();
        if (StringUtils.isBlank(method)) {
            throw new BZXmlRpcException("XMLRPC Methos cannot be null");
        }
        try {
            return (T) client.execute(method, this.parameters.get());
        } catch (XmlRpcException e) {
            throw new BZXmlRpcException(e.getMessage(), e);
        }
    }

}
