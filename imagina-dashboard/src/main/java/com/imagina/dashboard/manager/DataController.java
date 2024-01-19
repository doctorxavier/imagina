package com.imagina.dashboard.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imagina.business.wowza.IDataManager;
import com.imagina.dao.imagina.IDataCenterInfoDao;
import com.imagina.dao.imagina.IInfoDao;
import com.imagina.dao.nagios.IBitrateDao;
import com.imagina.dao.nice.IStreamDao;
import com.imagina.dao.nice.ISystemDao;
import com.imagina.dashboard.util.model.parser.WowzaInfoParser;
import com.imagina.model.BitrateInfo;
import com.imagina.model.DataCenter;
import com.imagina.model.DataCenterInfo;
import com.imagina.model.Info;
import com.imagina.model.StreamInfo;
import com.imagina.model.SystemInfo;
import com.imagina.model.nice.Stream;
import com.imagina.model.wowza.WowzaInfo;
import com.imagina.util.json.JSON;

@Controller("dataController")
public class DataController {

	// private static Logger logger = LoggerFactory.getLogger(DataController.class);

	@Autowired
	private IDataManager		dataManager;

	@Autowired
	private IStreamDao			streamDao;

	@Autowired
	private ISystemDao			systemDao;

	@Autowired
	private IBitrateDao			bitrateDao;

	@Autowired
	private IInfoDao			infoDao;

	@Autowired
	private IDataCenterInfoDao	dataCenterInfoDao;
	
	private String state;
	
	@Value("${imagina.dashboard.from}")
	private Integer from;

	public void setDataManager(IDataManager dataManager) {
		this.dataManager = dataManager;
	}
	
	public void cleanInfo() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		
		this.infoDao.cleanFrom(calendar.getTime());
	}
	
	public void hourCleanInfo(Integer sec) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, Integer.valueOf("-3"));
		
		this.infoDao.cleanFrom(calendar.getTime(), sec);
	}
	
	public void dayCleanInfo(Integer sec) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		
		this.infoDao.cleanFrom(calendar.getTime(), sec);
	}
	
	public void weekCleanInfo(Integer sec) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, Integer.valueOf("-7"));
		
		this.infoDao.cleanFrom(calendar.getTime(), sec);
	}
	
	public void monthCleanInfo(Integer sec) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		
		this.infoDao.cleanFrom(calendar.getTime(), sec);
	}

	public void refreshInfo(Integer sec) {
		WowzaInfoParser wowzaInfoParser = new WowzaInfoParser();
		WowzaInfo wowzaInfo = this.dataManager.loadData();
		Info info = wowzaInfoParser.unwrapDataCenterInfo(wowzaInfo);
		
		Map<String, BitrateInfo> bitrateInfos = BitrateInfo.parseBitrate(this.bitrateDao.getBitrates());
		
		for (DataCenterInfo dataCenterInfo : info.getDataCenterInfos()) {
			
			BitrateInfo bitRateInfo = bitrateInfos.get(DataCenter.parse(dataCenterInfo.getDataCenter()));

			dataCenterInfo.setDownload(bitRateInfo.getDownload());
			dataCenterInfo.setUpload(bitRateInfo.getUpload());
		}
		
		info.setSec(sec);
		this.infoDao.save(info);
	}
	
	public void refreshInfoRange(Integer sec) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, -sec);
		List<DataCenterInfo> dataCenterInfos = this.dataCenterInfoDao.getAveraged(this.from, calendar.getTime());

		Info info = new Info();
		
		if (!dataCenterInfos.isEmpty()) {
			info.setDataCenterInfos(dataCenterInfos);
			info.setDate(new Date());
			info.setSec(sec);
			for (DataCenterInfo dataCenterInfo : dataCenterInfos) {
				dataCenterInfo.setInfo(info);
			}
			this.infoDao.save(info);
		}
	}

	@RequestMapping(value = "/infos.json", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getWowzaInfosJSON(@RequestParam(value = "callback", required = false) String callback,
			@RequestParam(value = "from", required = false) Integer from) {
		WowzaInfoParser wowzaInfoParser = new WowzaInfoParser();
		List<WowzaInfo> wowzaInfos = new ArrayList<WowzaInfo>(0);
		List<Info> infos = new ArrayList<Info>(0);
		if (from != null) {
			infos = this.infoDao.getAllFiltered(from);

		} else {
			infos = this.infoDao.getAllFiltered(this.from);
		}
		for (Info info : infos) {
			wowzaInfos.add(wowzaInfoParser.wrapDataCenterInfo(info));
		}
		return JSON.toJSONP(callback, "infos", wowzaInfos);
	}

	@RequestMapping(value = "/info.json", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getWowzaInfoJSON(@RequestParam(value = "callback", required = false) String callback) {
		WowzaInfoParser wowzaInfoParser = new WowzaInfoParser();
		WowzaInfo wowzaInfo = new WowzaInfo();
		Info info = this.infoDao.getLast();
		if (info != null) {
			wowzaInfo = wowzaInfoParser.wrapDataCenterInfo(info);
		}
		return JSON.toJSONP(callback, "info", wowzaInfo);
	}

	@RequestMapping(value = "/edgeInfos.json", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getWowzaEdgeInfosJSON(@RequestParam(value = "callback", required = false) String callback) {
		WowzaInfoParser wowzaInfoParser = new WowzaInfoParser();
		WowzaInfo wowzaInfo = new WowzaInfo();
		Info info = this.infoDao.getLastFilled();
		if (info != null) {
			wowzaInfo = wowzaInfoParser.wrapDataCenterInfo(info);
		}
		return JSON.toJSONP(callback, "edgeInfos", wowzaInfo);
	}

	@RequestMapping(value = "/streams.json", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getStreamInfosJSON(@RequestParam(value = "callback", required = false) String callback) {
		List<StreamInfo> streamInfos = new ArrayList<StreamInfo>(0);
		for (Stream stream : this.streamDao.getStreams()) {
			StreamInfo streamInfo = new StreamInfo();

			if (stream.getMultibitrate() == 1) {
				streamInfo.setName(stream.getPublishResource() + "_MB_" + stream.getBitrate());
			} else {
				streamInfo.setName(stream.getPublishResource());
			}

			streamInfo.setUrl(stream.getUrl());

			streamInfos.add(streamInfo);
		}
		return JSON.toJSONP(callback, "streams", streamInfos);
	}

	@RequestMapping(value = "/systems.json", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getSystemInfosJSON(@RequestParam(value = "callback", required = false) String callback) {
		List<SystemInfo> systemInfos = new ArrayList<SystemInfo>(0);

		for (com.imagina.model.nice.System system : this.systemDao.getSystems()) {
			SystemInfo systemInfo = new SystemInfo();
			systemInfo.setName(system.getName());
			systemInfo.setCount(system.getCount());
			systemInfos.add(systemInfo);
		}
		return JSON.toJSONP(callback, "systems", systemInfos);
	}
	
	@RequestMapping(value = "/state.json", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getStateJSON(@RequestParam(value = "callback", required = false) String callback) {
		if (this.state == null) {
			this.state = "";
		}
		if (callback != null) {
			return callback + "(" + this.state + ")";
		} else {
			return this.state;
		}
	}
	
	@RequestMapping(value = "/setstate", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void setStateJSON(@RequestParam(value = "callback", required = false) String callback, @RequestParam(value = "state", required = false) String state) {
		this.state = state;
	}

}
