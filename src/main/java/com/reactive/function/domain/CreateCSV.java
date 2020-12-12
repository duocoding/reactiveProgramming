package com.reactive.function.domain;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName CreateCSV
 * @Description TODO
 * @Author qulingxiao
 * @Date 2020/12/12 23:14
 * @Version 1.0
 */
public class CreateCSV {

    public static void main(String[] args) {
        create("D:/", "fileTest.csv", "姓名", "学号", "专业");
    }

    /**
     * create csv file head
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @param data 文件头
     */
    public static void create(String filePath, String fileName, String... data) {
        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(filePath + fileName);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
            List<String> headList = Arrays.asList(data);
            int num = headList.size() / 2;
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < num; i++) {
                buffer.append(" ,");
            }
//            csvWtriter.write(buffer.toString() + fileName + buffer.toString());
//            csvWtriter.newLine();
            writeRow(headList, csvWtriter);
            csvWtriter.flush();
            System.out.println("SUCCESS");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FAIL");
        }
    }

    private static void writeRow(List<String> row, BufferedWriter csvWriter) throws IOException{
        for (String data : row) {
            StringBuffer buffer = new StringBuffer();
            String rowStr = buffer.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }
}
