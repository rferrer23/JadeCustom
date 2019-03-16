package org.apache.agent.belief;

public class Belief<T> {

	private T value;
	
	private Class<T> type;
	
	
	public Belief(T value, Class<T> type) {
		checkNotNullAssert(value,"value");
		checkNotNullAssert(type,"value");
		this.value = value;
		this.type = type;
	}


	public T getValue() {
		return value;
	}


	public void setValue(T value) {
		this.value = value;
	}


	public Class<T> getType() {
		return type;
	}


	public void setType(Class<T> type) {
		this.type = type;
	}
	
	private void checkNotNullAssert(Object value,String msg) {
		if(value==null) {
			throw new IllegalArgumentException(msg);
		}
	}


}
