package start;

import java.sql.SQLException;
import java.util.logging.Logger;
import presentation.Controller;
import presentation.View;
import presentation.Model;

/**
 * @author Gabor Csaba Laszlo
 * The Class Start containing the main method
 */
public class Start {
	
	/** The LOGGER for the Start class */
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		View view = new View();
		Controller con = new Controller(view, new Model());
	}

}
