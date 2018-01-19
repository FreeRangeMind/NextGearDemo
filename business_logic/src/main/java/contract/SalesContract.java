package contract;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "SalesContract")
@DiscriminatorValue("SALES")
public class SalesContract extends Contract {
	
}
