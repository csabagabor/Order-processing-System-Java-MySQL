package bll;

import java.util.ArrayList;
import java.util.List;
import bll.validators.PhoneValidator;
import bll.validators.Validator;
import dao.ShipperDAO;
import model.Shipper;


/**
 * The Class ShipperBLL.
 */
public class ShipperBLL extends AbstractBLL<ShipperBLL, Shipper, ShipperDAO> {

	
	@Override
	public List<Validator<Shipper>> getValidators() {
		List<Validator<Shipper>> validators;
		validators = new ArrayList<Validator<Shipper>>();
		validators.add(new PhoneValidator<Shipper>());
		return validators;
	}

}
