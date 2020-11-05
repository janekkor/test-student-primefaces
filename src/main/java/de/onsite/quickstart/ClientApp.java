package de.onsite.quickstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ClientApp 
{

	public static void main( String[] args )
    {
    	SpringApplication.run(ClientApp.class, args);
    	System.out.println("ClientApp wurde aufgerufen!");
    }
}
