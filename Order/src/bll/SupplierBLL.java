package bll;

import java.util.ArrayList;
import java.util.List;

import bll.validators.PhoneValidator;
import bll.validators.Validator;
import dao.SupplierDAO;
import model.Supplier;


/**
 * The Class SupplierBLL.
 */
public class SupplierBLL extends AbstractBLL<SupplierBLL, Supplier, SupplierDAO> {

	
	@Override
	public List<Validator<Supplier>> getValidators() {
		List<Validator<Supplier>> validators;
		validators = new ArrayList<Validator<Supplier>>();
		validators.add(new PhoneValidator<Supplier>());
		return validators;
	}

}
