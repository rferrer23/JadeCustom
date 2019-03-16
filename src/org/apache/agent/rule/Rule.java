package org.apache.agent.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.agent.belief.Belief;
@SuppressWarnings("rawtypes")
public class Rule {
	
	private List<Class> parameters;
	
	private Function<List,Result> result;
	
	private Map<String, Belief> beliefs;
	
	public Rule(List<Class> parameters,Function<List,Result> result) {
		checkNotNullAssert(parameters,"params");
		checkNotNullAssert(result,"result");
		this.parameters = parameters;
		this.setResult(result);
	}
	
	public List<Class> getParameters() {
		if(parameters==null) {
			parameters=new ArrayList<>();
		}
		return parameters;
	}

	public void setParameters(List<Class> parameters) {
		this.parameters = parameters;
	}

	public Function<List,Result> getResult() {
		return result;
	}

	public void setResult(Function<List,Result> result) {
		this.result = result;
	}

	private void checkNotNullAssert(Object value,String msg) {
		if(value==null) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	public Result runAction(Result r) {
		return result.apply(r.getArguments());
	}

	public Map<String, Belief> getBeliefs() {
		return beliefs;
	}

	public void setBeliefs(Map<String, Belief> beliefs) {
		this.beliefs = beliefs;
	}

}
