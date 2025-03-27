package com.mao.springandvideo.mapper;

import com.mao.springandvideo.dao.Document;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.print.Doc;
import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface DocumentMapper {
    @Insert("insert into document(create_time, author, content) values(NOW(), #{author}, #{content})")
    void publish(Document document);

    @Select("select * from document")
    List<Document> getAll();

    @Select("select * from document where create_time = #{id}")
    Document getById(String id);
}
