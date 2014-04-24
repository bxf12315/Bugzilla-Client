package com.redhat.engineering.xmlrpc;

import org.apache.xmlrpc.XmlRpcException;

public interface XmlRpcClient {

    public XmlRpcClient url(String url);

    public XmlRpcClient user(String user);

    public XmlRpcClient password(String password);

    public XmlRpcClient method(String method);

    public XmlRpcClient parameter(Object value);

    public <T> T execute() throws XmlRpcException;

}
