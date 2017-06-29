package bootdemo.design.patterns.builder;

public class HuaweiBuilder implements Builder {
	Computer computer = new Computer();

	/**
	 * 产品零件建造方法1
	 */
	@Override
	public void buildDisplay() {
		// 构建产品的第一个零件
		computer.setDisplay("华为显示器");
	}

	/**
	 * 产品零件建造方法1
	 */
	@Override
	public void buildHost() {
		// 构建产品的第二个零件
		computer.setHost("华为主机");
	}

	/**
	 * 产品返还方法
	 */
	@Override
	public Computer retrieveResult() {
		return computer;
	}
}
