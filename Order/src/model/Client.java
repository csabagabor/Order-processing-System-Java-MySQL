package model;

import bll.ClientBLL;


/**
 * The Class Client.
 *
 */
public class Client extends AbstractModel<ClientBLL> {

	/** The id. */
	private int id;

	/** The name. */
	private String name;

	/** The city. */
	private String city;

	/** The address. */
	private String address;

	/** The email. */
	private String email;

	/** The age. */
	private int age;

	/** The phone. */
	private String phone;

	/**
	 * Instantiates a new client.
	 */
	public Client() {

	}

	/**
	 * Instantiates a new client.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param city
	 *            the city
	 * @param address
	 *            the address
	 * @param email
	 *            the email
	 * @param age
	 *            the age
	 * @param phone
	 *            the phone
	 */
	public Client(int id, String name, String city, String address, String email, int age, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.email = email;
		this.age = age;
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
	 * @param id
	 *            the new id
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
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @param city
	 *            the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address
	 *            the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age
	 *            the new age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @param phone
	 *            the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", city=" + city + ", address=" + address + ", email=" + email
				+ ", age=" + age + ", phone=" + phone + "]";
	}

}
