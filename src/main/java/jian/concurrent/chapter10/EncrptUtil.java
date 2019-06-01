package jian.concurrent.chapter10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncrptUtil {


    public static void encrpt(File src, File dest) {
        FileInputStream fis = null;
        FileOutputStream fos = null;


        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);

            int temp = -1;
            while ((temp = fis.read()) != -1) {
                fos.write(temp ^ 0xFF);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
