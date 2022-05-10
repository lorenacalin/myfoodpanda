package ro.sd.springdemo.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import ro.sd.springdemo.model.Food;
import ro.sd.springdemo.model.Restaurant;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Service class that helps to create a PDF
 */
@Slf4j
public class PDFService {
    private final String title = "%s.pdf ";

    public void export(Restaurant restaurant) {
        Document document = new Document();
        PdfPTable table = new PdfPTable(restaurant.getFoods().size());

        try {
            PdfWriter.getInstance(document, new FileOutputStream(String.format(title, restaurant.getName())));
        } catch (DocumentException e) {
            log.error("PDF Service: cannot open file");
        } catch (FileNotFoundException e) {
            log.error("PDF Service: cannot find file");
        }

        document.open();
        addHeader(table);
        addRows(table, restaurant.getFoods());

        try {
            document.add(table);
        } catch (DocumentException e) {
            log.error("PDF Service: cannot edit file");
        }
        document.close();
        log.info("PDF Service: pdf file created successfully");
    }

    private void addHeader(PdfPTable table) {
        Stream.of("Name", "Description", "Grams", "Price", "Category")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, Set<Food> foodItemList) {
        foodItemList.stream().forEach(f -> {
            table.addCell(f.getName());
            table.addCell(f.getDescription());
            table.addCell(Double.toString(f.getGrams()));
            table.addCell(Double.toString(f.getPrice()));
            table.addCell(f.getCategory().toString());
        });
    }
}
