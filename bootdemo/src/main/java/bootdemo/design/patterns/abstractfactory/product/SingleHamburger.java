package bootdemo.design.patterns.abstractfactory.product;

public class SingleHamburger  implements Hamburger{
    @Override
    public void create() {
        System.out.println("单人套餐汉堡");
    }
}