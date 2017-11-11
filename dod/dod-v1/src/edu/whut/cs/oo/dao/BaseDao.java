package edu.whut.cs.oo.dao;

import java.util.List;

import edu.whut.cs.oo.exception.BaseException;

public interface BaseDao<T> {
	T insert(T object) throws BaseException;

	T update(T object) throws BaseException;

	T delete(T object) throws BaseException;

	T findById(long id) throws BaseException;

	List<T> findAllOnes() throws BaseException;
}