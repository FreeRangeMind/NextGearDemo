package business.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import business.Business;
import business.controller.impl.BusinessControllerImpl;


public class BusinessControllerTest {
//
//    private EmbeddedDatabase db;
    //@Autowired
	BusinessController businessController;


    @Before
    public void setUp() {

//    	db = new EmbeddedDatabaseBuilder()
//    		.setType(EmbeddedDatabaseType.H2)
//    		.addScript("db/sql/create-db.sql")
//    		.addScript("db/sql/insert-data.sql")
//    		.build();
    }
    
    @Test
    public void testFindById1() {
//    	
//    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
//    	BusinessControllerImpl businessController = new BusinessControllerImpl();
//    	businessController.set
//    	Assert.assertNotNull(businessController);
//    	
//    	Business business = businessController.findById(1L);
//
//    	Assert.assertNotNull(business);
//    	Assert.assertEquals(1L, business.getBusinessNumber());
//    	Assert.assertEquals("Association of burger flippers", business.getName());
    }

    @After
    public void tearDown() {
//        db.shutdown();
    }
}
