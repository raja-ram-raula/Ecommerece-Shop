package in.nareshit.raghu.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import in.nareshit.raghu.entity.CategoryType;

public class CategoryTypesExportExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.addHeader("Content-Disposition", "attachment;filename=CategoryTypes.xlsx");

		@SuppressWarnings("unchecked")
		List<CategoryType> list = (List<CategoryType>) model.get("list");

		Sheet sheet = workbook.createSheet("CATEGORY TYPE DATA");

		createHeader(sheet);
		createBody(sheet, list);
	}

	private void createHeader(Sheet sheet) {
		int rowNum = 0;
		Row row = sheet.createRow(rowNum);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("NOTE");
	}

	private void createBody(Sheet sheet, List<CategoryType> list) {
		int rowNum = 1;
		for(CategoryType ct:list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(ct.getId());
			row.createCell(1).setCellValue(ct.getName());
			row.createCell(2).setCellValue(ct.getNote());
		}
		
	}
}	
