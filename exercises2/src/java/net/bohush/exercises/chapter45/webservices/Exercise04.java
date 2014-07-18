package net.bohush.exercises.chapter45.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;
import com.sun.xml.ws.developer.servlet.HttpSessionScope;

@HttpSessionScope
@WebService(name = "Exercise04", serviceName = "Exercise45_04")
public class Exercise04 {
    private int count = 0;
    
    @WebMethod(operationName = "getCount")
    public int getCount() {
        return count++;
    }
   
}