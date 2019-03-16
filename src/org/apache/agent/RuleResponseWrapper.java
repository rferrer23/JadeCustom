package org.apache.agent;

import java.util.ArrayList;
import java.util.List;

import org.apache.agent.rule.Rule;

public class RuleResponseWrapper {
	
	Boolean leaf;
	
	Boolean added;

	List<RuleNode> fathers;
	
	Rule rule;
	
	public RuleResponseWrapper(Boolean leaf, Rule rule) {
		super();
		this.leaf = leaf;
		this.rule = rule;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public List<RuleNode> getFathers() {
		if(fathers==null) {
			fathers=new ArrayList<>();
		}
		return fathers;
	}
	
	public void addFathers(RuleNode n) {
		fathers.add(n);
	}

	public Rule getRule() {
		return rule;
	}
	
	public Boolean getAdded() {
		return added;
	}

	public void setAdded(Boolean added) {
		this.added = added;
	}


	
}
