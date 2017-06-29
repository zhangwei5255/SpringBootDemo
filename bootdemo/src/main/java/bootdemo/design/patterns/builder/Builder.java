package bootdemo.design.patterns.builder;

public interface Builder {
	public void buildHost();

	public void buildDisplay();

	public Computer retrieveResult();
}
