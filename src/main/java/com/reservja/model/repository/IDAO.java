package com.reservja.model.repository;

import java.io.Serializable;
import java.util.List;

public interface IDAO<T, I extends Serializable> {

	void save(T entity);
	
	void remove(Class<T> classe, I pk);
	
	T getById(Class<T> classe, I pk);
	
	List<T> getAll(Class<T> classe);
	
}
