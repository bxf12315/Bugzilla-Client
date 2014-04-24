package com.redhat.engineering.xmlrpc;

import org.apache.ws.commons.util.NamespaceContextImpl;
import org.apache.xmlrpc.common.TypeFactoryImpl;
import org.apache.xmlrpc.common.XmlRpcController;
import org.apache.xmlrpc.common.XmlRpcStreamConfig;
import org.apache.xmlrpc.parser.NullParser;
import org.apache.xmlrpc.parser.TypeParser;
import org.apache.xmlrpc.serializer.TypeSerializer;
import org.apache.xmlrpc.serializer.TypeSerializerImpl;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class TypeFactory extends TypeFactoryImpl {

	public TypeFactory(XmlRpcController pController) {
		super(pController);
	}
	
	public TypeParser getParser(XmlRpcStreamConfig pConfig, NamespaceContextImpl pContext, String pURI, String pLocalName) {
		if (NullSerializer.NIL_TAG.equals(pLocalName)) {
			return new NullParser();
        } else {
            return super.getParser(pConfig, pContext, pURI, pLocalName);
        }
    }

    public TypeSerializer getSerializer(XmlRpcStreamConfig pConfig, Object pObject) throws SAXException {
    	if (pObject == null) {
			return new NullSerializer();
        } else {
            return super.getSerializer(pConfig, pObject);
        }
    }
    
    private class NullSerializer extends TypeSerializerImpl {
    	
    	/** Tag name of a nil value. */
    	public static final String NIL_TAG = "nil";
    	
    	public void write(ContentHandler pHandler, Object pObject) throws SAXException {
    		pHandler.startElement("", VALUE_TAG, VALUE_TAG, ZERO_ATTRIBUTES);
    		pHandler.endElement("", VALUE_TAG, VALUE_TAG);
    	}
    	
    }

}
