package org.apache.agent;

import java.util.List;
import java.util.Map;

import org.apache.agent.belief.TypeList;
import org.apache.agent.rule.Rule;

public class RuleLeaf {
	



	Rule rules;

	List<RuleNode> matchers;

	public RuleLeaf(Rule rules, List<RuleNode> matchers) {
		super();
		this.rules = rules;
		this.matchers = matchers;
	}
	public Rule getRules() {
		return rules;
	}

	public List<RuleNode> getMatchers() {
		return matchers;
	}

	
}
