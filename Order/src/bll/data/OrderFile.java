package bll.data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import bll.ClientBLL;
import bll.ProductBLL;
import bll.ShipperBLL;
import model.Client;
import model.Orders;
import model.Product;
import model.Shipper;


/**
 * The Class OrderFile. It is used to create a .txt file for the order.
 */
public class OrderFile {

	/** used to store orders in files. */
	private static PrintWriter writer; 

	/**
	 * Creates a .txt file for the order.
	 *
	 * @param order
	 *            the order to store as .txt file
	 */
	public static void createOrderFile(Orders order) {
		try {
			writer = new PrintWriter("invoices/Order #" + order.getId() + ".txt", "UTF-8");
			writer.println("************************************************************************************");
			writer.println("*************ORDER #" + order.getId() + "*********************");
			writer.println();
			ClientBLL clientBLL = new ClientBLL();
			ProductBLL productBLL = new ProductBLL();
			ShipperBLL shipperBLL = new ShipperBLL();
			Client client = clientBLL.findById(order.getIdClient());
			Product product = productBLL.findById(order.getIdProduct());
			Shipper shipper = shipperBLL.findById(order.getIdShipper());

			writer.println("Client name: " + client.getName());
			writer.println("Client Address: " + client.getAddress());
			writer.println("Client E-mail: " + client.getEmail());
			writer.println("Client Phone: " + client.getPhone());
			writer.println();
			writer.println("Product name: " + product.getName());
			writer.println("Product Unit Price: " + product.getUnitPrice());
			writer.println("Number bought: " + order.getQuantity() + " pcs");
			writer.println();
			writer.println("Shipper name: " + shipper.getName());
			writer.println("Shipper phone: " + shipper.getPhone());
			writer.println("Expected shipping time: " + shipper.getShippingTime() + " days");
			writer.println();
			writer.println("TOTAL PRICE: " + order.getQuantity() * product.getUnitPrice());
			writer.println("DATE: " + order.getDate());

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
}
