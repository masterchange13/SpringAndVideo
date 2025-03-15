package com.mao.springandvideo;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CameraViewer {
    static {
        // 加载 OpenCV 库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private JLabel cameraLabel;
    private VideoCapture camera;

    public CameraViewer() {
        JFrame frame = new JFrame("Camera Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);

        cameraLabel = new JLabel();
        frame.add(cameraLabel, BorderLayout.CENTER);

        frame.setVisible(true);

        startCamera();
    }

    private void startCamera() {
        camera = new VideoCapture(0); // 0 表示默认摄像头
        if (!camera.isOpened()) {
            System.out.println("无法打开摄像头！");
            return;
        }

        new Thread(() -> {
            Mat frame = new Mat();
            while (true) {
                if (camera.read(frame)) {
                    Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2RGB);
                    Image img = matToBufferedImage(frame);
                    cameraLabel.setIcon(new ImageIcon(img));
                }
            }
        }).start();
    }

    private Image matToBufferedImage(Mat mat) {
        int width = mat.width(), height = mat.height();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        byte[] data = new byte[width * height * (int) mat.elemSize()];
        mat.get(0, 0, data);
        image.getRaster().setDataElements(0, 0, width, height, data);
        return image;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CameraViewer::new);
    }
}
