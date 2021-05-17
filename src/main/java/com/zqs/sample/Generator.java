package com.zqs.sample;

import com.gitlab.techschool.pcbook.pb.*;
import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.util.Random;

public class Generator {

    private Random rand;

    public Generator() {
        rand = new Random();
    }

    public Keyboard NewKeyboard() {
        return Keyboard.newBuilder()
                .setLayout(getLayout())
                .setBacklit(false)
                .build();
    }

    // 枚举类
    private Keyboard.Layout getLayout(){
        switch (rand.nextInt(3)){
            case 1:
                return Keyboard.Layout.QWERTY;
            case 2:
                return Keyboard.Layout.QWERTZ;
            default:
                return Keyboard.Layout.AZERTY;
        }
    }

    public CPU NewCPU() {
        return CPU.newBuilder()
                .setBrand("aa")
                .setName("bb")
                .setNumberCores(8)
                .setNumberThreads(16)
                .setMinGhz(1.0)
                .setMaxGhz(8.0)
                .build();
    }

    public GPU NewGPU() {
        Memory memory = Memory.newBuilder()
                .setValue(1)
                .setUnit(Memory.Unit.GIGABYTE)
                .build();

        return GPU.newBuilder()
                .setBrand("aa")
                .setName("bb")
                .setMinGhz(1.0)
                .setMaxGhz(8.0)
                .setMemory(memory)
                .build();
    }

    public Memory NewMemory() {
        return Memory.newBuilder()
                .setValue(1)
                .setUnit(Memory.Unit.KILOBYTE)
                .build();
    }

    public Storage NewSSD() {
        Memory memory = Memory.newBuilder()
                .setValue(1)
                .setUnit(Memory.Unit.GIGABYTE)
                .build();

        return Storage.newBuilder()
                .setDriver(Storage.Driver.SSD)
                .setMemory(memory)
                .build();
    }

    public Storage NewHHD() {
        Memory memory = Memory.newBuilder()
                .setValue(1)
                .setUnit(Memory.Unit.GIGABYTE)
                .build();

        return Storage.newBuilder()
                .setDriver(Storage.Driver.HDD)
                .setMemory(memory)
                .build();
    }

    public Screen NewScreen() {
        Screen.Resolution resolution = Screen.Resolution
                .newBuilder()
                .setWidth(1)
                .setHeight(2)
                .build();

        return Screen.newBuilder()
                .setSizeInch(2.0f)
                .setResolution(resolution)
                .setPanel(Screen.Panel.OLED)
                .setMultitouch(true)
                .build();
    }

    public Laptop NewLaptop() {
        Instant now = Instant.now();
        Timestamp timestamp = Timestamp.newBuilder()
                .setSeconds(now.getEpochSecond())
                .setNanos(now.getNano())
                .build();

        return Laptop.newBuilder()
                .setId("xx")
                .setBrand("aa")
                .setName("bb")
                .setCpu(NewCPU())
                .setRam(NewMemory())
                .addGpus(NewGPU())  //数组类型的属性用add
                .addStorages(NewSSD())
                .addStorages(NewHHD())
                .setScreen(NewScreen())
                .setKeyboard(NewKeyboard())
                .setWeightKg(12.0)
                .setPriceUsd(2.0)
                .setReleaseYear(2)
                .setUpdatedAt(timestamp)
                .build();
    }

    public static void main(String[] args) {
        Generator generator = new Generator();
        Laptop laptop = generator.NewLaptop();
        System.out.println(laptop);
    }


}
