package sample;

import java.io.*;

public class Downloader {
    public static void main(String[] args){}
    public static void download()
    {
        File srcFolder = new File("C:\\FileTest");
        File destFolder = new File("C:\\Users\\jod_h\\Desktop");

        //make sure source exists
        if(!srcFolder.exists()){
            System.out.println("cant get");
            System.exit(0);

        }else{

            try{
                getClient(srcFolder,destFolder);
            }catch(IOException e){
                e.printStackTrace();
                //error, just exit
                System.exit(0);
            }
        }

        System.out.println("Done");
    }

    public static void getClient(File src, File dest) throws IOException
    {
        if(src.isDirectory()){

            //if directory not exists, create it
            if(!dest.exists()){
                dest.mkdir();
                System.out.println("Directory copied from "
                        + src + "  to " + dest);
            }

            //list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                getClient(srcFile,destFile);
            }

        }else{
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
            System.out.println("File copied from " + src + " to " + dest);
        }


    }
}
