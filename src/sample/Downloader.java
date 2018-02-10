package sample;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by jod_h on 10.02.2018.
 */
public class Downloader {


    public static void main(String[] args) {
    }
    public static void downloadClient(){
        try {
            String filename = "minecraft.zip";
            URL url = new URL("http://justgg.ru/download/minecraft1.zip");
            String filepath = "C:\\Users\\jod_h\\Desktop\\minecraft\\";
            String fileName = filepath + filename;
            getClient(url, fileName);
            unzip(fileName,filepath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void getClient(URL url, String file) throws IOException{
        InputStream in = url.openStream();
        FileOutputStream fos = new FileOutputStream(new File(file));

        int length = -1;
        byte[] buffer = new byte[1024];
        while ((length = in.read(buffer)) > -1) {
            fos.write(buffer, 0, length);
        }
        fos.close();
        in.close();
        System.out.println("Скачано");
    }
    public static void unzip(String zipFile, String outputFolder){

        byte[] buffer = new byte[1024];

        try{

            //создание папки, если её нет
            File folder = new File(outputFolder);
                if(!folder.exists()){
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while(ze!=null){
                if (ze.isDirectory()) {

                    ze = zis.getNextEntry();

                    continue;

                }

                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);

                System.out.println("file unzip : "+ newFile.getAbsoluteFile());
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("Done");

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
