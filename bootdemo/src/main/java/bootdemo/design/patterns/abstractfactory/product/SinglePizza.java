package bootdemo.design.patterns.abstractfactory.product;

public class SinglePizza implements Pizza{
    @Override
    public void create() {
        System.out.println("单人套餐披萨");
    }
}
