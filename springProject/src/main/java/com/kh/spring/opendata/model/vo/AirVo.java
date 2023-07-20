package com.kh.spring.opendata.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AirVo {
	// 숫자로 되어있다고 int로 받으면 null값이 들어왔을때 오류가 나기 때문에, count같이 값이 없을때 0을 확실하게 반환하는 컬럼을 제외하고는 String을 쓰는게 좋음
	private String stationName;
	private String dataTime;
	private String khaiValue;
	private String pm10Value;
	private String coValue;
	private String no2Value;
	private String so2Value;
	private String o3Value;
}
