package in.nareshit.raghu.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import in.nareshit.raghu.entity.Brand;

public class BrandsExportExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {

		response.addHeader("Content-Disposition", "attachment;filename=BRANDS.xlsx");
		
		@SuppressWarnings("unchecked")
		List<Brand> list = (List<Brand>) model.get("list");
		
		Sheet sheet = workbook.createSheet("BRANDS DATA");
		
		createHeader(sheet);
		createBody(sheet,list);
	}


	private void createHeader(Sheet sheet) {
		int rowNum = 0;
		Row row = sheet.createRow(rowNum);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("TAG LINE");
		row.createCell(4).setCellValue("NOTE");
	}
	

	private void createBody(Sheet sheet, List<Brand> list) {
		int rowNum = 1;
		for(Brand brand:list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(brand.getId());
			row.createCell(1).setCellValue(brand.getName());
			row.createCell(2).setCellValue(brand.getCode());
			row.createCell(3).setCellValue(brand.getTagLine());
			row.createCell(4).setCellValue(brand.getNote());
		}
		
	}

}
