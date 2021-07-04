package com.test.question;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 2G内存对1000G数据进行排序
 */
public class N0003_DataSort {

    long subLength = 10489;

    @Test
    public void test0() throws IOException {
        N0003_DataSort process = new N0003_DataSort();
        // 初始化数据
        process.initData();
        // 分治 并排序
        process.cutAndSort();
        // 归并排序
        process.mergeSort();
    }

    // 归并排序
    @Test
    public void mergeSort() throws IOException {
        // 1 - 10
        File[] files = new File[10];
        for (int i = 0; i < 10; i++) {
            File file1 = new File("src/main/resources/0003/data-cut" + (i + 1) + ".txt");
            files[i] = file1;
        }
        File[] mergeSort = mergeSort(files);
        for (int i = 0; i < mergeSort.length; i++) {
            mergeSort[i].renameTo(new File("src/main/resources/0003/data-end" + (i + 1) + ".txt"));
            System.out.println("mergeSort["+i+"].getName() = " + mergeSort[i].getName());
            mergeSort[i].delete();
        }
    }

    public File[] mergeSort(File[] files) throws IOException {
        if (files.length < 2) {
            return files;
        }
        int middle = (int) Math.floor(files.length / 2);
        File[] left = Arrays.copyOfRange(files, 0, middle);
        File[] right = Arrays.copyOfRange(files, middle, files.length);
        File[] files1 = mergeSort(left);
        File[] files2 = mergeSort(right);
        File[] files3 = mergeSort(files1, files2);
        for (int i = 0; i < files1.length; i++) {
            if (!files1[i].getName().contains("cut"))
            files1[i].delete();
        }
        for (int i = 0; i < files2.length; i++) {
            if (!files2[i].getName().contains("cut"))
            files2[i].delete();
        }
        return files3;
    }

    @Test
    public void testMergeSort() throws IOException {
        File file1 = new File("src/main/resources/0003/data-cut" + 1 + ".txt");
        File file2 = new File("src/main/resources/0003/data-cut" + 2 + ".txt");
        File[] files = mergeSort(mergeSort(new File[]{file1}), mergeSort(new File[]{file2}));
        System.out.println("Arrays.toString(files) = " + Arrays.toString(files));
    }

