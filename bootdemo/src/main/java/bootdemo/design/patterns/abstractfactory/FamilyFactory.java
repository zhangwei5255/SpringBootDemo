package bootdemo.design.patterns.abstractfactory;

import bootdemo.design.patterns.abstractfactory.product.FamilyHamburger;
import bootdemo.design.patterns.abstractfactory.product.FamilyPizza;
import bootdemo.design.patterns.abstractfactory.product.Hamburger;
import bootdemo.design.patterns.abstractfactory.product.Pizza;

public class FamilyFactory implements Factory{
    @Override
    public Pizza createPizza() {
        return new FamilyPizza();
    }
    @Override
    public Hamburger createHamburger() {
        return new FamilyHamburger();
    }
}