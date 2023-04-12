package com.klinnovations.request;

import lombok.Data;

@Data
public class SearchRequest {
	private String planName;
	private String planStatus;
	private String gender;
	private String startDate;
	private String endDate;

}
