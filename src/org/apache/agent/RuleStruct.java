package org.apache.agent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.agent.belief.TypeList;
import org.apache.agent.rule.Rule;

public class RuleStruct {
	
	List<RuleNode> roots;
	
	private Map<TypeList, RuleLeaf> rules;
	
	public void registerRule(Rule rule) {
		
		RuleNode ruleN = new RuleNode(rule);
		boolean leaf = isNotLeft(ruleN, false);
		boolean newRoot = false;
		if(leaf) {
			List<RuleNode> result= new ArrayList<>();
			for(RuleNode child:roots) {
				if(child.match( new RuleNode(rule))) {
					result.add(ruleN);
				}
			}
			registerRuleInternal(new RuleLeaf(rule, result));
		}else {
			if(roots==null) {
				newRoot=true;
				roots=Arrays.asList(ruleN);
			}else {
				List<RuleNode> removeChild = new ArrayList<>();
				boolean failAllMatch = true;
				newRoot = registerAbstractRule(ruleN, removeChild, failAllMatch);
			}
			
			if(newRoot) {
				for(RuleLeaf leafs:rules.values()) {
					if(ruleN.match( new RuleNode(leafs.getRules()))) {
						leafs.getMatchers().add(ruleN);
					}
				}
			}
			
		}
	}
	
	public List<Rule> getRules(TypeList t){
		List<Rule> result= new ArrayList<>();
		RuleLeaf r = rules.get(t);
		if(r==null) {
			for(RuleNode child:roots) {
				result.addAll(getMatchRules(child));
			}
		}

		return result;
	}

	private Collection<? extends Rule> getMatchRules(RuleNode child) {
		List<RuleNode> result = new ArrayList<>();
		for(RuleNode root:roots) {
			result.addAll(root.getMatchingRules(child));
		}
		if(result.isEmpty()) {
			return new ArrayList<>();
		}
		return result.stream().map(n->n.getRule()).collect(Collectors.toList());
	}

	private boolean registerAbstractRule(RuleNode ruleN, List<RuleNode> removeChild,boolean failAllMatch) {
		boolean newRoot = false;
		
		for(RuleNode child:roots) {
			if(child.getWeigh()>ruleN.getWeigh() && ruleN.match(child)) {
				removeChild.add(child);
			}
			
			if(child.match(ruleN)) {
				RuleResponseWrapper sonResponse = child.addChild(ruleN);
				failAllMatch= failAllMatch && !sonResponse.getAdded();
			}
		}
		

		if(failAllMatch || !removeChild.isEmpty()) {
			roots.add(ruleN);
			newRoot=true;
		}
		if(!removeChild.isEmpty()) {
			roots.removeAll(removeChild);
		}
		return newRoot;
	}
	
	public boolean isNotLeft(RuleNode rule, boolean result) {
		List<Class> params = rule.getRule().getParameters();
		
		for(int i=0; i<params.size(); i++) {
			Class param = params.get(i);
			
			if(isValueParam(param)) {
				result = true;
			}
			
			if(isMultiValueParam(param)) {
				result = true;
			}
			if(result) {
				break;
			}
		}
		return result;
	}
	
	private boolean isValueParam(Class param) {
		// TODO Auto-generated method stub
		return AnonymousMonoValueType.class.equals(param);
	}

	private boolean isMultiValueParam(Class param) {
		// TODO Auto-generated method stub
		return AnonymousListType.class.equals(param);
	}

	
	private void registerRuleInternal(RuleLeaf ruleLeaf) {
		rules.put(new TypeList(ruleLeaf.getRules().getParameters()), ruleLeaf);
	}

}


