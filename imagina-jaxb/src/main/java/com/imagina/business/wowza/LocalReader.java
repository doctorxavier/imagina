package com.imagina.business.wowza;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imagina.model.wowza.DataCenter;
import com.imagina.model.wowza.WowzaMediaServerPro;
import com.imagina.util.xml.Parser;
import com.imagina.util.xml.jaxb.JAXBParser;

public class LocalReader implements IReader {

	private static Logger	logger	= LoggerFactory.getLogger(LocalReader.class);

	public WowzaMediaServerPro read(String name) {
		try {
			Parser<WowzaMediaServerPro> parser = new JAXBParser<WowzaMediaServerPro>();
			return parser.unmarshall(WowzaMediaServerPro.class, Thread.currentThread().getContextClassLoader().getResourceAsStream(name));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	public List<WowzaMediaServerPro> getData(DataCenter dataCenter) {
		List<WowzaMediaServerPro> wowzaMediaServerPros = new ArrayList<WowzaMediaServerPro>(0);
		for (int i = 1; i <= RemoteReader.NODES; i++) {
			String file = "serverinfo-" + dataCenter + "-edge0" + i + ".xml";
			WowzaMediaServerPro wowzaMediaServerPro = this.read(file);
			wowzaMediaServerPro.setName("edge0" + i);
			wowzaMediaServerPros.add(wowzaMediaServerPro);
		}
		return wowzaMediaServerPros;
	}

}
