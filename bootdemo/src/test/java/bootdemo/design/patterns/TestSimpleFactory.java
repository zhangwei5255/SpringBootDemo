package bootdemo.design.patterns;

import bootdemo.design.patterns.factory.product.CheesePizza;
import bootdemo.design.patterns.factory.product.GreekPizza;
import bootdemo.design.patterns.factory.product.Pizza;
import bootdemo.design.patterns.simplefactory.SimplePizzaFactory;

public class TestSimpleFactory {

	public static void main(String[] args) {
		Pizza pizza;
		pizza=SimplePizzaFactory.CreatePizza(CheesePizza.class);
		pizza.prepare();

		pizza=SimplePizzaFactory.CreatePizza(GreekPizza.class);
		pizza.prepare();

	}

}
