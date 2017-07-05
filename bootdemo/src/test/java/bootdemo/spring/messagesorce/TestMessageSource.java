package bootdemo.spring.messagesorce;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class TestMessageSource {



	public static void main(String[] args) throws IOException {
		String fileName = "https://s3-ap-northeast-1.amazonaws.com/bucketzwjp/ErrorResources.properties";

		 ResourceLoader resourceLoader = new DefaultResourceLoader();

			Resource resource = resourceLoader.getResource(fileName);




		ExposedResourceMessageBundleSource  msgSource = new ExposedResourceMessageBundleSource();
		msgSource.setDefaultEncoding("UTF-8");

		msgSource.loadProperties(resource, fileName);
		Properties properties = msgSource.getMessages(Locale.getDefault());

		for(Map.Entry<Object,Object> entry : properties.entrySet()){
			System.out.println(entry.getKey());
		}


	}

}
