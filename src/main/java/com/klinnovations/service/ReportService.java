package com.klinnovations.service;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.klinnovations.entity.CitizenPlan;
import com.klinnovations.request.SearchRequest;

public interface ReportService {

	// abstract Method
	public List<String> getPlanNames();

	public List<String> getPlanStatuses();

	public List<CitizenPlan> search(SearchRequest request);

	public boolean exportExcel(HttpServletResponse response) throws Exception;

	public boolean exportPdf(HttpServletResponse response) throws Exception;

}