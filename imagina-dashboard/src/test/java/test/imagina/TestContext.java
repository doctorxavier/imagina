package test.imagina;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imagina.util.test.AbstractTest;

public class TestContext extends AbstractTest {

	private static Logger	logger	= LoggerFactory.getLogger(TestContext.class);

	@Test
	public void test() {
		logger.info("test");
	}

}
