package in.nareshit.raghu.view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.nareshit.raghu.entity.Order;
import in.nareshit.raghu.entity.OrderItem;

public class CustomerInvoiceView extends AbstractPdfView {

	@Override
	protected void buildPdfMetadata(
			Map<String, Object> model, 
			Document document, HttpServletRequest request)
	{
		HeaderFooter header = new HeaderFooter(new Phrase("INVOICE PDF VIEW"), false);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);

		HeaderFooter footer = new HeaderFooter(new Phrase(new Date()+" (C) Nareshit, Page # "), true);
		footer.setAlignment(Element.ALIGN_RIGHT);
		document.setFooter(footer);
	}
	@Override
	protected void buildPdfDocument(
			Map<String, Object> model, 
			Document document, 
			PdfWriter writer,
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {

		//Download PDF
		//download PDF with a given filename
		response.addHeader("Content-Disposition", "attachment;filename=INVOICE.pdf");

		//Order Object
		Order order = (Order) model.get("order");

		//Title
		Font invoiceFont = new Font(Font.TIMES_ROMAN, 20, Font.BOLD, Color.BLACK);
		Paragraph invoiceId = new Paragraph(order.getId()+":"+System.currentTimeMillis(),invoiceFont);

		invoiceId.setAlignment(Element.ALIGN_CENTER);
		invoiceId.setSpacingBefore(10.0f);
		invoiceId.setSpacingAfter(10.0f);
		//add to document
		document.add(invoiceId);

		//adding Image
		Image img = Image.getInstance("https://www.nareshit.com/wp-content/uploads/2019/06/Nareshit-Logo-Png.png");
		//set width and height
		//img.scaleAbsolute(width, height);
		//set alignment
		img.setAlignment(Element.ALIGN_CENTER);
		document.add(img);
		
		PdfPTable table = new PdfPTable(4);
		table.setSpacingBefore(35.0f);
		table.setSpacingAfter(15.0f);
		
		table.addCell(getDesignCell("Customer Name"));
		table.addCell(getTextCell(order.getCustomer().getName()));
		
		table.addCell(getDesignCell("Order Status"));
		table.addCell(getTextCell(order.getStatus()));
		
		table.addCell(getDesignCell("No.of Items"));
		table.addCell(getTextCell(String.valueOf(order.getOrderItems().size())));
		
		table.addCell(getDesignCell("Totla Amount"));
		table.addCell(getTextCell(order.getGrandTotal().toString()));
		document.add(table);
		
		
		//ITEMS DATA
		PdfPTable itemsData = new PdfPTable(5);
		//itemsData.getDefaultCell().setBorderWidth(0f);
		itemsData.setSpacingBefore(25.0f);
		itemsData.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		itemsData.addCell(getDesignCell("PRODUCT NAME"));
		itemsData.addCell(getDesignCell("COST"));
		itemsData.addCell(getDesignCell("DISCOUNT"));
		itemsData.addCell(getDesignCell("QTY"));
		itemsData.addCell(getDesignCell("TOTAL"));
		
		List<OrderItem>  itemsList = order.getOrderItems(); 
		for(OrderItem item : itemsList) {
			itemsData.addCell(getTextCell(item.getProduct().getName()));
			itemsData.addCell(getTextCell(item.getProduct().getCost().toString()));
			itemsData.addCell(getTextCell(item.getDiscount()!=null?item.getDiscount().toString():"NONE"));
			itemsData.addCell(getTextCell(item.getQty().toString()));
			itemsData.addCell(getTextCell(item.getLineAmount().toString()));
		}
		
		document.add(itemsData);
		
		//NOTE IN BOTTOM
		Font noteFont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD, Color.RED);
		Paragraph note = new Paragraph("NOTE: THIS IS AUTO-GENERATED PAYMENT SLIP, FOR MORE DETALS CONTACT FRONTDESK @ 1234567890",noteFont);
		note.setAlignment(Element.ALIGN_CENTER);
		note.setSpacingBefore(15.0f);
		document.add(note);
		
		//SIGNATURE
		Font signFont = new Font(Font.TIMES_ROMAN, 20, Font.BOLD, Color.BLACK);
		Paragraph sign = new Paragraph("SIGNATURE",signFont);
		sign.setAlignment(Element.ALIGN_RIGHT);
		sign.setSpacingBefore(35.0f);
		sign.setSpacingAfter(10.0f);
		//add to document
		document.add(sign);
		Paragraph datePar = new Paragraph("Date :" + new Date());
		datePar.setAlignment(Element.ALIGN_RIGHT);
		document.add(datePar);
	}
	
	private Phrase getDesignCell(String data) {
		Font font = new Font(Font.TIMES_ROMAN, 14, Font.BOLD, Color.BLUE);
		return new Phrase(data, font);
	}
	private Phrase getTextCell(String data) {
		Font font = new Font(Font.TIMES_ROMAN, 14, Font.BOLD, Color.BLACK);
		return new Phrase(data, font);
	}
}
