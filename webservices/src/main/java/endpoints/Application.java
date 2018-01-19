package endpoints;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//import org.apache.log4j.spi.LoggerFactory;
//import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This class acts as the main WS "bus" & bean configuration file.  
 * 
 * @author Tony Boarman
 * @date Jan 5, 2018
 */
@Configuration
@ComponentScan({"endpoints","contract","business","util"})
@SpringBootApplication()
public class Application implements ServletContextInitializer {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	/* This bit is only needed to fix issue with port sharing between app and H2 dev console.
	 * On the application startup, the H2 console servlet is loaded and mapped to /h2console/ URL.
	 * When I start the application, I can access the console under this URL: http://localhost:8080/myApp/h2console/. */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	//TODO this may no longer be needed since we switched to TCP connection URL
        initH2Console(servletContext);
    }

    private void initH2Console(ServletContext servletContext) {
    	//TODO this may no longer be needed since we switched to TCP connection URL
        ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console", new org.h2.server.web.WebServlet());
        h2ConsoleServlet.addMapping("/h2console/*");
        h2ConsoleServlet.setLoadOnStartup(1);
    }
    
	@Autowired
	@Qualifier("contractService")
	private ContractService contractService;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            container.setPort(8666);
        });
    }
}
