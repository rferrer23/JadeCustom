package org.apache.agent;

import java.util.ArrayList;
import java.util.List;

import org.apache.agent.rule.Rule;

public class RuleNode {
	
	public int getWeigh() {
		return weigh;
	}

	public void setWeigh(int weigh) {
		this.weigh = weigh;
	}

	int weigh;
	
	Rule rule;
	
	List<RuleNode> childs;
	
	public RuleNode(Rule rule) {
		this.rule=rule;
	}
	
	public RuleResponseWrapper addChild(RuleNode rule) {
		
		boolean result = false;
		RuleResponseWrapper x = new RuleResponseWrapper(result, rule.getRule());
		if(childs.isEmpty()) {
			this.childs.add(rule);
			x.setAdded(true);
		}else{
			boolean added = false;
			List<RuleNode> removeChild = new ArrayList<>();
			for(RuleNode child:childs) {
				if(child.getWeigh()>rule.getWeigh() && rule.match(child)) {
					x.addFathers(child);
				}
				
				if(child.match(rule)) {
					RuleResponseWrapper sonResponse = child.addChild(rule);
					added= added || sonResponse.getAdded();
					removeChild.addAll(sonResponse.getFathers());
				}
			}
			if(added && removeChild.isEmpty()) {
				this.childs.add(rule);
				x.setAdded(true);
			}
			if(!removeChild.isEmpty()) {
				childs.removeAll(removeChild);
				this.childs.add(rule);
				x.setAdded(true);
			}
		}
		return x;
		
	}
	
	public List<RuleNode> getMatchingRules(RuleNode rule){
		List<RuleNode> result = new ArrayList<>();
		if(!this.match(rule)) {
			return result;
		}
		result.add(this);
		if(childs.isEmpty()) {
			return result;
		}
		for(RuleNode child:childs) {
			result.addAll(child.getMatchingRules(rule));
		}
		return result;
	}

	
	public boolean match(RuleNode rule) {
		List<Class> params = rule.getRule().getParameters();
		List<Class> myParams = this.getRule().getParameters();
		boolean result = false;
		int j=0;
		for(int i=0; i<params.size(); i++) {
			result = false;
			Class param = params.get(i);
			Class myParam = myParams.get(j);
			
			if(param.equals(myParam) || isValueParam(myParam)) {
				result = true;
				j++;
			}
			
			if(isMultiValueParam(myParam)) {
				result = true;
			}
			if(!result) {
				return result;
			}
		}
		
		if(myParams.size()>j) {
			return false;
		}
		
		return result;
	}
	
	public Rule getRule(){
		return rule;
	}
	
	private boolean isValueParam(Class param) {
		// TODO Auto-generated method stub
		return AnonymousMonoValueType.class.equals(param);
	}

	private boolean isMultiValueParam(Class param) {
		// TODO Auto-generated method stub
		return AnonymousListType.class.equals(param);
	}

}
