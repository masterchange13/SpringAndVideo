package com.mao.springandvideo.mapper;

import com.mao.springandvideo.dao.Navigator;
import com.mao.springandvideo.dao.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NavigatorMapper {
    @Select("select * from navigators")
    public List<Navigator> getAllNavigators();

    @Insert("insert into navigators (name, url, img) values(#{name}, #{url}, #{img})")
    public int addNavigator(Navigator navigator);
}
