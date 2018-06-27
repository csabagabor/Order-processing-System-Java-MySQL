package bll;

import java.util.ArrayList;
import java.util.List;

import bll.validators.Validator;
import dao.CategoryDAO;
import model.Category;


/**
 * The Class CategoryBLL.
 */
public class CategoryBLL extends AbstractBLL<CategoryBLL, Category, CategoryDAO> {

	
	@Override
	public List<Validator<Category>> getValidators() {
		List<Validator<Category>> validators;
		validators = new ArrayList<Validator<Category>>();
		return validators;
	}

}
