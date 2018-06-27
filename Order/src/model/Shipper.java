package model;

import bll.ShipperBLL;


/**
 * The Class Shipper.
 */
public class Shipper extends AbstractModel<ShipperBLL>{
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The shipping time. */
	private int shippingTime;
	
	/** The phone. */
	private String phone;

	/**
	 * Instantiates a new shipper.
	 */
	public Shipper() {

	}

	/**
	 * Instantiates a new shipper.
	 *
	 * @param id the id
	 * @param name the name
	 * @param shippingTime the shipping time
	 * @param phone the phone
	 */
	public Shipper(int id, String name, int shippingTime, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.shippingTime = shippingTime;
		this.phone = phone;
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
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the shipping time.
	 *
	 * @return the shipping time
	 */
	public int getShippingTime() {
		return shippingTime;
	}

	/**
	 * Sets the shipping time.
	 *
	 * @param shippingTime the new shipping time
	 */
	public void setShippingTime(int shippingTime) {
		this.shippingTime = shippingTime;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	@Override
	public String toString() {
		return "Shipper [id=" + id + ", name=" + name + ", shippingTime=" + shippingTime + ", phone=" + phone + "]";
	}

}
