package com.mao.springandvideo.controller;

import com.mao.springandvideo.dao.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {
    // 获取项目根目录
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + File.separator + "target" + File.separator + "upload" + File.separator;

    // 文件上传接口
    @PostMapping("/upload")
    public Result<? extends Object> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("文件不能为空");
        }

        try {
            // 获取上传文件的原始文件名
            String fileName = file.getOriginalFilename();
            // 构建文件保存的路径
            Path path = Paths.get(UPLOAD_DIR + fileName);

            // 创建目录（如果不存在）
            Files.createDirectories(path.getParent());

            // 将上传的文件保存到指定路径
            file.transferTo(path.toFile());

            return Result.success("文件上传成功: " + fileName);
        } catch (IOException e) {
            return Result.error("文件上传失败: " + e.getMessage());
        }
    }
}
