package com.redhat.engineering.xmlrpc;

import com.redhat.hss.bugzilla.exception.BZXmlRpcException;

public class XmlRpcClientBuilder {

    private static Class<? extends XmlRpcClient> CLIENT = ApacheXmlRpcClient.class;

    public static synchronized <E extends XmlRpcClient> void setXmlRpcClientClass(Class<E> clazz) {
        CLIENT = clazz;
    }

    public static XmlRpcClient xmlrpcclient() {
        try {
            return CLIENT.newInstance();
        } catch (Exception e) {
            throw new BZXmlRpcException(e.getMessage(), e);
        }
    }

}
