package com.klinnovations.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.klinnovations.entity.CitizenPlan;
import com.klinnovations.repo.CitizenPlanRepository;
import com.klinnovations.request.SearchRequest;
import com.klinnovations.util.EmailUtils;
import com.klinnovations.util.ExcelGenerator;
import com.klinnovations.util.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepo;

	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdflGenerator;

	@Autowired
	private EmailUtils emailUtils;

	public List<String> getPlanNames() {

		return planRepo.getPlanNames();

	}

	@Override
	public List<String> getPlanStatuses() {

		return planRepo.getPlanStatus();

	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {

		CitizenPlan entity = new CitizenPlan();

		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}

		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}

		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}

		if (null != request.getStartDate() && !"".equals(request.getStartDate())) {

			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(startDate, formatter);

			entity.setPlanStartDate(localDate);
		}

		return planRepo.findAll(Example.of(entity));

	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {

		File f = new File("Plans.xls");
		
		List<CitizenPlan> plans = planRepo.findAll();
		
		excelGenerator.generate(response, plans, f);
		String subject = "Test Mail Subject";
		String body = "<h1>Test Mail Body</h1>";
		String to = "gainilaxman12@gmail.com";
		f.delete();
		emailUtils.sendEmail(subject, body, to, f);

		return true;
	}

	public boolean exportPdf(HttpServletResponse response) throws Exception {

		
		File f = new File("D:\\Softwares\\STS Tool\\sts_workspace\\Insurance-Reports-Genaration-App/Plans.pdf");
		List<CitizenPlan> plans = planRepo.findAll();
		
		pdflGenerator.generate(response, plans, f);
		String subject = "Test Mail Subject";
		String body = "<h1>Test Mail Body</h1>";
		String to = "gainilaxman12@gmail.com";
		//f.delete();
		emailUtils.sendEmail(subject, body, to, f);

		

		return true;
	}

}