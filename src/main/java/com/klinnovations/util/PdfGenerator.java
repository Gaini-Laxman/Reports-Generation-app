package com.klinnovations.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.klinnovations.entity.CitizenPlan;
import com.klinnovations.repo.CitizenPlanRepository;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class PdfGenerator {

	@Autowired
	private CitizenPlanRepository planRepo;

	public void generate(HttpServletResponse response, List<CitizenPlan> plans, File f) throws Exception {

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();
		
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);
		Paragraph paragraph = new Paragraph("Citizen Plan", fontTiltle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph);
		
		try {

		PdfPTable table = new PdfPTable(6);
		
		table.setSpacingBefore(5);
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(CMYKColor.MAGENTA);
		cell.setPadding(5);


		table.addCell("ID");
		table.addCell("Citizen Name");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");

		
		for(CitizenPlan plan : plans) {
			
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getPlanStartDate()+"");
			table.addCell(plan.getPlanEndDate()+"");
		}
		
		
		document.add(table);
		document.close();
		}catch (DocumentException e) {
     System.out.println(e);
		}

	}

	
	}


