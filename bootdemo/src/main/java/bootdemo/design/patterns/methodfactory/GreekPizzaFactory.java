package bootdemo.design.patterns.methodfactory;

import bootdemo.design.patterns.factory.GreekPizza;
import bootdemo.design.patterns.factory.Pizza;

public class GreekPizzaFactory  implements PizzaFactory{
    @Override
    public Pizza createPizza() {
        return new GreekPizza();
    }
}
