package model;

import java.lang.reflect.ParameterizedType;
import java.util.logging.Logger;
import bll.AbstractBLL;


/**
 * The Class AbstractModel.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractModel<T extends AbstractBLL<?, ?, ?>> {

	/** The LOGGER of this class. */
	protected static final Logger LOGGER = Logger.getLogger(AbstractModel.class.getName());

	/** The type of the bll class. */
	private final Class<T> type;

	/**
	 * Instantiates a new abstract model.
	 */
	@SuppressWarnings("unchecked")
	public AbstractModel() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Creates a new bll object corresponding to the child class this method is invoked in.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the t
	 */
	@SuppressWarnings({ "hiding", "unchecked" })
	public <T extends AbstractBLL<?, ?, ?>> T createBLL() {
		T instance = null;
		try {
			instance = (T) type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return instance;
	}

	
	public abstract String toString();

}
