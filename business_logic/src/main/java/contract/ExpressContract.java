package contract;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "ExpressContract")
@DiscriminatorValue("EXPRESS")
public class ExpressContract extends Contract {

}
