package com.imagina.business.wowza;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imagina.model.wowza.DataCenter;
import com.imagina.model.wowza.WowzaMediaServerPro;
import com.imagina.util.xml.Parser;
import com.imagina.util.xml.jaxb.JAXBParser;

public class RemoteReader implements IReader {

	private static Logger	logger		= LoggerFactory.getLogger(RemoteReader.class);

	private String			username	= "monitor";
	private String			password	= "Mediapro013";
	private String			port		= "8086";
	private String			protocol	= "http";
	private String			request		= "/serverinfo";
	private String			realm		= AuthScope.ANY_REALM;
	private String			scheme		= "digest";

	public WowzaMediaServerPro read(String url) {
		return this.read(this.protocol, url, this.port, this.realm, this.scheme, this.request);
	}

	public WowzaMediaServerPro read(String protocol, String url, String port, String realm, String scheme, String request) {
		WowzaMediaServerPro wowzaMediaServerPro = null;

		HttpHost targetHost = new HttpHost(url, Integer.valueOf(port), protocol);

		CredentialsProvider credsProvider = new BasicCredentialsProvider();

		credsProvider.setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort(), AuthScope.ANY_REALM, "digest"),
				new UsernamePasswordCredentials(username, password));

		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(request);

		try {
			CloseableHttpResponse response = httpclient.execute(targetHost, httpget, context);
			if (logger.isDebugEnabled()) {
				logger.debug("Response Status line :" + response.getStatusLine());
			}

			try {
				HttpEntity entity = response.getEntity();
				Parser<WowzaMediaServerPro> parser = new JAXBParser<WowzaMediaServerPro>();
				wowzaMediaServerPro = parser.unmarshall(WowzaMediaServerPro.class, entity.getContent());
			} finally {
				response.close();
			}

			httpclient.close();

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		return wowzaMediaServerPro;
	}

	public List<WowzaMediaServerPro> getData(DataCenter dataCenter) {
		List<WowzaMediaServerPro> wowzaMediaServerPros = new ArrayList<WowzaMediaServerPro>(0);
		for (int i = 1; i <= RemoteReader.NODES; i++) {
			String url = dataCenter + "-edge0" + i + ".mediapro.es";
			WowzaMediaServerPro wowzaMediaServerPro = this.read(url);
			wowzaMediaServerPro.setName("edge0" + i);
			wowzaMediaServerPros.add(wowzaMediaServerPro);
		}
		return wowzaMediaServerPros;
	}

}
