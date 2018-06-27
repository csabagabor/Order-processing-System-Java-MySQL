package model;

import java.sql.Date;

import bll.OrderBLL;

/**
 * The Class Orders.
 */
public class Orders extends AbstractModel<OrderBLL> {

	/** The id. */
	private int id;

	/** The id client. */
	private int idClient;

	/** The id product. */
	private int idProduct;

	/** The quantity. */
	private int quantity;

	/** The date of the order. */
	private Date date;

	/** The id shipper. */
	private int idShipper;

	/**
	 * Instantiates a new orders.
	 */
	public Orders() {

	}

	/**
	 * Instantiates a new orders.
	 *
	 * @param id
	 *            the id
	 * @param idClient
	 *            the id client
	 * @param idProduct
	 *            the id product
	 * @param quantity
	 *            the quantity
	 * @param date
	 *            the date of the order
	 * @param idShipper
	 *            the id shipper
	 */
	public Orders(int id, int idClient, int idProduct, int quantity, Date date, int idShipper) {
		super();
		this.id = id;
		this.idClient = idClient;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.date = date;
		this.idShipper = idShipper;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the id client.
	 *
	 * @return the id client
	 */
	public int getIdClient() {
		return idClient;
	}

	/**
	 * Sets the id client.
	 *
	 * @param idClient
	 *            the new id client
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	/**
	 * Gets the id product.
	 *
	 * @return the id product
	 */
	public int getIdProduct() {
		return idProduct;
	}

	/**
	 * Sets the id product.
	 *
	 * @param idProduct
	 *            the new id product
	 */
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity
	 *            the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date
	 *            the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the id shipper.
	 *
	 * @return the id shipper
	 */
	public int getIdShipper() {
		return idShipper;
	}

	/**
	 * Sets the id shipper.
	 *
	 * @param idShipper
	 *            the new id shipper
	 */
	public void setIdShipper(int idShipper) {
		this.idShipper = idShipper;
	}

	
	@Override
	public String toString() {
		return "Orders [id=" + id + ", idClient=" + idClient + ", idProduct=" + idProduct + ", quantity=" + quantity
				+ ", date=" + date + ", idShipper=" + idShipper + "]";
	}

}