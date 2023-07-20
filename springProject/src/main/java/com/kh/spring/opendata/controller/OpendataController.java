package com.kh.spring.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.spring.opendata.model.vo.AirVo;

@Controller
@RequestMapping("/opendata")
public class OpendataController {
	public static final String SERVICE_KEY = "FYVberixT%2Bp4Pyj4hiRJekzj%2BGpl%2BB%2BVj3Uvtjp992Altno9Qug3FWKNfFwgxo3hTiNpDBOQ6ERzVvufWkL0pA%3D%3D";
	
	@GetMapping("opendataList.do")
	public void opendataList() {}
	
	
	// json 타입
	@ResponseBody	// 보낼때 body에 담아서 보냄
	@GetMapping(value="air.do", produces="application/json; charset=utf-8")
	public String airPollution(String location) throws IOException {
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey=" + SERVICE_KEY;
		url += "&sidoName=" + URLEncoder.encode(location, "UTF-8");
		url += "&returnType=json";
		//url += "&numOfRows=20";
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		while((line = br.readLine()) != null) {
			responseText += line;
		}

		// DB에 저장시
		JsonObject totalobj = JsonParser.parseString(responseText).getAsJsonObject();
		JsonObject responseObj = totalobj.getAsJsonObject("response");  // response => {} JsonObject
		JsonObject bodyObj = responseObj.getAsJsonObject("body");	// body => {} JsonObject
		
		int totalCount = bodyObj.get("totalCount").getAsInt();  // totalCount 속성 접근(int형으로 얻어옴)
		JsonArray itemArr = bodyObj.getAsJsonArray("items");  // items => [] JsonArray
		
		List<AirVo> list = new ArrayList<>();
		
		for(int i=0; i<itemArr.size(); i++) {
			JsonObject item = itemArr.get(i).getAsJsonObject();
			
			/*
			// AirVo의 bean파일과 sevice interface, Impl, dao, xml 을 만들어 넣어주면 됨
			AirVo air = new AirVo();
			air.setStationName(item.get("stationName").getAsString());
			...
			
			list.add(air);
			int result = opendataService.insertAir(air);
			*/
		}
		
		br.close();
		urlConnection.disconnect();
		
		return responseText;
	}
	
	/*
	// xml 타입
	@ResponseBody
	@GetMapping(value="air.do", produces="text/xml; charset=utf-8")	// xml 형식
	public String airPollution(String location) throws IOException {
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey=" + SERVICE_KEY;
		url += "&sidoName=" + URLEncoder.encode(location, "UTF-8");
		url += "&returnType=xml";

		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		return responseText;
	}
	*/
	
	@ResponseBody
	@GetMapping(value="disaster.do", produces="text/xml; charset=utf-8")	
	public String disasterShelter() throws IOException {
		
		String url = "http://apis.data.go.kr/1741000/TsunamiShelter3/getTsunamiShelter1List";
		url += "?serviceKey=" + SERVICE_KEY;
		url += "&numOfRows=20";
		url += "&type=xml";
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		return responseText;
	}
}
