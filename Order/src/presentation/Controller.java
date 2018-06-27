package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import bll.data.OrderFile;
import bll.validators.DateValidator;
import model.AbstractModel;
import model.Orders;

/**
 * The Class Controller. It adds the functionality of the Model class to the
 * View.
 */
public class Controller {

	/** The LOGGER for this class. */
	protected static final Logger LOGGER = Logger.getLogger(Controller.class.getName());

	/** The view. */
	private View view;

	/** The model. */
	private Model model;

	/**
	 * Instantiates a new controller. Adds listeners to the buttons. Sets the table
	 * content to the Client table. Shows the view frame.
	 * 
	 *
	 * @param view
	 *            the view
	 * @param model
	 *            the model
	 */
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		this.view.addClientButtonListener(new ClientListener());
		this.view.addProductButtonListener(new ProductListener());
		this.view.addUpdateButtonListener(new UpdateListener());
		this.view.addInsertButtonListener(new InsertListener());
		this.view.addDeleteButtonListener(new DeleteListener());
		this.view.addSearchButtonListener(new SearchListener());
		this.view.addSupplierButtonListener(new SupplierListener());
		this.view.addOrderButtonListener(new OrderListener());
		this.view.addCategoryButtonListener(new CategoryListener());
		this.view.addShipperButtonListener(new ShipperListener());
		this.view.addTotalButtonListener(new TotalListener());

