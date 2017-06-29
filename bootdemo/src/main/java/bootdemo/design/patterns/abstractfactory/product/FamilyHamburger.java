package bootdemo.design.patterns.abstractfactory.product;

public class FamilyHamburger implements Hamburger{
    @Override
    public void create() {
        System.out.println("家庭套餐汉堡");
    }
}
