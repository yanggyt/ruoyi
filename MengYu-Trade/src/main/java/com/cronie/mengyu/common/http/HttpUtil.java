package com.cronie.mengyu.common.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParser;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageParserFactory;
import org.apache.http.io.HttpMessageWriterFactory;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.LineParser;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;


/**
 * Http。
 * 
 * @author Zhao.Hailin
 *
 */
public class HttpUtil {

	/** 连接池管理 */
	private static volatile PoolingHttpClientConnectionManager connManager = null;

	/** 配置信息 */
	private static volatile Properties properties = null;

	private static final String CLIENT_PROTOCOL_ERR = "客户端协议异常";
	private static final String HTTP_HOST_CONNECT_ERR = "连接请求被拒绝";
	private static final String SOCKET_TIMEOUT_ERR = "连接请求超时";
	private static final String IO_ERR = "通信过程中发生了异常";
	private static final String CLOSE_IO_ERR = "关闭连接时发生了异常";

	// private static final String UNSUPPORTED_ENCODING_ERR = "不支持的编码格式";

	


	/**
	 * HttpClient POST。
	 * 
	 * @param url URL
	 * @param params Map<String, Object>
	 * @return String
	 * @throws Exception 
	 */
	public static String sendPost(String url, Map<String, Object> params) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> pairs = covertParams2NVPS(params);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, "utf-8"));
		} catch (Exception e) {
			throw e;
		}
		return getResult(httpPost, Charset.forName("utf-8"));
	}

	private static List<NameValuePair> covertParams2NVPS(
			Map<String, Object> params) {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			pairs.add(new BasicNameValuePair(param.getKey(),
					nullIsEmptyStr(param.getValue())));
		}
		return pairs;
	}

	/**
	 * application/viid+json, utf-8 编码的 POST 请求，华尊 1400 专用。
	 * 
	 * @param url 请求 URI
	 * @return String JSON格式的字符串
	 * @throws Exception 
	 */
	public static String sendGet(String url) throws Exception {
		// add request header
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("Content-Type", "application/viid+json");
		headers.put("User-Identify", "51340000041205914046");
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headers.put("Connection", "Keep-Alive");
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
		
		return get(url, Charset.forName("utf-8"), headers);
	}



	/**
	 * GET 请求。
	 * 
	 * @param uri 请求 URI。
	 * @param charset 字符集。
	 * @param headers 请求头。
	 * @return String 响应实体消息。
	 * @throws Exception 
	 */
	public static String get(String uri, Charset charset,
			Map<String, Object> headers) throws Exception {
		HttpGet httpGet = new HttpGet(uri);
		
		return getResult(httpGet, charset);
	}

	

