package in.nareshit.raghu.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import in.nareshit.raghu.entity.Category;

public class categoriesExportExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Content-Disposition", "attachment;filename=Categories.xlsx");

		@SuppressWarnings("unchecked")
		List<Category> list = (List<Category>) model.get("list");

		Sheet sheet = workbook.createSheet("CATEGORY DATA");

		createHeader(sheet);
		createBody(sheet, list);
	}

	private void createHeader(Sheet sheet) {
		int rowNum = 0;
		Row row = sheet.createRow(rowNum);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("ALIAS");
		row.createCell(3).setCellValue("STATUS");
		row.createCell(4).setCellValue("NOTE");
		row.createCell(5).setCellValue("TYPE");
	}

	private void createBody(Sheet sheet, List<Category> list) {
		int rowNum = 1;
		for(Category c:list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(c.getId());
			row.createCell(1).setCellValue(c.getName());
			row.createCell(2).setCellValue(c.getAlias());
			row.createCell(3).setCellValue(c.getStatus());
			row.createCell(4).setCellValue(c.getNote());
			row.createCell(5).setCellValue(c.getCategoryType().getName());
		}
		
	}
}	
