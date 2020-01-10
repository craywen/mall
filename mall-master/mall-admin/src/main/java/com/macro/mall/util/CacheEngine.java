package com.macro.mall.util;

import java.util.Collection;

public interface CacheEngine {
	public static final String DUMMY_FQN = "";
	public static final String NOTIFICATION = "notification";

	/**
	 * Inits the cache engine.
	 */
	public void init();

	/**
	 * Stops the cache engine
	 */
	public void stop();

	/**
	 * Adds a new object to the cache. The fqn will be set as the value of
	 * {@link #DUMMY_FQN}
	 * 
	 * @param key
	 *            The key to associate with the object.
	 * @param value
	 *            The object to cache
	 */
	public void add(String key, Object value);

	/**
	 * 
	 * Adds a new object to the cache.
	 * 
	 * @param fqn
	 *            The fully qualified name of the cache.
	 * @param key
	 *            The key to associate with the object
	 * @param value
	 *            The object to cache
	 */
	public void add(String fqn, String key, Object value);

	/**
	 * Gets some object from the cache.
	 * 
	 * @param fqn
	 *            The fully qualified name associated with the key
	 * @param key
	 *            The key to get
	 * @return The cached object, or <code>null</code> if no entry was found
	 */
	public Object get(String fqn, String key);

	/**
	 * Gets some object from the cache.
	 * 
	 * @param fqn
	 *            The fqn tree to get
	 * @return The cached object, or <code>null</code> if no entry was found
	 */
	public Object get(String fqn);

	/**
	 * Gets all values from some given FQN.
	 * 
	 * @param fqn
	 *            String
	 * @return Collection
	 */
	public Collection getValues(String fqn);

	/**
	 * Removes an entry from the cache.
	 * 
	 * @param fqn
	 *            The fully qualified name associated with the key
	 * @param key
	 *            The key to remove
	 */
	public void remove(String fqn, String key);

	/**
	 * Removes a complete note from the cache
	 * 
	 * @param fqn
	 *            The fqn to remove
	 */
	public void remove(String fqn);
}
