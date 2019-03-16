package org.apache.agent.rule;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("rawtypes")
public class Result {
	
	private List arguments;
	
	private List<Class> parameters;
	
	public Result(List parameters) {
		if(parameters==null) {
			this.arguments = parameters;
		}else {
			this.arguments = parameters;
		}
		if(!parameters.isEmpty()) {
			this.parameters=(List<Class>) parameters.stream().map(n->n.getClass()).collect(Collectors.toList());
		}

	}
	
	public List<Class> getParameters() {
		return parameters;
	}


	public List getArguments() {
		return arguments;
	}


}
