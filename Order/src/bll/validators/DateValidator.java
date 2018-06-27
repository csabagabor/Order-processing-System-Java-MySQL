package bll.validators;

import java.util.logging.Level;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import model.Orders;
import presentation.View;


/**
 * The Class DateValidator.
 *
 * @param <T>
 *             generic type
 */
public class DateValidator<T> implements Validator<T> {

	/** The DATE_PATTERN regex is used to identify correct/incorrect dates. */
	private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

	
	public boolean validate(T t) {
		try {
			Pattern pattern = Pattern.compile(DATE_PATTERN);
			if (t.getClass().getSimpleName().equals(Orders.class.getSimpleName())) {
				if (!pattern.matcher(((Orders) t).getDate().toString()).matches()) {
					throw new IllegalArgumentException(" Invalid date!");
				}
			} else
				throw new IllegalArgumentException("Class " + t.getClass().getName() + " does not have a date field");
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.WARNING, this.getClass().getName() + e.getMessage());
			View.showMessage("Invalid date", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Validates a String not an object.
	 *
	 * @param toValidate
	 *            the String to validate
	 * @return true, if successful
	 */
	public static boolean validateString(String toValidate) {
		Pattern pattern = Pattern.compile(DATE_PATTERN);
		try {
			if (!pattern.matcher(toValidate).matches()) {
				throw new IllegalArgumentException(" Invalid date!");
			}
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.WARNING, "DateValidator " + e.getMessage());
			View.showMessage("Invalid date", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

}
