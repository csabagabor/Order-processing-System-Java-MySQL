package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;


/**
 * The Class AbstractDAO.
 *
 * @param <T>
 *             generic type where T is a model class
 */
public class AbstractDAO<T> {

	/** The LOGGER of this class. */
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	/** The corresponding type of the model class. */
	private final Class<T> type;

	/**
	 * Instantiates a new abstract DAO.
	 */
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	/**
	 * Creates the select query.
	 *
	 * @param field
	 *              the element is searched using this field
	 * @return the query
	 */
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		// System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * Creates the insert query.
	 *
	 * @param t
	 *              the element that needs to be inserted
	 * @return the query
	 */
	private String createInsertQuery(T t) {
		StringBuilder sb = new StringBuilder();
		sb.append("Insert INTO ");
		sb.append(type.getSimpleName());
		sb.append(" VALUES ( ");
		for (int i = 0; i < type.getDeclaredFields().length; i++) {
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length() - 1);// delete last ,(comma)
		sb.append(")");
		// System.out.println("insert:"+sb.toString());
		return sb.toString();
	}

	/**
	 * Creates the update query.
	 *
	 * @param t
	 *              the element that needs to be updated is searched using this field
	 * @return the query
	 */
	private String createUpdateQuery(T t) {
		StringBuilder sb = new StringBuilder();
		sb.append("Update ");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		for (Field field : type.getDeclaredFields()) {
			sb.append(field.getName() + "=?,");
		}
		sb.deleteCharAt(sb.length() - 1);// delete last ,(comma)
		sb.append(" WHERE id=?");
		// System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * Creates the delete query.
	 *
	 * @param field
	 *            the element that needs to be deleted is searched using this field
	 * @return the query
	 */
	private String createDeleteQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		// System.out.println("delete ="+sb.toString());
		return sb.toString();
	}

	/**
	 * Find all elements in a table
	 *
	 * @return a list with the model objects returned
	 */
	public List<T> findAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = sb.toString();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll,query=" + query + "  " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * Find object in database by id.
	 *
	 * @param id
	 *            the id to search for
	 * @return  a new model object having the corresponding id taken as input
	 */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * Updates object t into the database
	 *
	 * @param t
	 *            the model object to insert
	 * @return 1 if successfull, 0 otherwise
	 */
	public int insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		int result = 0;
		String query = createInsertQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int i = 1;
			for (Field field : t.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				value = field.get(t);
				statement.setObject(i, value);
				i++;
			}
			result = statement.executeUpdate();
			return result;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Insert " + e.getMessage());
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Insert " + e.getMessage());
		} catch (IllegalAccessException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return 0;
	}

	/**
	 * Updates object t in the database with its new values
	 *
	 * @param t
	 *            the model object to update
	 * @return 1 if successfull, 0 otherwise
	 */
	public int update(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		int result = 0;
		String query = createUpdateQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int i = 1;
			for (Field field : t.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				value = field.get(t);
				statement.setObject(i, value);
				if (i == 1)
					statement.setObject(t.getClass().getDeclaredFields().length + 1, value);
				i++;
			}
			result = statement.executeUpdate();
			return result;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Update " + e.getMessage());
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Update " + e.getMessage());
		} catch (IllegalAccessException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Update " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return 0;
	}

	/**
	 * Deletes object t from the corresponding table
	 *
	 * @param t
	 *            the model object to delete
	 * @return 1 if successfull, 0 otherwise
	 */
	public int delete(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		int result = 0;
		Field field = t.getClass().getDeclaredFields()[0];
		field.setAccessible(true);
		String query = createDeleteQuery(field.getName());
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			// suppose the first element is always the ID -->> int
			statement.setInt(1, (int) field.get(t));
			result = statement.executeUpdate();
			return result;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Update " + e.getMessage());
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Update " + e.getMessage());
		} catch (IllegalAccessException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Update " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return 0;
	}

	/**
	 * Creates a list of object from the resultset of type 'type'
	 *
	 * @param resultSet
	 *            the result set
	 * @return the list
	 */
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		try {
			while (resultSet.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}
}
