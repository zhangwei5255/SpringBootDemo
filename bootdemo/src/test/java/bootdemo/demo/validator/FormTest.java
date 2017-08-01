package bootdemo.demo.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import bootdemo.hello.util.CommonUtils;
import bootdemo.hello.util.MsgUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = bootdemo.hello.Application.class) // SPRINGの情報
public class FormTest {
	private final Logger logger = LogManager.getLogger(this.getClass());

	public static final String STR_SPLIT_DOT = ".";

	@Autowired
	private Validator validator;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	MsgUtils msgUtils;

	private javax.validation.Validator validator2 = Validation.buildDefaultValidatorFactory().getValidator();



	@Test
	public void validate_単項目チェックでエラー() {

		/*
		 * @Autowired private Validator validator;
		 *
		 * @Override public Person process(Person item) throws Exception {
		 * DataBinder binder = new DataBinder(item);
		 * binder.setValidator(validator); binder.validate(); BindingResult
		 * result = binder.getBindingResult();
		 *
		 * if (result.hasErrors()) { log.warn(item.toString());
		 * result.getAllErrors().forEach(s -> log.warn(s.toString())); return
		 * null; // ItemProcessorでnullを返すと後続のWriterで処理されない }
		 *
		 * return item; }
		 */
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
		DataBinder binder = new DataBinder(form, "form");
		binder.setValidator(validator);
		binder.validate();

		BindingResult result = binder.getBindingResult();

		if (result.hasErrors()) {
			// logger.warn(form.toString());
			result.getAllErrors().forEach(s -> logger.warn(s.toString()));

			Map<String, String> mapMsg = new HashMap<String, String>();
			for (FieldError err : result.getFieldErrors()) {
				List<Object> lstArg = new ArrayList<Object>();
				// KEY
				String key = err.getObjectName() + STR_SPLIT_DOT + err.getField();

				if (mapMsg.keySet().contains(key)) {
					continue;
				}

				if (err.getArguments() != null) {
					Stream.of(err.getArguments()).forEach(p -> {
						if (p instanceof DefaultMessageSourceResolvable) {
							DefaultMessageSourceResolvable dms = (DefaultMessageSourceResolvable) p;


							//String code = StringUtils.replaceAll(dms.getCodes()[0], "¥[[0-9]*¥]", "");
							String code = StringUtils.replaceAll(dms.getCodes()[0], "\\[[0-9]*\\]", "");
							//String code = StringUtils.replaceAll(dms.getCodes()[0], "[1]", "");
							Object filed = messageSource.getMessage(code, null, Locale.getDefault());
							 lstArg.add(filed);

							 // 配列の処理
							 List<Object>  lstIdx =  CommonUtils.groupMatches(dms.getCodes()[0], "\\[[0-9]*\\]");
							 if(CollectionUtils.isNotEmpty(lstIdx)){
								 lstArg.addAll(lstIdx);
							 }



						} else {
							 lstArg.add(p);
						}

					});

					String msg = msgUtils.getMessage(err.getDefaultMessage(), lstArg);
				//	mapMsg.put(key, msg);
					logger.info(msg);

				}else{
					logger.info(err.getDefaultMessage());
				}

			}

		}
	}
}