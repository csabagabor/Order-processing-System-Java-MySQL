package bll;

import java.util.ArrayList;
import java.util.List;

import bll.validators.EmailValidator;
import bll.validators.PhoneValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;


/**
 * The Class ClientBLL.
 */
public class ClientBLL extends AbstractBLL<ClientBLL, Client, ClientDAO> {

	
	@Override
	public List<Validator<Client>> getValidators() {
		List<Validator<Client>> validators;
		validators = new ArrayList<Validator<Client>>();
		validators.add(new EmailValidator<Client>());
		validators.add(new PhoneValidator<Client>());
		return validators;
	}

}
