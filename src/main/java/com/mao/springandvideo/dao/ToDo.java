package com.mao.springandvideo.dao;

import lombok.Data;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;

@Data
public class ToDo {
    private Long time;  // 使用 Long 存储时间戳
    private String title;
    private boolean completed;

    // 方法用于将时间戳转换为 LocalTime
    public LocalTime getLocalTime() {
        return Timestamp.from(Instant.ofEpochMilli(this.time)).toLocalDateTime().toLocalTime();
    }
}
