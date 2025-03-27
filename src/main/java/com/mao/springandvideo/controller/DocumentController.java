package com.mao.springandvideo.controller;

import com.mao.springandvideo.dao.Document;
import com.mao.springandvideo.dao.Result;
import com.mao.springandvideo.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private DocumentMapper documentMapper;

    @PostMapping("/publish")
    public Result publishDocument(@RequestBody Document document) {
        documentMapper.publish(document);
        return Result.success("Document published successfully.");
    }

    @GetMapping("/getAll")
    public Result getAllDocuments() {
        // 调用Mapper方法获取所有文档信息
        return Result.success(documentMapper.getAll());
    }

    @GetMapping("/detail")
    public Result getDocumentDetail(@RequestParam("id") String  id) {
        System.out.println("Document ID: " + id);
        if (id == null) {
            return Result.error("缺少文档 ID");
        }

        Document document = documentMapper.getById(id);
        if (document == null) {
            return Result.error("未找到对应文档");
        }

        return Result.success(document);
    }
}
