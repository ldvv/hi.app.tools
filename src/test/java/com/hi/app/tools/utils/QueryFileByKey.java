package com.hi.app.tools.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class QueryFileByKey {

    //计算文件数量
    public static int count = 0;

    //关键字
    public static String keyWords = "crmeb";

    //搜索后查询到的文件路径汇总文件地址
    public static String searchedFilePath = "E:\\searchedFile.txt";

    public static File searchedFile = new File(searchedFilePath);

    public static FileOutputStream fos = null;

    public static void main(String[] args) {
        String path = "C:\\Users\\30045\\Downloads\\CRMEB_DT_v3.0.1";
//        String path = "G:/Document/HongDaXingYe/Project/oa/workflow/home/weaver/ecology/workflow/mode/";
        try {
            fos = new FileOutputStream(searchedFile);
            if (!searchedFile.exists()) {
                searchedFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(path);
        File[] files = file.listFiles();
        getFiles(files);
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }

    //递归搜索文件
    public static void getFiles(File[] files) {
        FileInputStream fis = null;
        try {
            for (File file : files) {
                count++;
                if (file.isDirectory()) {
                    getFiles(file.listFiles());
                } else {
                    if(!file.getPath().toLowerCase().contains("htm")){
                        continue;
                    }
                    StringBuilder sb = new StringBuilder();
                    byte[] bytes = new byte[1024];
                    fis = new FileInputStream(file);
                    int len = 0;
                    while ((len = fis.read(bytes)) != -1) {
                        sb.append(new String(bytes, 0, len));
                    }
                    fis.close();
                    if (sb.toString().toLowerCase().indexOf(keyWords) >= 0) {
                        System.out.println(file.getAbsolutePath());
                        fos.write((file.getAbsolutePath() + System.lineSeparator()).getBytes());
                        fos.flush();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
