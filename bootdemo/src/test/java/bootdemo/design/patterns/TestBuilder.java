package bootdemo.design.patterns;

import bootdemo.design.patterns.builder.Builder;
import bootdemo.design.patterns.builder.Computer;
import bootdemo.design.patterns.builder.Director;
import bootdemo.design.patterns.builder.LenovoBuilder;

public class TestBuilder {
	   public static void main(String[]args){
	        Builder builder = new LenovoBuilder();
	        Director director = new Director(builder);
	        director.construct();

	        Computer computer = builder.retrieveResult();
	        System.out.println(computer.getHost());
	        System.out.println(computer.getDisplay());
	    }
}
