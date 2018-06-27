package model;

import bll.CategoryBLL;


/**
 * The Class Category.
 */
public class Category extends AbstractModel<CategoryBLL> {

	/** The id. */
	private int id;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new category.
	 */
	public Category() {

	}

	/**
	 * Instantiates a new category.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 */
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * Gets the id.
	 *
	 * @return int
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

	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}
