package test.imagina;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imagina.dao.imagina.IInfoDao;
import com.imagina.dao.nagios.IBitrateDao;
import com.imagina.dao.nice.IStreamDao;
import com.imagina.dao.nice.ISystemDao;
import com.imagina.model.DataCenterInfo;
import com.imagina.model.EdgeInfo;
import com.imagina.model.Info;
import com.imagina.model.nagios.Bitrate;
import com.imagina.model.nice.Stream;
import com.imagina.util.Utilities;
import com.imagina.util.test.AbstractTest;

public class TestContext extends AbstractTest {

	private static Logger	logger	= LoggerFactory.getLogger(TestContext.class);

//	@Test
	public void test() {
		logger.info("Test");
	}
	
//	@Test
	public void testStream() {
		logger.info("testStream");
		try {
			IStreamDao streamDao = (IStreamDao) applicationContext.getBean("streamDao");
			for (Stream stream : streamDao.getStreams()) {
				logger.info("Resource: " + stream.getPublishResource() + ", URL: " + stream.getUrl());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
//	@Test
	public void testSystem() {
		logger.info("testSystem");
		try {
			ISystemDao systemDao = (ISystemDao) applicationContext.getBean("systemDao");
			for (com.imagina.model.nice.System system : systemDao.getSystems()) {
				logger.info("Count: " + system.getCount() + ", Name: " + system.getName());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	//@Test
	public void testBitrate() {
		logger.info("testBitrate");
		try {
			IBitrateDao bitrateDao = (IBitrateDao) applicationContext.getBean("bitrateDao");
			for (Bitrate bitrate : bitrateDao.getBitrates()) {
				logger.info("Name: " + bitrate.getName() + ", Upload: " + bitrate.getOutput() + ", Time: " + bitrate.getTime());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	//@Test
	public void testInfoSave() {
		logger.info("testInfoSave");
		try {
			IInfoDao infoDao = (IInfoDao) applicationContext.getBean("infoDao");
			
			Info info = new Info();
			info.setDate(new Date());
			info.setSec(Integer.valueOf("2"));
			
			List<DataCenterInfo> dataCenterInfos = new ArrayList<DataCenterInfo>(0);
			
			for (int i = 0; i < Integer.valueOf("2"); i++) {
				DataCenterInfo dataCenterInfo = new DataCenterInfo();
				dataCenterInfo.setInfo(info);
				dataCenterInfo.setDataCenter(i + 1);
				dataCenterInfo.setDownload(Double.valueOf("120.0"));
				
				List<EdgeInfo> edgeInfos = new ArrayList<EdgeInfo>(0);
				for (int j = 0; j < Integer.valueOf("8"); j++) {
					EdgeInfo edgeInfo = new EdgeInfo();
					edgeInfo.setDataCenterInfo(dataCenterInfo);
					edgeInfo.setEdge(j + 1);
					//edgeInfo.setId(id);
					edgeInfo.setNumCon(Long.valueOf("11"));
					edgeInfo.setNumConLive(Long.valueOf("22"));
					edgeInfo.setNumConVod(Long.valueOf("33"));
					edgeInfos.add(edgeInfo);
				}
				
				dataCenterInfo.setEdgeInfos(edgeInfos);
				//dataCenterInfo.setId(id);
				dataCenterInfo.setNumCon(Long.valueOf("12"));
				dataCenterInfo.setNumConLive(Long.valueOf("22"));
				dataCenterInfo.setNumConVod(Long.valueOf("33"));
				dataCenterInfo.setUpload(Double.valueOf("65"));
				
				dataCenterInfos.add(dataCenterInfo);
			}
			info.setDataCenterInfos(dataCenterInfos);
			
			infoDao.save(info);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	//@Test
	public void testInfoGetByIdFilled() {
		long start = 0L;
		long elapsed = 0L;
		
		start = System.currentTimeMillis();
		
		logger.info("testInfoGetByIdFilled");
		try {
			IInfoDao infoDao = (IInfoDao) applicationContext.getBean("infoDao");

			Info info = infoDao.getByIdFilled(Integer.valueOf("6"));
			logInfo(info);

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		elapsed = System.currentTimeMillis() - start;
		logger.info("Total Time elapsed: " + Utilities.parseMilliseconds(elapsed));
	}
	
	//@Test
	public void testGetById() {
		long start = 0L;
		long elapsed = 0L;
		
		start = System.currentTimeMillis();
		
		logger.info("testGetById");
		try {
			IInfoDao infoDao = (IInfoDao) applicationContext.getBean("infoDao");
			
			Info info = infoDao.getById(Integer.valueOf("6"));
			logInfo(info);

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		elapsed = System.currentTimeMillis() - start;
		logger.info("Total Time elapsed: " + Utilities.parseMilliseconds(elapsed));
	}
	
	//@Test
	public void testGetAllFiltered() {
		long start = 0L;
		long elapsed = 0L;
		
		start = System.currentTimeMillis();
		
		logger.info("testInfoGetAll");
		try {
			IInfoDao infoDao = (IInfoDao) applicationContext.getBean("infoDao");
			
			Date dateFilter = new Date();
			Calendar cal = new GregorianCalendar();
	        cal.setTimeInMillis(dateFilter.getTime());
	        cal.add(Calendar.DATE, Integer.valueOf("-2"));
	        
			List<Info> infos = infoDao.getAllFiltered(cal.getTime());
			
			for (Info info : infos) {
				logInfo(info);
			}

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		elapsed = System.currentTimeMillis() - start;
		logger.info("Total Time elapsed: " + Utilities.parseMilliseconds(elapsed));
	}
	
	//@Test
	public void testGetLastBack() {
		long start = 0L;
		long elapsed = 0L;
		
		start = System.currentTimeMillis();
		
		logger.info("testInfoGetAll");
		try {
			IInfoDao infoDao = (IInfoDao) applicationContext.getBean("infoDao");
			
			Info info = infoDao.getLastFilled();
			logInfo(info);

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		elapsed = System.currentTimeMillis() - start;
		logger.info("Total Time elapsed: " + Utilities.parseMilliseconds(elapsed));
	}
	
	//@Test
	public void testDeleteFrom() {
		long start = 0L;
		long elapsed = 0L;
		
		start = System.currentTimeMillis();
		
		logger.info("testDeleteFrom");
		try {
			IInfoDao infoDao = (IInfoDao) applicationContext.getBean("infoDao");
			
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, -1);
			
			infoDao.cleanFrom(calendar.getTime(), Integer.valueOf("2"));

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		elapsed = System.currentTimeMillis() - start;
		logger.info("Total Time elapsed: " + Utilities.parseMilliseconds(elapsed));
	}

	private void logInfo(Info info) {
		Integer id = info.getId();
		Date date = info.getDate();
		
		logger.info("Id: " + id);
		logger.info("Date: " + date);
		
		for (DataCenterInfo dataCenterInfo : info.getDataCenterInfos()) {
			
			Integer dataCenter = dataCenterInfo.getDataCenter();
			Double download = dataCenterInfo.getDownload();
			id = dataCenterInfo.getId();
			Long numCon = dataCenterInfo.getNumCon();
			Long numConLive = dataCenterInfo.getNumConLive();
			Long numConVod = dataCenterInfo.getNumConVod();
			Double upload = dataCenterInfo.getUpload();
			
			logger.info("DataCenter: " + dataCenter);
			logger.info("Download: " + download);
			logger.info("Id: " + id);
			logger.info("NumCon: " + numCon);
			logger.info("NumConLive: " + numConLive);
			logger.info("NumConVod: " + numConVod);
			logger.info("Upload: " + upload);
			
			for (EdgeInfo edgeInfo : dataCenterInfo.getEdgeInfos()) {
				id = edgeInfo.getId();
				numCon = edgeInfo.getNumCon();
				numConLive = edgeInfo.getNumConLive();
				numConVod = edgeInfo.getNumConVod();
				logger.info("\tEdge Id: " + id);
				logger.info("\tEdge NumCon: " + numCon);
				logger.info("\tEdge NumConLive: " + numConLive);
				logger.info("\tEdge NumConVod: " + numConVod);
			}
		}
	}
}
