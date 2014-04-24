package com.redhat.engineering.xmlrpc;

import com.redhat.hss.bugzilla.exception.BZXmlRpcException;

public interface XmlRpcClient {

    public XmlRpcClient url(String url);

    public XmlRpcClient user(String user);

    public XmlRpcClient password(String password);

    public XmlRpcClient method(String method);

    public XmlRpcClient parameter(Object value);

    public <T> T execute() throws BZXmlRpcException;

}
