package test.imagina;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.imagina.model.wowza.WowzaMediaServerPro;
import com.imagina.util.test.AbstractTest;
import com.imagina.util.xml.jaxb.JAXBParser;

public class TestJSON extends AbstractTest {

	private static Logger	logger	= LoggerFactory.getLogger(TestJSON.class);

	@Test
	public void testUnMarshall() {
		try {
			JAXBParser<WowzaMediaServerPro> parser = new JAXBParser<WowzaMediaServerPro>();
			WowzaMediaServerPro wowzaMediaServerPro = 
					parser.unmarshall(WowzaMediaServerPro.class, new FileInputStream("serverinfo-cdnvir-edge01.xml"));

			ByteArrayOutputStream bufferOut = new ByteArrayOutputStream();
			ObjectMapper mapper = new ObjectMapper();

			mapper.writeValue(bufferOut, wowzaMediaServerPro);

			logger.info(bufferOut.toString());
		} catch (FileNotFoundException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} catch (JsonGenerationException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} catch (JsonMappingException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

}
