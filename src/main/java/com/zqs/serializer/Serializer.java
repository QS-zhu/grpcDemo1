package com.zqs.serializer;

import com.gitlab.techschool.pcbook.pb.Laptop;
import com.zqs.sample.Generator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Serializer {

    //序列化
    public void WriteBinaryFile(Laptop laptop, String fileName) throws Exception {
        FileOutputStream fos = new FileOutputStream(fileName);
        laptop.writeTo(fos);
        fos.close();
    }

    // 反序列化
    public Laptop ReadBinaryFile(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Laptop laptop = Laptop.parseFrom(fis);
        fis.close();
        return laptop;
    }

    public static void main(String[] args) throws Exception {
        String binaryFile = "laptop.bin";
        Laptop laptop1 = new Generator().NewLaptop();

        Serializer serializer = new Serializer();
        serializer.WriteBinaryFile(laptop1, binaryFile);

        Laptop laptop2 = serializer.ReadBinaryFile(binaryFile);

        System.out.println(laptop1.equals(laptop2));
    }
}
