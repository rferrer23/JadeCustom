import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.agent.SystemService;
import org.apache.agent.goal.Goal;
import org.apache.agent.rule.Result;
import org.apache.agent.rule.Rule;

public class Main {

	public static void main(String[] args) throws Exception {
		SystemService s = new SystemService();

		List<Class> params = new ArrayList<>();
		params.add(Integer.class);
		params.add(Integer.class);
		
		Rule rule = new Rule(params, l -> fibo(l));
		s.registerRule(rule, params);
		
		List<Class> paramsGoal = new ArrayList<>();
		paramsGoal.add(Integer.class);
		
		Goal goal = new Goal(paramsGoal, n -> n.get(0));
		s.registerGoal(goal , paramsGoal);
		
		Result r = new Result(Arrays.asList(1,5));
		System.out.println(s.run(r ));
		
	}
	
	public static Result fibo(List t) {
		Integer prof = (Integer)t.get(1);
		if(prof==0) {
			List args=Arrays.asList((Integer)t.get(0));
			return new Result(args);
		}
		
		return new Result(Arrays.asList(((Integer)t.get(0))*prof,prof-1));
		
	}

}
