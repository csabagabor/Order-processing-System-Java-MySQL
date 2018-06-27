package bll;

import java.util.ArrayList;
import java.util.List;

import bll.validators.DateValidator;
import bll.validators.Validator;
import dao.OrderDAO;
import model.Orders;


/**
 * The Class OrderBLL.
 */
public class OrderBLL extends AbstractBLL<OrderBLL, Orders, OrderDAO> {

	
	@Override
	public List<Validator<Orders>> getValidators() {
		List<Validator<Orders>> validators;
		validators = new ArrayList<Validator<Orders>>();
		validators.add(new DateValidator<Orders>());
		return validators;
	}

}
