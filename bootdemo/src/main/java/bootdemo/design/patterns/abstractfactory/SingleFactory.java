package bootdemo.design.patterns.abstractfactory;

import bootdemo.design.patterns.abstractfactory.product.Hamburger;
import bootdemo.design.patterns.abstractfactory.product.Pizza;
import bootdemo.design.patterns.abstractfactory.product.SingleHamburger;
import bootdemo.design.patterns.abstractfactory.product.SinglePizza;

public class SingleFactory implements Factory{
    @Override
    public Pizza createPizza() {
        return new SinglePizza();
    }

    @Override
    public Hamburger createHamburger() {
        return new SingleHamburger();
    }
}