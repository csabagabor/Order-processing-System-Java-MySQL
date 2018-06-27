package model;

import bll.SupplierBLL;


/**
 * The Class Supplier.
 */
public class Supplier extends AbstractModel<SupplierBLL>{
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The country. */
	private String country;
	
	/** The city. */
	private String city;
	
	/** The phone. */
	private String phone;

	/**
	 * Instantiates a new supplier.
	 */
	public Supplier() {

	}

	/**
	 * Instantiates a new supplier.
	 *
	 * @param id the id
	 * @param name the name
	 * @param country the country
	 * @param city the city
	 * @param phone the phone
	 */
	public Supplier(int id, String name, String country, String city, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.city = city;
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
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
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
		return "Supplier [id=" + id + ", name=" + name + ", country=" + country + ", city=" + city + ", phone=" + phone
				+ "]";
	}

}
