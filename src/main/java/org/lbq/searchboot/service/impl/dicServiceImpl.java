package org.lbq.searchboot.service.impl;

import org.lbq.searchboot.service.DicService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class dicServiceImpl implements DicService {

    @Override   //读取词典内容
    public List<String> getDic() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("static/init.dic");
        File file = classPathResource.getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> words= new ArrayList();
        while (true){
            String s = bufferedReader.readLine();
            if(s==null) break;
            words.add(s);
        }
        bufferedReader.close();
        return words;
    }


    @Override   //添加词汇
    public void addDic(String word) throws IOException {
        //去空格
        String trimWord = word.trim();

        //如果插入的词汇存在则抛出抛出异常
        if(filterDic(trimWord)) throw  new  RuntimeException("存在相同词汇");

        //获取需要追加的文件
        ClassPathResource classPathResource = new ClassPathResource("static/init.dic");
        File file = classPathResource.getFile();

        //创建文件输入流程
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw  = new BufferedWriter(fw);

        try {

            bw.newLine();
            bw.write(word);

            fw.flush();
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //添加过滤原来的词典
    public  boolean  filterDic(String word) throws IOException {
        List<String> dic = getDic(); //获取文件中存在的词汇

        boolean flag = false;

        for (String s : dic) {
            if(word.equals(s)) flag = true;   //存在则返回true
        }

        return flag;
    };

  //---------------------------------------------分割线-----------------------------------------------------------------------
    @Override //删除词汇
    public void deleteDic(String word) throws IOException {

        //去空格
        String deleteWord = word.trim();

        //获取重新输入的文件
        List<String> strings = filterDeleteDic(deleteWord);

        //获取需要追加的文件
        ClassPathResource classPathResource = new ClassPathResource("static/init.dic");
        File file = classPathResource.getFile();

        //删除原来存在的文件，重新输入
        if(file.exists()){
           file.delete();
        }

       //重新创建文件， 遍历数组 词汇输入到文件
        file.createNewFile();

       //创建输出流
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw  = new BufferedWriter(fw);

        for (String string : strings) {
             bw.write(string);
             bw.newLine();
             fw.flush();
             bw.flush();
        }
           fw.flush();
           bw.flush();
           fw.close();
           bw.close();

        List<String> dic = getDic();
        dic.forEach(System.out::println);


    }

    //过滤文件，过滤出需要重新添加到文件中词汇
    public List<String> filterDeleteDic(String deleteWord) throws IOException {
        //获取文件中存在的词汇
        List<String> dic = getDic();

        //存储需求重新插入文件的词汇
          List<String> newDic = new ArrayList();

        //遍历存在的词汇，把需要删除的词汇从集合中删除，在存储到新集合中
        for (String old : dic) {
            if(!deleteWord.equals(old)){
                newDic.add(old);
            }
        }
        return  newDic;
    };






}
