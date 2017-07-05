package bootdemo.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

import bootdemo.properties.order.LinkedProperties;
import bootdemo.properties.order.OrderedProperties;

public class TestOrderedProperties {

    @Test
    public void testOrderedProperties() throws FileNotFoundException, IOException {
    	//Properties props = new LinkedProperties();
    	Properties props = new OrderedProperties();
		props.load(new FileInputStream("C:/Users/zhang/git/SpringBootDemo/bootdemo/src/main/resources/ErrorResources.properties"));
		for (Enumeration e = props.propertyNames(); e.hasMoreElements();) {
			while(e.hasMoreElements()){
			   /* Vector v = (Vector)e.nextElement();
			    System.out.println(v);*/
				System.out.println(e.nextElement());
			}

		}
    }


    @Test
    public void testLinkedProperties() throws FileNotFoundException, IOException {
    	Properties props = new LinkedProperties();
    	//Properties props = new OrderedProperties();
		props.load(new FileInputStream("C:/Users/zhang/git/SpringBootDemo/bootdemo/src/main/resources/ErrorResources.properties"));
		Set<Map.Entry<Object,Object>> kk = props.entrySet();

		for(Map.Entry<Object,Object> k : kk){
			System.out.println(k.getKey());
		}

		System.out.println("----------------------------------------------------------------");

		for(Object key : props.keySet()){
			System.out.println(key);
		}


    }
}