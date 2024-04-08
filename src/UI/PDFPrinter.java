package UI;

import java.io.FileOutputStream;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFPrinter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get input from user
        System.out.println("Enter the content for PDF:");
        String content = scanner.nextLine();

        // Generate PDF file
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
            document.open();
            document.add(new Paragraph(content));
            document.close();
            System.out.println("PDF created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Print the generated PDF file
        printPDF("output.pdf");
    }

    public static void printPDF(String filename) {
    	  try {
    	    // Open the PDF with the default PDF viewer
    	    Runtime.getRuntime().exec("explorer.exe " + filename);
    	    // This might prompt a print dialog within the viewer.
    	  } catch (Exception e) {
    	    e.printStackTrace();
    	  }
    	}

}
