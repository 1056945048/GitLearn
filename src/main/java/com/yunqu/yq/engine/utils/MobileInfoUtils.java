package com.yunqu.yq.engine.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class MobileInfoUtils {
	private static Random random = new Random();
	private static HashMap<String,Integer> pool = new HashMap<>();
	private static String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
			"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
			"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
			"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
			"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
			"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
			"Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0",
			"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36 QIHU 360SE"};
	private static AtomicInteger SUCCESS = new AtomicInteger();
	private static AtomicInteger FAIL = new AtomicInteger();
	public static JSONObject so(String mobile){
	    	JSONObject jsonObject=new JSONObject();
	    	String GET_IP_URL = "http://47.106.160.121/Index-generate_api_url.html?packid=1&fa=0&qty=1&port=1&format=json&ss=5&css=&ipport=1&pro=&city=&usertype=13";
		    Document document = null;
		    try {
			document = Jsoup.connect(GET_IP_URL).get();
		    } catch (IOException e) {
		    	e.printStackTrace();
			}
		    System.out.println(document.text());
		    JSONObject json = JSONObject.parseObject(document.text());
		    List<Map<String,Object>> list = (List<Map<String,Object>>) json.get("data");
		    String ip = "";
		    Integer port = null;
		    for(Map<String,Object> map :list){
		    	ip = (String)map.get("ip");
		    	port = (Integer) map.get("port");
		    	if(!"".equals(ip)&&port!=null) break;
			}
			jsonObject.put("count",0);//多少人标识
		     log.info("搜索成功的次数:"+SUCCESS);
		     log.info("搜索失败的次数:"+FAIL);
		     log.info("搜索IP:"+ip+"搜索PORT:"+port);
			try {
				Connection con = Jsoup.connect("http://180.163.251.85/s?src=srp&fr=none&psid=088e0e16599641fc38a6717a3cc143e2&q="+mobile);
				con.header("Host", "https://www.so.com");
				con.proxy(ip,port);
				con.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
				con.header("Content-Type", "application/x-www-form-urlencoded");
//				con.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
				con.header("User-Agent", ua[random.nextInt(15)]);
				Document doc=con.get();
				String count=doc.select(".mohe-tips b").text();
				log.info("[360搜索数据成功]-->mobile:"+mobile+",html:"+doc.html());
				if(StringUtils.isNotBlank(count)){
					SUCCESS.incrementAndGet();
					jsonObject.put("count",count);//多少人标识
					jsonObject.put("address",doc.select(".mohe-mobileInfoContent .mh-detail span").last().text());//地址
					jsonObject.put("type",doc.select(".mohe-tips .mohe-ph-mark").text());//标识类别
					jsonObject.put("source",doc.select(".mohe-tips .mohe-sjws").text());//来源
					jsonObject.put("date",DateUtils.getCurrentDateStr(DateUtils.TIME_FORMAT_YMD));
					return jsonObject;
				}else{
					FAIL.incrementAndGet();
					return null;
				}
			} catch (Exception e) {
				FAIL.incrementAndGet();
				log.error("[360搜索数据失败]-->mobile:"+mobile+",error:"+e.getMessage());
				return null;
			}
	}
	public static JSONObject baidu(String mobile){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("count",0);//多少人标识
			try {
				String url = "http://180.97.33.107/s?wd="+mobile;
//				Connection con = Jsoup.connect(Constants.BAIDU_SGIN_URL+"/s?wd="+mobile);
				Connection con = Jsoup.connect(url);
				con.header("Host", "www.baidu.com");
				con.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
				con.header("Content-Type", "application/x-www-form-urlencoded");
//				con.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
				con.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36 QIHU 360SE");
				Document doc=con.get();
				log.info("[baidu搜索数据成功]-->mobile:"+mobile+",html:"+doc.html());
				String source=doc.select(".op_fraudphone_word a").text();
				if(StringUtils.isNotBlank(source)){
					jsonObject.put("source",doc.select(".op_fraudphone_word a").text());//来源
					String desc=doc.select(".op_fraudphone_word").text();
					if(StringUtils.isNotBlank(desc)){
						String count=desc.substring(desc.indexOf("被")+1,desc.indexOf("个"));
						if(StringUtils.isNotBlank(count)){
							jsonObject.put("count",count);//多少人标识
							jsonObject.put("address",doc.select(".op_fraudphone_addr").text());//地址

							String type=doc.select(".op_fraudphone_word strong").text();
							if(StringUtils.isBlank(type)){
								type=doc.select(".op_fraudphone_label").text();
							}
							if(StringUtils.isNotBlank(type)){
								type = type.replaceAll("\"", "");
							}
							jsonObject.put("type",type);//标识类别
							jsonObject.put("date",DateUtils.getCurrentDateStr(DateUtils.TIME_FORMAT_YMD));
							return jsonObject;
						}else{
							return null;
						}
					}else{
						return null;
					}
				}else{
					return null;
				}
			} catch (Exception e) {
				log.error("[baidu搜索数据失败]-->mobile:"+mobile+",error:"+e.getMessage());
				return null;
			}
	}
	public static void main(String[] args) throws IOException {
		// String mobile="073184145266";
		// String mobile="03752910086";
//		 String mobile="15812495560";
		 String mobile="02066327264";
		System.out.println(baidu(mobile));

	}

}
