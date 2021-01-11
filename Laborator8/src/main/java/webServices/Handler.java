package webServices;

import authorization.User;
import fileHandling.UserFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Collections;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPMessage;
public class Handler implements SOAPHandler<SOAPMessageContext>
{

    private static List<Integer> queryIds;
    private static List<List<UserFile>> results;
    public static void reset()
    {
        queryIds=new ArrayList<Integer>();
        results=new ArrayList<List<UserFile>>();
    }
    public Set<QName> getHeaders()
    {
        return Collections.emptySet();
    }

    public boolean handleMessage(SOAPMessageContext messageContext)
    {
        Boolean outboundProperty = (Boolean)
                messageContext.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (outboundProperty.booleanValue()) {
            try {
                List<UserFile> result=(List<UserFile>)messageContext.getMessage().getProperty("getALL");
                if(results==null)
                {
                    results=new ArrayList<List<UserFile>>();
                }
                results.add(result);
            } catch (SOAPException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Integer id=(Integer) messageContext.getMessage().getProperty("getALL");
                if(queryIds==null)
                {
                    queryIds=new ArrayList<Integer>();
                }
                queryIds.add(id);
            } catch (SOAPException e) {
                e.printStackTrace();
            }
        }

        System.out.println("** Response: "+messageContext.getMessage().toString());
        return true;
    }

    public boolean handleFault(SOAPMessageContext messageContext)
    {
        return true;
    }
    public void close(MessageContext messageContext)
    {
    }
}