		view.setTableContent(TableContent.CLIENT);
		view.setTable(createTable(model.getClients()));
		this.view.addFrame();
	}

	/**
	 * Reflection method which creates a TableModel based on a list of objects. It
	 * extracts the object's type and fields and sets the names of the column and
	 * the data in the rows accordingly.
	 *
	 * @param objects
	 *            the objects
	 * @return the default table model
	 */
	public DefaultTableModel createTable(List<?> objects) {
		if (objects.size() > 0) {
			Object rowData[][] = new String[objects.size()][objects.get(0).getClass().getDeclaredFields().length];
			Object columnNames[] = new String[objects.get(0).getClass().getDeclaredFields().length];

			for (int i = 0; i < objects.size(); i++) {
				int j = 0;
				Object object = objects.get(i);
				for (Field field : object.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					Object value;
					try {
						value = field.get(object);
						rowData[i][j] = value.toString();
						if (i == 0)
							columnNames[j] = field.getName();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					j++;
				}
			}
			return new DefaultTableModel(rowData, columnNames);
		}
		return new DefaultTableModel();
	}

	/**
	 * It is the inverse of the method 'createTable'. It creates an object from a
	 * row in the database using reflection
	 *
	 * @param p
	 *            the object whose fields need to be modified according to the data
	 *            presnet in the row
	 * @param m
	 *            data from the table
	 * @param row
	 *            the row from which to extract the new fields
	 * @return the same object with the updated field values
	 */
	public AbstractModel<?> setFiels(AbstractModel<?> p, DefaultTableModel m, int row) {
		int j = 0;

		for (Field field : p.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				if (field.getType().equals(int.class))
					field.setInt(p, Integer.parseInt(m.getValueAt(row, j).toString()));
				if (field.getType().equals(String.class))
					field.set(p, m.getValueAt(row, j));
				else if (field.getType().equals(java.sql.Date.class))
					field.set(p, java.sql.Date.valueOf(m.getValueAt(row, j).toString()));
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			j++;
		}
		return p;
	}

	/**
	 * Construct model object from an input dialog. It uses reflection to set the
	 * fields of the object
	 *
	 * @return the newly created model object
	 */
	public AbstractModel<?> constructModelFromDialogPane() {
		AbstractModel<?> newObject = model.getCorrespondingModelObject(view.getTableContent());
		Field field;
		Object[] a = constructPaneObject(newObject);
		if (JOptionPane.showConfirmDialog(null, a, "INSERT", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			for (int i = 0; i < newObject.getClass().getDeclaredFields().length; i++) {
				field = newObject.getClass().getDeclaredFields()[i];
				field.setAccessible(true);
				JTextField f = (JTextField) a[2 * i + 1];
				try {
					if (field.getType().equals(int.class))
						field.setInt(newObject, Integer.parseInt(f.getText()));
					else if (field.getType().equals(String.class))
						field.set(newObject, f.getText());
					else if (field.getType().equals(java.sql.Date.class)) {
						if (DateValidator.validateString(f.getText()))
							field.set(newObject, java.sql.Date.valueOf(f.getText()));
						else
							return null;
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					LOGGER.log(Level.WARNING, " insert " + e.getMessage());
					View.showMessage("INSERT NOT SUCCESSFULL", JOptionPane.WARNING_MESSAGE);
					return null;// invalid data, do not insert anything
				}
			}
			return newObject;
		}
		return null;
	}

	/**
	 * Construct pane panel using reflection. The panel acts as an input for the
	 * user to insert a row in a table
	 *
	 * @param newObject
	 *            the object from which the field names need to be extracted and
	 *            corresponding JTextFields are created for input
	 * @return an array of objects containing the 'model' of the panel which will be
	 *         used by the method 'constructModelFromDialogPane()' to show a panel
	 *         to the user for entering the fields for an insertion
	 * 
	 */
	public Object[] constructPaneObject(AbstractModel<?> newObject) {
		Object[] a = new Object[2 * newObject.getClass().getDeclaredFields().length];
		Field field;
		for (int i = 0; i < 2 * newObject.getClass().getDeclaredFields().length; i++) {
			if (i % 2 == 0) {
				field = newObject.getClass().getDeclaredFields()[i / 2];
				field.setAccessible(true);
				a[i] = field.getName();
			} else {
				if (a[i - 1].toString().equals("date") == false)
					a[i] = new JTextField();
				else if (view.getTableContent() == TableContent.ORDER) {
					long time = System.currentTimeMillis();
					a[i] = new JTextField((new java.sql.Date(time)).toString());
				} else {
					// something
					a[i] = new JTextField("........");
				}
			}
		}
		return a;
	}

	/**
	 * Iterates through all the modified rows and updates all the corresponding
	 * objects. Then it clears the list with the modified rows. It shows a message
	 * with the state of the operation.
	 */
	public void update() {
		int succesfull = 1;
		DefaultTableModel m = view.getTableModel();
		AbstractModel<?> p = null;
		for (Integer row : view.getRowsModified()) {
			TableContent content = view.getTableContent();
			p = model.getCorrespondingModelObject(view.getTableContent());
			p = setFiels(p, m, row);
			if (model.update(p, content) <= 0)
				succesfull = 0;
		}
		// after updating the database, remove all elements
		view.removeAllRowsModified();
		View.showMessageTrue(succesfull, "UPDATE SUCCESSFULL", "UPDATE NOT SUCCESSFULL");
	}

	/**
	 * Iterates through all the modified rows and inserts all the corresponding
	 * objects into the table. Then it clears the list with the modified rows. It shows a message
	 * with the state of the operation.
	 */
	public void insert() {
		int result = 0;
		TableContent content = view.getTableContent();
		AbstractModel<?> newObject = constructModelFromDialogPane();
		if (newObject != null) {
			result = model.insert(newObject, content);
			if (result > 0) {
				if (view.getTableContent() == TableContent.ORDER) {
					// make .txt file for order
					OrderFile.createOrderFile((Orders) newObject);
				}
			}
			View.showMessageTrue(result, "INSERT SUCCESSFULL", "INSERT NOT SUCCESSFULL");
		}
	}

	/**
	 * Iterates through all the modified rows and deletes all the corresponding
	 * objects. Then it clears the list with the modified rows. It shows a message
	 * with the state of the operation.
	 */
	public void delete() {
		TableContent content = view.getTableContent();
		int row = view.getTable().getSelectedRow();
		int result = 0;
		result = model.delete((AbstractModel<?>) model.getCorrespondingModelList(view.getTableContent()).get(row),
				content);
		View.showMessageTrue(result, "DELETE SUCCESSFULL", "DELETE NOT SUCCESSFULL");
	}

	/**
	 * Show a panel with the search options.
	 */
	public void showSearchOptions() {
		AbstractModel<?> newObject = model.getCorrespondingModelObject(view.getTableContent());
		String[] choices = new String[newObject.getClass().getDeclaredFields().length];
		Object[] pane = new Object[4];
		JTextField input = new JTextField("");
		JRadioButton bigger = new JRadioButton("Bigger");
		JRadioButton smaller = new JRadioButton("Smaller");
		ButtonGroup bg = new ButtonGroup();
		bg.add(bigger);
		bg.add(smaller);
		int i = 0;
		for (Field field : newObject.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				choices[i] = field.getName();
				i++;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		JComboBox<String> cb = new JComboBox<String>(choices);
		pane[0] = cb;
		pane[1] = input;
		pane[2] = smaller;
		pane[3] = bigger;
		if (JOptionPane.showConfirmDialog(null, pane, "Search",
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			int index = cb.getSelectedIndex();
			int biggerSmaller = -1;
			if (bigger.isSelected())
				biggerSmaller = 2;
			else if (smaller.isSelected())
				biggerSmaller = 1;
			showList(index, input.getText(), biggerSmaller);
		}
	}

	/**
	 * Show a list of objects which correspond to the search criteria.
	 *
	 * @param index
	 *            the search filter used(it is the same as the field of the object)
	 * @param searchFor
	 *            what to search
	 * @param biggerSmaller
	 *            if it is 2, it will search for values greater
	 *              if it is 1, it will search for values smaller
	 *                  if it is -1, it will search for equal values
	 */
	public void showList(int index, String searchFor, int biggerSmaller) {
		List<Object> searchedObjects = new ArrayList<Object>();
		for (Object object : model.getCorrespondingModelList(view.getTableContent())) {
			if (objectEqual(object, index, searchFor, biggerSmaller))
				searchedObjects.add(object);
		}
		if (searchedObjects.size() > 0)
			view.setTable(createTable(searchedObjects));
		else
			View.showMessage("No element was found", JOptionPane.INFORMATION_MESSAGE);
		view.addFrame();
	}

	/**
	 * Checks if the given object corresponds to the search criteria.
	 *
	 * @param o
	 *            the object which needs to satisfy the search criteria
	 * @param index
	 *            the index of the filter( the same as the index of the field in the object(reflection))
	 * @param searchFor
	 *            what to search
	 * @param biggerSmaller
	 *           if it is 2, it will search for values greater
	 *              if it is 1, it will search for values smaller
	 *                  if it is -1, it will search for equal values
	 * @return true, if successful
	 */
	public boolean objectEqual(Object o, int index, String searchFor, int biggerSmaller) {
		Field field = o.getClass().getDeclaredFields()[index];
		field.setAccessible(true);
		Object value;
		try {
			value = field.get(o);
			if (field.getType().equals(int.class)) {
				if (biggerSmaller == 2) {// search for bigger elements
					if ((int) value > Integer.parseInt(searchFor))
						return true;
				} else if (biggerSmaller == 1) {// search for smaller elements
					if ((int) value < Integer.parseInt(searchFor))
						return true;
				} else if (biggerSmaller == -1) {// search for equal elements
					if ((int) value == Integer.parseInt(searchFor))
						return true;
				}
			} else if (field.getType().equals(String.class)) {
				if (value.toString().toLowerCase().contains(searchFor.toLowerCase())) {
					return true;
				}
			} else if (field.getType().equals(java.sql.Date.class)) {
				java.sql.Date search = java.sql.Date.valueOf(searchFor);

				if (biggerSmaller == 2) {// search for bigger elements
					if (search.compareTo((Date) value) == -1)
						return true;
				} else if (biggerSmaller == 1) {// search for smaller elements
					if (search.compareTo((Date) value) == 1)
						return true;
				} else if (biggerSmaller == -1) {// search for equal elements
					if (search.equals(value)) {
						return true;
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Show total options.
	 */
	public void showTotalOptions() {
		String[] choices;
		TableContent content = view.getTableContent();
		if (content == TableContent.PRODUCT) {
			choices = new String[2];
			choices[1] = "TOTAL NUMBER OF PRODUCTS(sum of quantities)";
		} else if (content == TableContent.ORDER) {
			choices = new String[2];
			choices[1] = "TOTAL NUMBER OF ORDERS(sum of quantities)";
		} else {
			choices = new String[1];
		}
		choices[0] = "TOTAL NUMBER OF " + content.toString() + "S";
		Object[] pane = new Object[1];
		JComboBox<String> cb = new JComboBox<String>(choices);
		pane[0] = cb;

		String n = (String) JOptionPane.showInputDialog(null, "Your Option:", "", JOptionPane.QUESTION_MESSAGE, null,
				choices, choices[0]);
		if (n != null) {
			if (n.equals(choices[0])) {
				View.showMessage("Total number of " + content.toString() + "S="
						+ model.getCorrespondingModelList(content).size(), JOptionPane.INFORMATION_MESSAGE);
			} else if (n.equals(choices[1]) && content == TableContent.PRODUCT) {
				View.showMessage("Total number of PRODUCTS is (sum of quantities)" + model.totalProducts(),
						JOptionPane.INFORMATION_MESSAGE);
			} else if (n.equals(choices[1]) && content == TableContent.ORDER) {
				View.showMessage("Total number of ORDERS is (sum of quantities)" + model.totalOrders(),
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * Update table with the corresponding list.
	 */
	public void updateTable() {
		view.setTable(createTable(model.getCorrespondingModelList(view.getTableContent())));
	}

	/**
	 * Listener for pressing the 'Client' button
	 * @see ClientEvent
	 */
	class ClientListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (view.getRowsModified().size() > 0) {
				if (View.askMessage("Do you want to update the modified rows?")) {
					update();
				}
			}
			view.setTableContent(TableContent.CLIENT);
			updateTable();
			view.addFrame();
		}

	}

	/**
	 *  Listener for pressing the 'Supplier' button
	 *
	 * @see SupplierEvent
	 */
	class SupplierListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (view.getRowsModified().size() > 0) {
				if (View.askMessage("Do you want to update the modified rows?")) {
					update();
				}
			}
			view.setTableContent(TableContent.SUPPLIER);
			updateTable();
			view.addFrame();
		}

	}

	/**
	 *  Listener for pressing the 'Product' button
	 *
	 * @see ProductEvent
	 */
	class ProductListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (view.getRowsModified().size() > 0) {
				if (View.askMessage("Do you want to update the modified rows?")) {
					update();
				}
				// if the user does not want to update the modified values,
				// these(modified) values will be lost
				view.removeAllRowsModified();
			}
			view.setTableContent(TableContent.PRODUCT);
			updateTable();
			view.addFrame();
		}
	}

	/**
	 * Listener for pressing the 'Order' button
	 *
	 * @see OrderEvent
	 */
	class OrderListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (view.getRowsModified().size() > 0) {
				if (View.askMessage("Do you want to update the modified rows?")) {
					update();
				}
				// if the user does not want to update the modified values,
				// these(modified) values will be lost
				view.removeAllRowsModified();
			}
			view.setTableContent(TableContent.ORDER);
			updateTable();
			view.addFrame();
		}
	}

	/**
	 * Listener for pressing the 'Category' button
	 *
	 * @see CategoryEvent
	 */
	class CategoryListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (view.getRowsModified().size() > 0) {
				if (View.askMessage("Do you want to update the modified rows?")) {
					update();
				}
				// if the user does not want to update the modified values,
				// these(modified) values will be lost
				view.removeAllRowsModified();
			}
			view.setTableContent(TableContent.CATEGORY);
			updateTable();
			view.addFrame();
		}
	}

	/**
	 * Listener for pressing the 'Shipper' button
	 *
	 * @see ShipperEvent
	 */
	class ShipperListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (view.getRowsModified().size() > 0) {
				if (View.askMessage("Do you want to update the modified rows?")) {
					update();
				}
				// if the user does not want to update the modified values,
				// these(modified) values will be lost
				view.removeAllRowsModified();
			}
			view.setTableContent(TableContent.SHIPPER);
			updateTable();
			view.addFrame();
		}
	}

	/**
	 *  Listener for pressing the 'Update' button
	 *
	 * @see UpdateEvent
	 */
	class UpdateListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// first, update the database with the new values
			if (view.getTableContent() != TableContent.ORDER) {// if we want to update an order, then we need to know
																// the previous state- it is a lot harder so instead of
																// updating an order, we simply delete it and insert it
																// again
				update();
				updateTable();
				view.addFrame();
			}
		}
	}

	/**
	 *  Listener for pressing the 'Insert' button
	 *
	 * @see InsertEvent
	 */
	class InsertListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// first, insert the new rows into the database
			insert();
			updateTable();
			view.addFrame();
		}
	}

	/**
	 *  Listener for pressing the 'Delete' button
	 *
	 * @see DeleteEvent
	 */
	class DeleteListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			delete();
			updateTable();
			view.addFrame();
		}
	}

	/**
	 *  Listener for pressing the 'Search' button
	 *
	 * @see SearchEvent
	 */
	class SearchListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			showSearchOptions();
		}
	}

	/**
	 *  Listener for pressing the 'Totals' button
	 *
	 * @see TotalEvent
	 */
	class TotalListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			showTotalOptions();
		}
	}

}
