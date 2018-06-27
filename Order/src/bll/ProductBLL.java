package bll;

import java.util.ArrayList;
import java.util.List;

import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;


/**
 * The Class ProductBLL.
 */
public class ProductBLL extends AbstractBLL<ProductBLL, Product, ProductDAO> {

	
	@Override
	public List<Validator<Product>> getValidators() {
		List<Validator<Product>> validators;
		validators = new ArrayList<Validator<Product>>();
		return validators;
	}

}
