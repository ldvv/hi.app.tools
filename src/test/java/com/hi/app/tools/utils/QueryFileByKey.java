package com.hi.app.tools.utils;


import java.io.*;

public class QueryFileByKey {

    //计算文件数量
    public static int count = 0;

    //关键字
    public static String keyWords = "public function";

    //搜索后查询到的文件路径汇总文件地址
    public static String searchedFilePath = "E:\\searchedFile.txt";

    public static File searchedFile = new File(searchedFilePath);

    public static FileOutputStream fos = null;

    public static void main(String[] args) {
        String path = "D:\\soft\\php\\Apache24\\htdocs\\system\\modules";
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
        StringBuilder sb = new StringBuilder();
        try {
            for (File file : files) {
                if (file.isDirectory()) {
                    getFiles(file.listFiles());
                } else {
                    if(!file.getPath().toLowerCase().contains(".php")){
                        continue;
                    }
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String str;
                    while((str = bufferedReader.readLine()) != null){
                        if(str.contains(keyWords)){
                            count++;
                            sb.append(str).append("\n");
                        }
                    }
                    bufferedReader.close();
                    System.out.println(file.getAbsolutePath());
                }
            }
            fos.write(sb.toString().getBytes());
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
