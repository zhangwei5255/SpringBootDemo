package bootdemo.design.patterns;

import bootdemo.design.patterns.abstractfactory.Factory;
import bootdemo.design.patterns.abstractfactory.FamilyFactory;
import bootdemo.design.patterns.abstractfactory.SingleFactory;
import bootdemo.design.patterns.abstractfactory.product.Hamburger;
import bootdemo.design.patterns.abstractfactory.product.Pizza;

//实际常见的应用 JAVAのJDBC
public class TestAbstractFactory {
	public static void main(String[] args){
        Factory factory=new SingleFactory();
        Pizza pizza=factory.createPizza();
        pizza.create();
        Hamburger hamburger=factory.createHamburger();
        hamburger.create();
        factory= new FamilyFactory();
        pizza=factory.createPizza();
        pizza.create();
        hamburger=factory.createHamburger();
        hamburger.create();
    }
}
