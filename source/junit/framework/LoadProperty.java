package junit.framework;

import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;


public class LoadProperty {

	private static LoadProperty instance;
	Properties props ;
	
	private LoadProperty()throws Throwable{
		props = new Properties();
	   InputStream is = ClassLoader.getSystemResourceAsStream("unittest.properties");
	   props.load(is);
					
		
	}
	
	private void displayClassPath(){
		 ClassLoader cl = ClassLoader.getSystemClassLoader();
   	  
         URL[] urls = ((URLClassLoader)cl).getURLs();
  
         for(URL url: urls){
         	System.out.println(url.getFile());
         }
	}
	
	  public static LoadProperty getInstance() throws Throwable{
	    if (instance == null) {
	      synchronized (LoadProperty.class) {
	        if (instance == null) {
	        	instance = new LoadProperty();
	        }
	      }
	    }
	    return instance;
	  }
	  
	  public String getProperty(String key){
		  return props.getProperty(key,"");
	  }
	
}