    public File[] mergeSort(File[] left, File[] right) throws IOException {
        File[] newFiles = new File[left.length + right.length];

        int leftFileIndex = 0;
        int rightFileIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        List<Integer> list1 = readFile(left[leftFileIndex++]);
        List<Integer> list2 = readFile(right[rightFileIndex++]);


        Integer leftVal = null;
        if (leftIndex < list1.size()) {
            leftVal = list1.get(leftIndex++);
        } else if (leftFileIndex < left.length) {
            // 换下一个文件
            list1 = readFile(left[leftFileIndex++]);
            leftIndex = 0;
            leftVal = list1.get(leftIndex++);
        }

        Integer rightVal = null;
        if (rightIndex < list2.size()) {
            rightVal = list2.get(rightIndex++);
        } else if (leftFileIndex < left.length) {
            // 换下一个文件
            list2 = readFile(right[rightFileIndex++]);
            rightIndex = 0;
            rightVal = list2.get(rightIndex++);
        }


        int totalRead = 0;
        for (int i = 0; i < newFiles.length; i++) {
            newFiles[i] = new File("src/main/resources/0003/data-" + System.currentTimeMillis() + ".txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFiles[i]));
            while (true) {
                Integer nextContent = 0;
                // 获取下一个内容
                if (leftVal != null && rightVal != null) {
                    if (leftVal <= rightVal) {
                        nextContent = leftVal;
                        // 获取下一个 leftVal
                        if (leftIndex < list1.size()) {
                            leftVal = list1.get(leftIndex++);
                        } else if (leftFileIndex < left.length) {
                            // 换下一个文件
                            list1 = readFile(left[leftFileIndex++]);
                            leftIndex = 0;
                            leftVal = list1.get(leftIndex++);
                        } else {
                            leftVal = null;
                        }
                    } else {
                        nextContent = rightVal;
                        if (rightIndex < list2.size()) {
                            rightVal = list2.get(rightIndex++);
                        } else if (rightFileIndex < right.length) {
                            // 换下一个文件
                            list2 = readFile(right[rightFileIndex++]);
                            rightIndex = 0;
                            if (list2.size() == 0) {
                                System.out.println("list2 = " + list2);
                            }
                            rightVal = list2.get(rightIndex++);
                        } else {
                            rightVal = null;
                        }
                    }
                } else if (leftVal != null && rightVal == null) {
                    nextContent = leftVal;
                    // 获取下一个 leftVal
                    if (leftIndex < list1.size()) {
                        leftVal = list1.get(leftIndex++);
                    } else if (leftFileIndex < left.length) {
                        // 换下一个文件
                        list1 = readFile(left[leftFileIndex++]);
                        leftIndex = 0;
                        leftVal = list1.get(leftIndex++);
                    } else {
                        leftVal = null;
                    }
                } else if (leftVal == null && rightVal != null) {
                    nextContent = rightVal;
                    if (rightIndex < list2.size()) {
                        rightVal = list2.get(rightIndex++);
                    } else if (rightFileIndex < right.length) {
                        // 换下一个文件
                        list2 = readFile(right[rightFileIndex++]);
                        rightIndex = 0;
                        rightVal = list2.get(rightIndex++);
                    } else {
                        rightVal = null;
                    }
                } else {
                    break;
                }
//                System.out.println("nextContent = " + nextContent);

                bw.write(nextContent.toString());
                bw.newLine();
                totalRead += nextContent.toString().length() + 1;
                if (totalRead > subLength) {
                    totalRead = 0;
                    break;
                }
            }

            bw.close();
        }

        return newFiles;
    }


    public List<Integer> readFile(File file) throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader(file));
        List<Integer> list = new ArrayList<>();
        String s = "";
        while ((s = br1.readLine()) != null) {
            list.add(Integer.parseInt(s));
        }
        br1.close();
        if (list.size() == 0) {
            System.out.println("file = " + file.getName());
        }
        return list;
    }


    // 分治
    // 分成 10个文件 并排序
    @Test
    public void cutAndSort() throws IOException {
        File file = new File("src/main/resources/0003/data.txt");
        long length = file.length();
        long subLength = length / 10;
        System.out.println("subLength = " + subLength);

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/0003/data.txt"));) {
            long totalRead = 0;
            int i = 0;
            String s = "";
            while (s != null) {
                i++;
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/0003/data-cut" + i + ".txt"));
                BufferedWriter bw2 = new BufferedWriter(new FileWriter("src/main/resources/0003/data-nocut" + i + ".txt"));
                List<Integer> list = new ArrayList<>();
                while ((s = br.readLine()) != null) {
                    list.add(Integer.parseInt(s));
                    totalRead += s.length() + 1;
                    if (totalRead > subLength) {
                        totalRead = 0;
                        for (int j = 0; j < list.size(); j++) {
                            bw2.write(list.get(j).toString());
                            bw2.newLine();
                        }
                        // 排序
                        list.sort(Integer::compareTo);
                        for (int j = 0; j < list.size(); j++) {
                            bw.write(list.get(j).toString());
                            bw.newLine();
                        }
                        list.clear();
                        bw2.flush();
                        bw2.close();
                        bw.flush();
                        bw.close();
                        break;
                    }
                }
                if (list.size() > 0) {
                    for (int j = 0; j < list.size(); j++) {
                        bw2.write(list.get(j).toString());
                        bw2.newLine();
                    }
                    list.sort(Integer::compareTo);
                    for (int j = 0; j < list.size(); j++) {
                        bw.write(list.get(j).toString());
                        bw.newLine();
                    }
                    bw2.flush();
                    bw2.close();
                    bw.flush();
                    bw.close();
                }

            }
        }
    }


    @Test
    public void testfile() throws IOException {
        File file = new File("src/main/resources/0003/data.txt");
        long length = file.length();
        long subLength = length / 10;
        System.out.println("subLength = " + subLength);

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/0003/data-test.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/0003/data-test.txt", true));
        ) {
            String s = "";
            while ((s = br.readLine()) != null) {
                System.out.println("s = " + s);
            }
        }
    }

    @Test
    public void testmergfile() throws IOException {
        File file1 = new File("src/main/resources/0003/data-merge12345.txt");
        File file2 = new File("src/main/resources/0003/data-merge678910.txt");

        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/0003/data-merge12345678910.txt"));
        ) {
            List<Integer> list1 = readFile(file1);
            List<Integer> list2 = readFile(file2);
            List<Integer> list = new ArrayList<>();
            list.addAll(list1);
            list.addAll(list2);
            list.sort(Integer::compare);
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i).toString());
                bw.newLine();
            }
        }
    }

    // 生成2g数据
    @Test
    public void initData() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/0003/data.txt"));) {
            Random random = new Random();
            for (int i = 0; i < 10000; i++) {
                bw.write(random.nextInt(Integer.MAX_VALUE) + "");
                bw.newLine();
            }
        }
    }

    @Test
    public void endDdata() throws IOException {
        File file = new File("src/main/resources/0003/data.txt");
        long length = file.length();
        long subLength = length / 10;
        System.out.println("subLength = " + subLength);

        try (
             BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/0003/data2.txt"));
        ) {
            List<Integer> list = readFile(file);
            list.sort(Integer::compare);
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i).toString());
                bw.newLine();
            }
        }
    }
}
