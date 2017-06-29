package bootdemo.design.patterns;

import bootdemo.design.patterns.factory.product.Pizza;
import bootdemo.design.patterns.methodfactory.CheesePizzaFactory;
import bootdemo.design.patterns.methodfactory.GreekPizzaFactory;
import bootdemo.design.patterns.methodfactory.PizzaFactory;

public class TestMethodFactory {

	public static void main(String[] args) {
		PizzaFactory factory = new CheesePizzaFactory();
		Pizza pizza = factory.createPizza();
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		factory = new GreekPizzaFactory();
		pizza = factory.createPizza();
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

	}

}
