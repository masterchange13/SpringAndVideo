package com.mao.springandvideo.dao;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Document {
    private Timestamp create_time;  // 使用 Long 存储时间戳
    private String author;
    private String content;
}
