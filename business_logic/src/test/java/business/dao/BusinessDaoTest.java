package business.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import business.Business;

//@ContextConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@Configuration
////@TestPropertySource("classpath:persistence-application.yml")
//@ComponentScan({"contract"})
//@ComponentScan({"business"})
//@ComponentScan({"util"})
////@RunWith(SpringRunner.class)


//@Configuration
//@EnableAutoConfiguration
//@EntityScan(basePackages = {"guru.springframework.domain"})
//@EnableJpaRepositories(basePackages = {"guru.springframework.repositories"})
//@EnableTransactionManagement
public class BusinessDaoTest {
//
//	   @Bean
//	   public static PropertySourcesPlaceholderConfigurer
//	     propertySourcesPlaceholderConfigurer() {
//		   
//		   //Resource r = new Resource();
//		   PropertySourcesPlaceholderConfigurer config =  new PropertySourcesPlaceholderConfigurer();
//		   
//	      return config;
//	   }
	//private EmbeddedDatabase db;

	@Autowired
	//@Qualifier("businessDAO")
	private BusinessDAO businessDAO;
	
    @Before
    public void setUp() {
        //db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
//    	db = new EmbeddedDatabaseBuilder()
//    		.setType(EmbeddedDatabaseType.H2)
//    		.addScript("db/sql/create-db.sql")
//    		.addScript("db/sql/insert-data.sql")
//    		.build();
    }
    
    @Test
    public void testFindByname() {
    	//NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
    	//BusinessDAOImpl bizDao = new BusinessDAOImpl();

    	Assert.assertNotNull(businessDAO);
    	
    	Business business = businessDAO.findOne(1L);

    	Assert.assertNotNull(business);
    	Assert.assertEquals(1L, business.getBusinessNumber());
    	Assert.assertEquals("Association of burger flippers", business.getName());
    }
    
    @After
    public void tearDown() {
        //db.shutdown();
    }
}
