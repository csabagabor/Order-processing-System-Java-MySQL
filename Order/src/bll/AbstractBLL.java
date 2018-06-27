package bll;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import bll.validators.Validator;
import dao.AbstractDAO;

/**
 * The Class AbstractBLL.
 *
 * @param <H>
 *            the generic type for the bll class
 * @param <T>
 *            the generic type for the model class
 * @param <D>
 *            the generic type for the dao class
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractBLL<H, T, D extends AbstractDAO> {

	/** The LOGGER of this class. */
	protected static final Logger LOGGER = Logger.getLogger(AbstractBLL.class.getName());

	/** The type of the bll class. */
	private final Class<T> type;

	/** The type of the DAO class. */
	private final Class<D> typeDAO;

	/** An isntance of the class DAO. */
	private D classDAO;

	/** The validators. */
	private List<Validator<T>> validators;

	/**
	 * Instantiates a new abstract BLL.
	 */
	@SuppressWarnings("unchecked")
	public AbstractBLL() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.typeDAO = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
		try {
			classDAO = typeDAO.newInstance();
			validators = getValidators();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Validate all the validators.
	 *
	 * @param p
	 *            the model object to validate
	 * @return true, if successful
	 */
	public boolean validateAll(T p) {
		for (Validator<T> validator : validators) {
			if (validator.validate((T) p) == false)
				return false;
		}
		return true;
	}

	/**
	 * Find model object by id.
	 *
	 * @param id
	 *            an id to search for in the table
	 * @return object instance if found, null otherwise
	 */
	public T findById(int id) {
		T t = (T) classDAO.findById(id);
		try {
			if (t == null) {
				throw new NoSuchElementException("The Product with id =" + id + " was not found!");
			} else {
				// check validators
				if (validateAll(t) == false)
					return null;
				return t;
			}
		} catch (NoSuchElementException e) {
			LOGGER.log(Level.WARNING, type.getName() + "BLL:findById " + e.getMessage());
			return null;// when calling this method, it must be checked whether
						// it returns NULL or not
		}
	}

	/**
	 * Select all elements in a given table and return all as a List.
	 *
	 * @return list of objects if found, null otherwise
	 */
	public List<T> findAll() {
		List<T> objects = classDAO.findAll();
		try {
			if (objects == null) {
				throw new NoSuchElementException("No product was found!");
			} else {
				for (T object : objects) {
					// check validators
					if (validateAll(object) == false)
						return null;
				}
				return objects;
			}
		} catch (NoSuchElementException e) {
			LOGGER.log(Level.WARNING, type.getName() + "BLL:findAll " + e.getMessage());
			return null;// when calling this method, it must be checked whether
						// it returns NULL or not
		}
	}

	/**
	 * Update a model object.
	 *
	 * @param t
	 *            the object to update 
	 * @return 1 if successful, 0 if unsuccessfull
	 */
	public int update(T t) {
		// FIRST validate, then update
		if (validateAll(t) == false)
			return 0;
		int result = classDAO.update(t);
		return result;
	}

	/**
	 * Insert a model object.
	 *
	 * @param t
	 *            the object to insert
	 * @return 1 if successful, 0 if unsuccessfull
	 */
	public int insert(T t) {
		// FIRST validate, then insert
		if (validateAll(t) == false)
			return 0;
		int result = classDAO.insert(t);
		return result;
	}

	/**
	 * Delete a model object.
	 *
	 * @param t
	 *            the object to delete
	 * @return 1 if successful, 0 if unsuccessfull
	 */
	public int delete(T t) {
		int result = classDAO.delete(t);
		return result;
	}

	/**
	 * Gets the validators.
	 *
	 * @return the validators
	 */
	public abstract List<Validator<T>> getValidators();

}
