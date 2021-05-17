package com.zqs.serializer;

import com.gitlab.techschool.pcbook.pb.Laptop;
import com.zqs.sample.Generator;
import org.junit.Assert;
import org.junit.Test;

public class SerializerTest {

    @Test
    public void writeAndReadBinaryFile() throws Exception {
        String binaryFile = "laptop.bin";
        Laptop laptop1 = new Generator().NewLaptop();

        Serializer serializer = new Serializer();
        serializer.WriteBinaryFile(laptop1, binaryFile);

        Laptop laptop2 = serializer.ReadBinaryFile(binaryFile);
        Assert.assertEquals(laptop1, laptop2);
    }


}