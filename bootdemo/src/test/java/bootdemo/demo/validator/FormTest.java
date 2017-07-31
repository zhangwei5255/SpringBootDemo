package bootdemo.demo.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=bootdemo.hello.Application.class)			//SPRINGの情報
public class FormTest {
	private  final Logger logger = LogManager.getLogger(this.getClass());

	 @Autowired
     private Validator validator;

    private javax.validation.Validator validator2 = Validation.buildDefaultValidatorFactory().getValidator();


    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setBasename("classpath:/ErrorResources");
        localValidatorFactoryBean.setValidationMessageSource(reloadableResourceBundleMessageSource);
        return localValidatorFactoryBean;
    }

    @Test
    public void validate_単項目チェックでエラー() {

    /*
     @Autowired
     private Validator validator;
     @Override
    	    public Person process(Person item) throws Exception {
    	        DataBinder binder = new DataBinder(item);
    	        binder.setValidator(validator);
    	        binder.validate();
    	        BindingResult result = binder.getBindingResult();

    	        if (result.hasErrors()) {
    	            log.warn(item.toString());
    	            result.getAllErrors().forEach(s -> log.warn(s.toString()));
    	            return null; // ItemProcessorでnullを返すと後続のWriterで処理されない
    	        }

    	        return item;
    	    }*/
        Form form = new Form();
        List<DataDto> lst = new ArrayList<DataDto>();
        DataDto dto = new DataDto();
        dto.setAddress("東京");
        lst.add(dto);
        dto = new DataDto();
        lst.add(dto);
        form.setAddresses(lst);


        // 単項目チェックでエラーになるため、相関チェックは一切行われない
        Set<ConstraintViolation<Form>> violations = validator2.validate(form);
        System.out.println("violations: " + violations);
        for (ConstraintViolation<Form> violation : violations) {
        	System.out.println(violation.getPropertyPath().toString());
        }

        logger.info("方法２--------------------------------------");
        DataBinder binder = new DataBinder(form);
        binder.setValidator(validator);
        binder.validate();
        BindingResult result = binder.getBindingResult();

        if (result.hasErrors()) {
        	//logger.warn(form.toString());
            result.getAllErrors().forEach(s -> logger.warn(s.toString()));

        }


    }
}