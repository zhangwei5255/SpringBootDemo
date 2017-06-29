package bootdemo.design.patterns.abstractfactory.product;

public class FamilyPizza implements Pizza {
	@Override
	public void create() {
		System.out.println("家庭套餐披萨");
	}
}