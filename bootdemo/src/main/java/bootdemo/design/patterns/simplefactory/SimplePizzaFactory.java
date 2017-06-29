package bootdemo.design.patterns.simplefactory;

import org.springframework.beans.BeanUtils;

import bootdemo.design.patterns.factory.CheesePizza;
import bootdemo.design.patterns.factory.GreekPizza;
import bootdemo.design.patterns.factory.Pizza;

public class SimplePizzaFactory {


	public static <T> T CreatePizza(Class<T> clazz){
		return BeanUtils.instantiate(clazz);
	}
	public static Pizza CreatePizza(String orderType) {
		Pizza pizza = null;
		if (orderType.equals("cheese")) {
			pizza = new CheesePizza();
		} else if (orderType.equals("greek")) {
			pizza = new GreekPizza();
		}
		return pizza;
	}


}
