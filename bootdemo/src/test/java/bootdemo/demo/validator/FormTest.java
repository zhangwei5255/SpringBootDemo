package bootdemo.demo.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

public class FormTest {


    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

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


        // 単項目チェックでエラーになるため、相関チェックは一切行われない
        Set<ConstraintViolation<Form>> violations = validator.validate(form);
        System.out.println("violations: " + violations);
        for (ConstraintViolation<Form> violation : violations) {
        	System.out.println(violation.getPropertyPath().toString());
        }

    }
}