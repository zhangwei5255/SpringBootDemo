package bootdemo.design.patterns.methodfactory;

import bootdemo.design.patterns.factory.product.GreekPizza;
import bootdemo.design.patterns.factory.product.Pizza;

public class GreekPizzaFactory  implements PizzaFactory{
    @Override
    public Pizza createPizza() {
        return new GreekPizza();
    }
}
