package com.hamza.website_analyzer.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;

@Service
public class WebsiteAnalyzerService {

    public Map<String, Object> analyzeWebsite(String url) throws IOException {
        long analysisStart = System.nanoTime();
        Map<String, Object> analysis = new HashMap<>();
        analysis.put("usesHttps", url.startsWith("https://"));

        // HEAD request timing
        long headStart = System.nanoTime();
        HttpURLConnection headConn = (HttpURLConnection) new URL(url).openConnection();
        headConn.setRequestMethod("HEAD");
        headConn.setConnectTimeout(10000);
        headConn.setReadTimeout(10000);
        headConn.setInstanceFollowRedirects(true);
        headConn.connect();
        int headStatus = headConn.getResponseCode();
        long headEnd = System.nanoTime();

        Map<String, Object> httpHeaders = headConn.getHeaderFields()
                .entrySet()
                .stream()
                .filter(e -> e.getKey() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        analysis.put("httpHeaders", httpHeaders);
        analysis.put("headStatusCode", headStatus);

        // TLS certificate info (if HTTPS)
        if (headConn instanceof HttpsURLConnection https) {
            try {
                https.getInputStream(); // trigger handshake
                Certificate[] certs = https.getServerCertificates();
                if (certs != null && certs.length > 0) {
                    Certificate leaf = certs[0];
                    analysis.put("certificateType", leaf.getType());
                    analysis.put("certificateCount", certs.length);
                }
            } catch (Exception ignored) {
                analysis.put("certificateError", true);
            }
        }

        // GET request timing + size
        long getStart = System.nanoTime();
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (WebsiteAnalyzerService)")
                .timeout(10000)
                .get();
        long getEnd = System.nanoTime();

        String html = document.outerHtml();
        analysis.put("htmlBytesApprox", html.getBytes().length);
        analysis.put("title", document.title());
        analysis.put("metaDescription", document.select("meta[name=description]").attr("content"));
        analysis.put("metaKeywords", document.select("meta[name=keywords]").attr("content"));
        analysis.put("hasRobotsTxt", hasResource(url, "/robots.txt"));
        analysis.put("hasSitemapXml", hasResource(url, "/sitemap.xml"));

        analysis.put("contentSecurityPolicy", headConn.getHeaderField("Content-Security-Policy"));
        analysis.put("xFrameOptions", headConn.getHeaderField("X-Frame-Options"));
        analysis.put("server", headConn.getHeaderField("Server"));
        analysis.put("xPoweredBy", headConn.getHeaderField("X-Powered-By"));

        // Timing metrics (ms)
        analysis.put("headResponseTimeMs", toMs(headEnd - headStart));
        analysis.put("getResponseTimeMs", toMs(getEnd - getStart));
        analysis.put("totalAnalysisTimeMs", toMs(System.nanoTime() - analysisStart));

        return analysis;
    }

    private boolean hasResource(String baseUrl, String resourcePath) {
        try {
            HttpURLConnection c = (HttpURLConnection) new URL(baseUrl + resourcePath).openConnection();
            c.setRequestMethod("HEAD");
            c.setConnectTimeout(5000);
            c.setReadTimeout(5000);
            c.connect();
            return c.getResponseCode() == 200;
        } catch (IOException e) {
            return false;
        }
    }

    private double toMs(long nanos) {
        return Math.round((nanos / 1_000_000.0) * 100.0) / 100.0;
    }
}

