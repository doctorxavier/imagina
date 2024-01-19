package test.imagina;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imagina.business.wowza.DataManager;
import com.imagina.model.wowza.DataCenter;
import com.imagina.model.wowza.WowzaInfo;
import com.imagina.model.wowza.WowzaMediaServerPro;
import com.imagina.util.Utilities;
import com.imagina.util.test.AbstractTest;
import com.imagina.util.xml.jaxb.JAXBParser;

public class TestDataManager extends AbstractTest {

	private static Logger	logger	= LoggerFactory.getLogger(TestDataManager.class);

	// @Test
	public void test() {
		logger.info(DataCenter.BARCELONA.toString());
	}

	// @Test
	public void testReaderJSON() {
		try {
			DataManager dataManager = new DataManager();
			List<WowzaMediaServerPro> wowzaMediaServerPros = dataManager.getData(DataCenter.BARCELONA);
			ByteArrayOutputStream bufferOut = new ByteArrayOutputStream();
			ObjectMapper mapper = new ObjectMapper();

			for (WowzaMediaServerPro wowzaMediaServerPro : wowzaMediaServerPros) {
				mapper.writeValue(bufferOut, wowzaMediaServerPro);
				logger.info(bufferOut.toString());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	// @Test
	public void testReaderJAXB() {
		try {
			DataManager dataManager = new DataManager();
			List<WowzaMediaServerPro> wowzaMediaServerPros = dataManager.getData(DataCenter.BARCELONA);
			JAXBParser<WowzaMediaServerPro> parser = new JAXBParser<WowzaMediaServerPro>();
			parser.setFormatted(true);

			for (WowzaMediaServerPro wowzaMediaServerPro : wowzaMediaServerPros) {
				logger.info(parser.toString(wowzaMediaServerPro));
			}

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	@Test
	public void testLoadData() {
		long start = 0L;
		long elapsed = 0L;

		start = System.currentTimeMillis();

		DataManager dataManager = new DataManager();

		WowzaInfo wowzaInfo = dataManager.loadData();

		try {
			ByteArrayOutputStream bufferOut = new ByteArrayOutputStream();
			ObjectMapper mapper = new ObjectMapper();

			mapper.writeValue(bufferOut, wowzaInfo);
			logger.info(bufferOut.toString());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		elapsed = System.currentTimeMillis() - start;
		logger.info("Total Time elapsed: " + Utilities.parseMilliseconds(elapsed));
	}

	//@Test
	public void testLoadDataBenck() {
		long start = 0L;
		long elapsed = 0L;

		start = System.currentTimeMillis();

		DataManager dataManager = new DataManager();

		Integer counter = Integer.valueOf("100");

		for (int i = 0; i < counter.intValue(); i++) {
			WowzaInfo wowzaInfo = dataManager.loadData();

			try {
				ByteArrayOutputStream bufferOut = new ByteArrayOutputStream();
				ObjectMapper mapper = new ObjectMapper();

				mapper.writeValue(bufferOut, wowzaInfo);
				logger.info(bufferOut.toString());
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}

		elapsed = System.currentTimeMillis() - start;
		logger.info("Total Time elapsed: " + Utilities.parseMilliseconds(elapsed));
	}

}
