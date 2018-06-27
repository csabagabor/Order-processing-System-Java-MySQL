package bll.validators;

import java.util.logging.Level;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import model.Client;
import model.Shipper;
import model.Supplier;
import presentation.View;


/**
 * The Class PhoneValidator.
 *
 * @param <T>
 *            the generic type
 */
public class PhoneValidator<T> implements Validator<T> {

	/** The PHONE_PATTERN regex is used to identify correct/incorrect phone numbers. */
	private static final String PHONE_PATTERN = "\\d{10}";

	
	public boolean validate(T t) {
		try {
			Pattern pattern = Pattern.compile(PHONE_PATTERN, Pattern.CASE_INSENSITIVE);
			if (t.getClass().getSimpleName().equals(Client.class.getSimpleName())) {
				if (!pattern.matcher(((Client) t).getPhone()).matches()) {
					throw new IllegalArgumentException(" Invalid phone number!");
				}
			} else if (t.getClass().getSimpleName().equals(Supplier.class.getSimpleName())) {
				if (!pattern.matcher(((Supplier) t).getPhone()).matches()) {
					throw new IllegalArgumentException(" Invalid phone number!");
				}
			} else if (t.getClass().getSimpleName().equals(Shipper.class.getSimpleName())) {
				if (!pattern.matcher(((Shipper) t).getPhone()).matches()) {
					throw new IllegalArgumentException(" Invalid phone number!");
				}
			} else
				throw new IllegalArgumentException("Class " + t.getClass().getName() + " does not have a phone field");
		} catch (

		IllegalArgumentException e) {
			LOGGER.log(Level.WARNING, this.getClass().getName() + e.getMessage());
			View.showMessage("Invalid Phone Number", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
}
