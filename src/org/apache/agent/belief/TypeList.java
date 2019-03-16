package org.apache.agent.belief;

import java.util.List;

public class TypeList {
	
	private List<Class> parameters;
	private int hashCode;

	public TypeList(List<Class> parameters) {
		super();
		this.parameters = parameters;
		this.hashCode=hashCodePre();
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	private int hashCodePre() {
		final int prime = 31;
		int result = 1;
		if(parameters != null) {
			for(Class r:parameters) {
				result = prime * result + ((r == null) ? 0 : r.hashCode());
			}
		}else {
			return prime;
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeList other = (TypeList) obj;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (obj.hashCode()!=this.hashCode())
			return false;
		return true;
	}

}
