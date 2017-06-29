package bootdemo.design.patterns.abstractfactory;

import bootdemo.design.patterns.abstractfactory.product.Hamburger;
import bootdemo.design.patterns.abstractfactory.product.Pizza;

public interface Factory {
    public Pizza createPizza();
    public Hamburger createHamburger();
}