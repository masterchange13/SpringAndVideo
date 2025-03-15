package com.mao.springandvideo;

import org.opencv.core.Core;

public class OpenCVTest {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        System.out.println("OpenCV Version: " + Core.VERSION);
    }
}
