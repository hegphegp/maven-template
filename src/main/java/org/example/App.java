package org.example;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Properties;

public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        DataSourceUtils.test();
        Map<String, String> envs = System.getenv();
        for (String key:envs.keySet()) {
            System.out.println(key + "   =====>>>>>  " + envs.get(key));
        }

        System.out.println("\n\n\n\n\n");

        Properties properties = System.getProperties();
        for (Object key:properties.keySet()) {
            System.out.println(key + "   =====>>>>>  " + properties.getProperty((String) key));
        }
        logger.info("000000000000000000000000000000000000000000000000000000000000000ccccccccccccccccccccccccc");
        logger.info("000000000000000000000000000000000000000000000000000000000000000ccccccccccccccccccccccccc");
        logger.info("000000000000000000000000000000000000000000000000000000000000000ccccccccccccccccccccccccc");
        logger.info("000000000000000000000000000000000000000000000000000000000000000ccccccccccccccccccccccccc");
        logger.info("000000000000000000000000000000000000000000000000000000000000000ccccccccccccccccccccccccc");
        logger.info("000000000000000000000000000000000000000000000000000000000000000ccccccccccccccccccccccccc");
        logger.info("000000000000000000000000000000000000000000000000000000000000000ccccccccccccccccccccccccc");
        logger.info("000000000000000000000000000000000000000000000000000000000000000ccccccccccccccccccccccccc");

//        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
//                SSLContext.getDefault(),
//                new String[] {"SSLv2Hello","SSLv3","TLSv1","TLSv1.1","TLSv1.2"},
//                null,
//                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
//        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", PlainConnectionSocketFactory.getSocketFactory())
//                .register("https", socketFactory)
//                .build();
//
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//
//        CloseableHttpClient httpClient = HttpClients.custom().build();
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectionRequestTimeout(3000)
//                .setSocketTimeout(3000) // 服务端相应超时
//                .setConnectTimeout(3000) // 建立socket链接超时时间
//                .build();
//
//        String proxyHost = "localhost";
//        String proxyPort = "11082";
////        System.setProperty("http.proxyHost", proxyHost);
////        System.setProperty("http.proxyPort", proxyPort);
////        // HTTPS 代理，只能代理 HTTPS 请求
////        System.setProperty("https.proxyHost", proxyHost);
////        System.setProperty("https.proxyPort", proxyPort);
//
//        // SOCKS 代理，支持 HTTP 和 HTTPS 请求
//        // 注意：如果设置了 SOCKS 代理就不要设 HTTP/HTTPS 代理
//        // 系统默认先使用 HTTP/HTTPS 代理，如果既设置了 HTTP/HTTPS 代理，又设置了 SOCKS 代理，SOCKS 代理会起不到作用
//        System.setProperty("socksProxyHost", proxyHost);
//        System.setProperty("socksProxyPort", proxyPort);
//        System.setProperty("proxySet", "true");
//
//        for (int i = 0; i < 5; i++) {
//            Thread.sleep(100);
//            HttpGet httpGet = new HttpGet("https://www.huobi.com/zh-cn/#exchange");
//            httpGet.setConfig(requestConfig);
//            CloseableHttpResponse response = null;
//            try {
//                response = httpClient.execute(httpGet);
//                HttpEntity responseEntity = response.getEntity();
//                if (responseEntity != null) {
//                    System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//                }
//            } catch (ClientProtocolException e) {
//                e.printStackTrace();
//            } catch (ParseException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (response != null) {
//                        response.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

}
