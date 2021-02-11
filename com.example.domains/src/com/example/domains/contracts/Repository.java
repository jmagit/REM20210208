package com.example.domains.contracts;

public interface Repository<T, K> {
	void add(T item);
	void modify(T item);
	void remove(T item);
	
	T[] get();
	T get(K id);
}
