package org.apache.agent.goal;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.agent.belief.Belief;
import org.apache.agent.rule.Result;
@SuppressWarnings("rawtypes")
public class Goal {
	

	private List<Class> parameters;
	
	private Function<List,Object> result;
	
	private Map<String, Belief> beliefs;
	
	public Goal(List<Class> parameters,Function<List,Object> result) {
		checkNotNullAssert(parameters,"params");
		checkNotNullAssert(result,"result");
		this.parameters = parameters;
		this.setResult(result);
	}
	
	public List<Class> getParameters() {
		return parameters;
	}

	public void setParameters(List<Class> parameters) {
		this.parameters = parameters;
	}

	private void checkNotNullAssert(Object value,String msg) {
		if(value==null) {
			throw new IllegalArgumentException(msg);
		}
	}

	public Function<List,Object> getResult() {
		return result;
	}

	public void setResult(Function<List,Object> result) {
		this.result = result;
	}

	public Map<String, Belief> getBeliefs() {
		return beliefs;
	}

	public void setBeliefs(Map<String, Belief> beliefs) {
		this.beliefs = beliefs;
	}
	
	public  Object runAction(Result r) {
		return result.apply(r.getArguments());
	}

}
