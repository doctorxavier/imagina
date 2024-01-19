package test.imagina;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.junit.Test;

import com.imagina.model.wowza.Application;
import com.imagina.model.wowza.ApplicationInstance;
import com.imagina.model.wowza.Client;
import com.imagina.model.wowza.HTTPSession;
import com.imagina.model.wowza.VHost;
import com.imagina.model.wowza.WowzaMediaServerPro;
import com.imagina.util.test.AbstractTest;
import com.imagina.util.xml.Parser;
import com.imagina.util.xml.jibx.JiBXParser;

public class TestContext extends AbstractTest {

	private static Logger	logger		= LoggerFactory.getLogger(TestContext.class);

	private List<String> dataCenters = new ArrayList<String>(2) { {
		   add("cdnvir");
		   add("cdn22a");
		} };

	public void connect(String url) {
		try {
			String username = "monitor";
			String password = "Mediapro013";

			HttpHost targetHost = new HttpHost(url, Integer.valueOf("8086"), "http");

			CredentialsProvider credsProvider = new BasicCredentialsProvider();

			credsProvider.setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort(), AuthScope.ANY_REALM, "digest"),
					new UsernamePasswordCredentials(username, password));

			HttpClientContext context = HttpClientContext.create();
			context.setCredentialsProvider(credsProvider);

			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpGet httpget = new HttpGet("/serverinfo");

			CloseableHttpResponse response = httpclient.execute(targetHost, httpget, context);
			logger.debug("Response Status line :" + response.getStatusLine());
			try {
				HttpEntity entity = response.getEntity();

				IBindingFactory bfact = BindingDirectory.getFactory(WowzaMediaServerPro.class);
				IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
				Object obj = uctx.unmarshalDocument(entity.getContent(), null);
				WowzaMediaServerPro wowzaMediaServerPro = (WowzaMediaServerPro) obj;

				ByteArrayOutputStream bufferOut = new ByteArrayOutputStream();
				IMarshallingContext mctx = bfact.createMarshallingContext();
				mctx.setOutput(bufferOut, null);
				mctx.marshalDocument(wowzaMediaServerPro);

				logger.info(bufferOut.toString());
			} finally {
				response.close();
			}

