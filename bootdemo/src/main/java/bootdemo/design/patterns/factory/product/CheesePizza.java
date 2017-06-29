package bootdemo.design.patterns.factory.product;

public class CheesePizza extends Pizza {
	public void prepare() {
		System.out.println("准备CheesePizza~");
	}

	public void bake() {
		System.out.println("正在烤CheesePizza~");
	}

	public void cut() {
		System.out.println("正在切CheesePizza~");
	}

	public void box() {
		System.out.println("正在打包CheesePizza~");
	}
}