package com.auth;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
  
public class OCRImplementation {
    public String convertImage(){
        Tesseract tesseract = new Tesseract();
        String text = "";
        try {
            tesseract.setLanguage("eng");
            tesseract.setDatapath("/mnt/file/Programming/passauth/");
            tesseract.setTessVariable("user_defined_dpi", "96");
            // the path of your tess data folder
            // inside the extracted file
            text = tesseract.doOCR(new File("Text7.png"));
  
            // path of your image file
            //* - Printing the ctext extracted from OCR
            System.out.println("OCR CTEXT");
            System.out.print(text);
        }
        catch (TesseractException e) {
            e.printStackTrace();
        }

        return text;
    }
}