			httpclient.close();

		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} catch (JiBXException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	// @Test
	public void testServers() {
		int counter = Integer.valueOf("8").intValue();
		for (int i = 1; i <= counter; i++) {
			this.connect("cdn22a-edge0" + i + ".mediapro.es");
		}
		for (int i = 1; i <= counter; i++) {
			this.connect("cdnvir-edge0" + i + ".mediapro.es");
		}
	}

	// @Test
	public void testUnMarshall() {
		try {
			JiBXParser<WowzaMediaServerPro> parser = new JiBXParser<WowzaMediaServerPro>();
			WowzaMediaServerPro wowzaMediaServerPro = parser.unmarshall(WowzaMediaServerPro.class, new FileInputStream("serverinfo-cdnvir-edge01.xml"));

			logger.info(parser.toString(wowzaMediaServerPro));

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	// @Test
	public void testAllUnMarshall() {
		try {
			JiBXParser<WowzaMediaServerPro> parser = new JiBXParser<WowzaMediaServerPro>();

			int counter = Integer.valueOf("8").intValue();

			for (String dataCenter : dataCenters) {
				for (int i = 1; i <= counter; i++) {
					WowzaMediaServerPro wowzaMediaServerPro = parser.unmarshall(WowzaMediaServerPro.class, new FileInputStream("serverinfo-" + dataCenter
							+ "-edge0" + i + ".xml"));
					logger.info(parser.toString(wowzaMediaServerPro));
				}
			}

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	@Test
	public void test() {

		WowzaMediaServerPro wowzaMediaServerPro = new WowzaMediaServerPro();
		VHost vHost = new VHost();

		Application application = new Application();

		ApplicationInstance applicationInstance = new ApplicationInstance();

		Client client = new Client();
		client.setClientId(1);
		client.setDateStarted("dateStarted");
		client.setFlashVersion("flashVersion");
		client.setIoBytesReceived(1L);
		client.setIoBytesSent(1L);
		client.setIoSessionBytesReceived(1L);
		client.setIoBytesSent(1L);
		client.setIoSessionLastIo(1L);
		client.setIpAddress("ipAddress");
		client.setIsEncrypted(false);
		client.setIsSSL(false);
		client.setLastValidateTime(1L);
		client.setPort(Integer.valueOf("1111").intValue());
		client.setProtocol("protocol");
		client.setQueryString("queryString");
		client.setReferrer("referrer");
		client.setTimeRunning(1L);
		client.setURI("uRI");

		List<Client> clients = new ArrayList<Client>(0);
		clients.add(client);

		applicationInstance.setClients(clients);
		applicationInstance.setConnectionsCurrent(1L);
		applicationInstance.setConnectionsTotal(1L);
		applicationInstance.setConnectionsTotalAccepted(1L);
		applicationInstance.setConnectionsTotalRejected(1L);
		applicationInstance.setCupertinoConnectionCount(1L);

		HTTPSession hTTPSession = new HTTPSession();
		hTTPSession.setDateStarted("dateStarted");
		hTTPSession.setIoBytesReceived(1L);
		hTTPSession.setIoBytesSent(1L);
		hTTPSession.setIoLastRequest(1L);
		hTTPSession.setIpAddress("ipAddress");
		hTTPSession.setPort(Integer.valueOf("1111").intValue());
		hTTPSession.setProtocol("protocol");
		hTTPSession.setQueryString("queryString");
		hTTPSession.setReferrer("referrer");
		hTTPSession.setSessionId("sessionId");
		hTTPSession.setSessionType("sessionType");
		hTTPSession.setTimeRunning(1D);
		hTTPSession.setURI("uRI");

		List<HTTPSession> hTTPSessions = new ArrayList<HTTPSession>(0);
		hTTPSessions.add(hTTPSession);

		applicationInstance.setHTTPSessions(hTTPSessions);

		HTTPSession rTPSession = new HTTPSession();
		rTPSession.setDateStarted("dateStarted");
		rTPSession.setIoBytesReceived(1L);
		rTPSession.setIoBytesSent(1L);
		rTPSession.setIoLastRequest(1L);
		rTPSession.setIpAddress("ipAddress");
		rTPSession.setPort(Integer.valueOf("1111").intValue());
		rTPSession.setProtocol("protocol");
		rTPSession.setQueryString("queryString");
		rTPSession.setReferrer("referrer");
		rTPSession.setSessionId("sessionId");
		rTPSession.setSessionType("sessionType");
		rTPSession.setTimeRunning(1D);
		rTPSession.setURI("uRI");

		List<HTTPSession> rTPSessions = new ArrayList<HTTPSession>(0);
		rTPSessions.add(rTPSession);

		applicationInstance.setRTPSessions(rTPSessions);

		applicationInstance.setHTTPSessionCount(1);
		applicationInstance.setName("name");
		applicationInstance.setRTMPConnectionCount(1L);
		applicationInstance.setRTMPSessionCount(1);
		applicationInstance.setRTPConnectionCount(1L);
		applicationInstance.setRTPSessionCount(1);
		applicationInstance.setSanJoseConnectionCount(1L);
		applicationInstance.setSmoothConnectionCount(1L);
		applicationInstance.setTimeRunning(1D);

		List<ApplicationInstance> applicationInstances = new ArrayList<ApplicationInstance>(0);
		applicationInstances.add(applicationInstance);

		application.setApplicationInstances(applicationInstances);
		application.setConnectionsCurrent(1L);
		application.setConnectionsTotal(1L);
		application.setConnectionsTotalAccepted(1L);
		application.setConnectionsTotalRejected(1L);
		application.setName("name");
		application.setStatus("status");
		application.setTimeRunning(1D);
		List<Application> applications = new ArrayList<Application>(0);
		applications.add(application);
		vHost.setApplications(applications);

		vHost.setConnectionsCurrent(1L);
		vHost.setConnectionsLimit(1);
		vHost.setConnectionsTotal(1L);
		vHost.setConnectionsTotalAccepted(1L);
		vHost.setConnectionsTotalRejected(1L);
		vHost.setName("name");
		vHost.setTimeRunning(1D);

		List<VHost> vHosts = new ArrayList<VHost>(0);
		vHosts.add(vHost);

		wowzaMediaServerPro.setVHosts(vHosts);

		Parser<WowzaMediaServerPro> parser = new JiBXParser<WowzaMediaServerPro>();

		ByteArrayOutputStream bufferOut = new ByteArrayOutputStream();
		parser.marshall(wowzaMediaServerPro, bufferOut);

		logger.info(bufferOut.toString());

	}

}
