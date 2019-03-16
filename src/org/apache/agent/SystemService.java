package org.apache.agent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.agent.belief.Belief;
import org.apache.agent.belief.TypeList;
import org.apache.agent.goal.Goal;
import org.apache.agent.rule.Result;
import org.apache.agent.rule.Rule;


public class SystemService {
	
	protected Map<String, Belief> beliefs;
	
	private Map<TypeList, Rule> rules;
	
	private Map<TypeList, Goal> goals;
	

	public SystemService() {
		beliefs = new HashMap<>();
		rules   = new HashMap<>();
		goals   = new HashMap<>();
	}
	
	public void registerBelief(Belief belief, String name) {
		beliefs.put(name, belief);
	}
	
	public void registerRule(Rule rule,List<Class> params) {
		rules.put(new TypeList(params), rule);
	}
	
	public void registerGoal(Goal goal,List<Class> params) {
		goals.put(new TypeList(params), goal);
	}
	
	public Object run(Result r) throws Exception {
		Goal goalsFound = goals.get(new TypeList(r.getParameters()));
		if(goalsFound!=null) {
			return goalsFound.runAction(r);
		}
		Rule rulesFound = rules.get(new TypeList(r.getParameters()));
		try {
			if(rulesFound != null) {
				return run(rulesFound.runAction(r));
			}
		}catch(Exception e) {
			
		}

		throw new Exception();
	}

}
