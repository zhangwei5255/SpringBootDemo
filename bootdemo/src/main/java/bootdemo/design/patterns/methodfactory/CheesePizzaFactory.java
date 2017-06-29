package bootdemo.design.patterns.methodfactory;

import bootdemo.design.patterns.factory.product.CheesePizza;
import bootdemo.design.patterns.factory.product.Pizza;

public class CheesePizzaFactory implements PizzaFactory{
    @Override
    public Pizza createPizza() {
        return new CheesePizza();
    }
}
