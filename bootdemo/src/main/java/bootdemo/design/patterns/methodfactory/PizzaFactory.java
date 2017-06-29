package bootdemo.design.patterns.methodfactory;

import bootdemo.design.patterns.factory.Pizza;

public interface PizzaFactory {
    /**
     * 工厂方法
     * @return
     */
    public Pizza createPizza();
}
