package dao;

import java.util.List;

public abstract class DAO<T>
{
	public abstract void add(T a);

	public abstract void delete(T d);

	public abstract void update(T u);

	public abstract T read(int id);

	public abstract List<T> findAll();
}
