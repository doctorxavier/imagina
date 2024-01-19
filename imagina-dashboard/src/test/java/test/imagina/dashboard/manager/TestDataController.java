package test.imagina.dashboard.manager;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imagina.dashboard.manager.DataController;
import com.imagina.util.test.AbstractTest;

public class TestDataController extends AbstractTest {

	private static Logger	logger		= LoggerFactory.getLogger(TestDataController.class);
	
	@Test
	public void testGetWowzaInfoJSON() {
		try {
			DataController dataController = (DataController) applicationContext.getBean("dataController");
			logger.info(dataController.getWowzaInfoJSON(null));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	@Test
	public void testGetWowzaInfosJSON() {
		try {
			DataController dataController = (DataController) applicationContext.getBean("dataController");
			logger.info(dataController.getWowzaInfosJSON(null, null));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	@Test
	public void testGetStreamInfosJSON() {
		try {
			DataController dataController = (DataController) applicationContext.getBean("dataController");
			logger.info(dataController.getStreamInfosJSON(null));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	@Test
	public void testGetSystems() {
		try {
			DataController dataController = (DataController) applicationContext.getBean("dataController");
			logger.info(dataController.getSystemInfosJSON(null));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	@Test
	public void testGetWowzaEdgeInfosJSON() {
		try {
			DataController dataController = (DataController) applicationContext.getBean("dataController");
			logger.info(dataController.getWowzaEdgeInfosJSON(null));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
}
