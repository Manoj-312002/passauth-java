package com.auth;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
  
public class OCRImplementation {
    public void convertImage(){
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setLanguage("eng");
            tesseract.setDatapath("/mnt/file/Programming/passauth/");
            tesseract.setTessVariable("user_defined_dpi", "96");
            // the path of your tess data folder
            // inside the extracted file
            String text = tesseract.doOCR(new File("Text7.png"));
  
            // path of your image file
            System.out.print(text);
        }
        catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}
