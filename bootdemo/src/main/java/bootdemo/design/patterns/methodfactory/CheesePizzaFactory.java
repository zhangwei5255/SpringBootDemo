package bootdemo.design.patterns.methodfactory;

import bootdemo.design.patterns.factory.CheesePizza;
import bootdemo.design.patterns.factory.Pizza;

public class CheesePizzaFactory implements PizzaFactory{
    @Override
    public Pizza createPizza() {
        return new CheesePizza();
    }
}
