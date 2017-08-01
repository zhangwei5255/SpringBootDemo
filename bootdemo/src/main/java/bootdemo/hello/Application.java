package bootdemo.hello;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@MapperScan("bootdemo.hello.dao")
public class Application {

  @Autowired
  private MessageSource messageSource;

  public static void main(String[] args) throws Exception {

    ConfigurableApplicationContext cac = SpringApplication.run(Application.class, args);
    Application app = cac.getBean(Application.class);
    app.hello();
  }

  public void hello() {
	    System.out.println("hello()");
	  }

  @Autowired
  private Environment env;

  //destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
  @Bean(destroyMethod =  "close")
  public DataSource dataSource() {
	  BasicDataSource dataSource = new BasicDataSource();
      dataSource.setUrl(env.getProperty("spring.datasource.url"));
      dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
      dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
      dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
      dataSource.setInitialSize(3);//初始化时建立物理连接的个数
      dataSource.setMaxTotal(10);//最大连接池数量
      dataSource.setMinIdle(3);//最小连接池数量
      dataSource.setTimeBetweenEvictionRunsMillis(60000);//获取连接时最大等待时间，单位毫秒。
      dataSource.setValidationQuery("SELECT 1 FROM DUAL");//用来检测连接是否有效的sql
      dataSource.setTestOnBorrow(true);//申请连接时执行validationQuery检测连接是否有效
      dataSource.setTestWhileIdle(true);//建议配置为true，不影响性能，并且保证安全性。
      dataSource.setPoolPreparedStatements(false);//是否缓存preparedStatement，也就是PSCache
      return dataSource;
  }

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:ErrorResources", "classpath:ErrorResources2");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
/*		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setBasename("classpath:/ErrorResources");*/
		localValidatorFactoryBean.setValidationMessageSource(messageSource);
		return localValidatorFactoryBean;
	}

}