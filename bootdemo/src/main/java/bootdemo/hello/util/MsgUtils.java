package bootdemo.hello.util;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MsgUtils {



	public String getMessage(String pattern , Object ... args){
		return MessageFormat.format(pattern, args);
	}


	public String getMessage(String pattern , List<Object> lstArg){
		return getMessage(pattern,lstArg.toArray(new Object[lstArg.size()]));
	}

}
