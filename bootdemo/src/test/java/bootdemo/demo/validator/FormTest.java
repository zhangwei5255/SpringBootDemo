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
        Form form = new Form();


        // 単項目チェックでエラーになるため、相関チェックは一切行われない
        Set<ConstraintViolation<Form>> violations = validator.validate(form);
        System.out.println("violations: " + violations);
        for (ConstraintViolation<Form> violation : violations) {
        	System.out.println(violation.getPropertyPath().toString());
        }

    }
}