//	/**
//	 * 设置请求头。
//	 * 
//	 * @param request HttpGet/HttpPost。
//	 * @param headers 请求头。
//	 */
//	private static void setHeader(HttpRequestBase request,
//			Map<String, Object> headers) {
//		if (headers != null) {
//			for (Map.Entry<String, Object> entry : headers.entrySet()) {
//				request.setHeader(entry.getKey(),
//						nullIsEmptyStr(entry.getValue()));
//			}
//		}
//	}


	/**
	 * 将对象转换成 String 的表现形式。如果该对象是 null，则返回空字符串。
	 * 
	 * @param obj Object。
	 * @return String
	 */
	private static String nullIsEmptyStr(Object obj) {
		return obj == null ? "" : String.valueOf(obj);
	}

	/**
	 * 获取响应实体消息。
	 * 
	 * @param request HttpGet/HttpPost。
	 * @param charset 字符集。
	 * @return String 一个新分配的字符串。
	 * @throws Exception 
	 */
	private static String getResult(HttpRequestBase request, Charset charset) throws Exception {
	

		HttpClientContext context = HttpClientContext.create();
		CloseableHttpResponse response = null;
		try {
			response = getHttpClient().execute(request, context);
			// 获取响应状态码。
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
//					String result = new String(EntityUtils.toByteArray(entity),
//							charset);
					String result = EntityUtils.toString(entity,charset) ;
//					String result = new String(EntityUtils.toByteArray(entity));
				
					return result;
				}
			}
		} catch (IOException e) {
			if (e instanceof ClientProtocolException)
				throw new Exception(CLIENT_PROTOCOL_ERR);
			if (e instanceof HttpHostConnectException)
				throw new Exception(HTTP_HOST_CONNECT_ERR);
			else if (e instanceof SocketTimeoutException)
				throw new Exception(SOCKET_TIMEOUT_ERR);
			else
				throw new Exception(IO_ERR);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					throw new Exception(CLOSE_IO_ERR);
				}
			}
		}
		return null;
	}

	/**
	 * 从连接池中获取 HttpClient。
	 * 
	 * @return CloseableHttpClient。
	 */
	private static CloseableHttpClient getHttpClient() {
		defaultConfigure();

		// 默认的请求配置。
		RequestConfig defaultRequestConfig = RequestConfig
				.custom()
				.setConnectTimeout((int) properties.get("connectTimeout"))
				.setSocketTimeout((int) properties.get("socketTimeout"))
				.setConnectionRequestTimeout(
						(int) properties.get("connectionRequestTimeout"))
				.build();

		// 使用给定的自定义依赖项和配置创建 HttpClient。
		CloseableHttpClient httpClient = HttpClients
				.custom()
				.setConnectionManager(getConnManager())
				.setConnectionManagerShared(false)
				.evictIdleConnections((int) properties.get("maxIdleTime"),
						TimeUnit.SECONDS)
				.evictExpiredConnections()
				.setConnectionTimeToLive(
						(int) properties.get("connTimeToLive"),
						TimeUnit.SECONDS)
				.setDefaultRequestConfig(defaultRequestConfig)
				.setConnectionReuseStrategy(
						DefaultConnectionReuseStrategy.INSTANCE)
				.setKeepAliveStrategy(
						DefaultConnectionKeepAliveStrategy.INSTANCE)
				.setRetryHandler(
						new DefaultHttpRequestRetryHandler((int) properties
								.get("retryCount"), false)).build();

		return httpClient;
	}

	/**
	 * 配置连接池。
	 * 
	 * @return PoolingHttpClientConnectionManager。
	 */
	private static PoolingHttpClientConnectionManager getConnManager() {
		if (connManager == null) {
			synchronized (HttpUtil.class) {
				if (connManager == null) {
					HttpMessageParserFactory<HttpResponse> responseParserFactory = new DefaultHttpResponseParserFactory() {
						@Override
						public HttpMessageParser<HttpResponse> create(
								final SessionInputBuffer buffer,
								final MessageConstraints constraints) {
							LineParser lineParser = new BasicLineParser() {
								@Override
								public Header parseHeader(
										final CharArrayBuffer buffer)
										throws ParseException {
									return super.parseHeader(buffer);
								}
							};
							return new DefaultHttpResponseParser(buffer,
									lineParser,
									DefaultHttpResponseFactory.INSTANCE,
									constraints) {
								@Override
								protected boolean reject(
										final CharArrayBuffer line,
										final int count) {
									// 无限地忽略状态行前面的所有垃圾。
									return false;
								}
							};
						}
					};
					HttpMessageWriterFactory<HttpRequest> requestWriterFactory = new DefaultHttpRequestWriterFactory();

					// 定制初始化传出 HTTP 连接的过程。
					HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory = new ManagedHttpClientConnectionFactory(
							requestWriterFactory, responseParserFactory);

					// 使用自定义 DNS 解析器覆盖系统 DNS 解析。
					DnsResolver dnsResolver = new SystemDefaultDnsResolver() {
						@Override
						public InetAddress[] resolve(final String host)
								throws UnknownHostException {
							return super.resolve(host);
						}
					};

					// 创建自定义连接套接字工厂的注册表。
					Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
							.<ConnectionSocketFactory> create()
							.register("http",
									PlainConnectionSocketFactory.INSTANCE)
							.register(
									"https",
									new SSLConnectionSocketFactory(SSLContexts
											.createSystemDefault())).build();

					// 使用自定义配置创建连接管理器。
					connManager = new PoolingHttpClientConnectionManager(
							socketFactoryRegistry, connFactory, dnsResolver);

					// 套接字配置
					SocketConfig socketConfig = SocketConfig.custom()
							.setTcpNoDelay(true).build();
					connManager.setDefaultSocketConfig(socketConfig);

					connManager.setValidateAfterInactivity((int) properties
							.get("validateAfterInactivity"));
					connManager.setMaxTotal((int) properties.get("maxTotal"));
					connManager.setDefaultMaxPerRoute((int) properties
							.get("defaultMaxPerRoute"));

					Runtime.getRuntime().addShutdownHook(new Thread() {
						@Override
						public void run() {
							if (connManager != null) {
								connManager.close();
							}
						}
					});
				}
			}
		}
		return connManager;
	}

	/**
	 * 加载默认配置。
	 */
	private static void defaultConfigure() {
		if (properties == null) {
			synchronized (HttpUtil.class) {
				if (properties == null) {
					properties = new Properties();
					properties.put("validateAfterInactivity", 5000);

					// 最大连接数。
					properties.put("maxTotal", 25);
					// 每个路由的最大连接数。
					properties.put("defaultMaxPerRoute", 5);

					// 连接超时时间（单位：毫秒）。
					properties.put("connectTimeout", 3000);
					// 等待数据超时时间（单位：毫秒）。
					properties.put("socketTimeout", 1000 * 60 * 3);
					// 从连接池获取连接的等待超时时间（单位：毫秒）。
					properties.put("connectionRequestTimeout", 1000 * 60);

					properties.put("maxIdleTime", 60);
					properties.put("connTimeToLive", 60);
					properties.put("retryCount", 0);
				}
			}
		}
	}

}
