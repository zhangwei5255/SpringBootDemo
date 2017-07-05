package bootdemo.spring.messagesorce;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;

public class ExposedResourceMessageBundleSource extends ReloadableResourceBundleMessageSource {

    private static final Logger LOGGER = Logger.getLogger(ExposedResourceMessageBundleSource.class);

    @Override
    public Properties loadProperties(Resource resource, String fileName) throws IOException {

        LOGGER.info("Load " + fileName);
        return super.loadProperties(resource, fileName);
    }

    /**
     * Gets all messages for presented Locale.
     * @param locale user request's locale
     * @return all messages
     */
    public Properties getMessages(Locale locale){
        return getMergedProperties(locale).getProperties();
    }
}