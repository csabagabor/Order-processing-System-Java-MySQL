package bll.validators;

import java.util.logging.Logger;

/**
 * The Interface Validator.
 *
 * @param <T>
 *             generic type
 */
public interface Validator<T> {

	/** The LOGGER of this class. */
	static final Logger LOGGER = Logger.getLogger(Validator.class.getName());

	/**
	 * Validate an object.
	 *
	 * @param t
	 *            the object that needs to be validated
	 * @return true, if successful
	 */
	public boolean validate(T t);
}
