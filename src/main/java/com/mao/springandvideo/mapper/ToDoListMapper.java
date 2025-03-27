package com.mao.springandvideo.mapper;

import com.mao.springandvideo.dao.ToDo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface ToDoListMapper {
    @Insert("INSERT INTO todos(time, title, completed) VALUES(#{time}, #{title}, #{completed})")
    void addTodo(ToDo toDo);

    @Delete("DELETE FROM todos WHERE time = #{time}")
    void removeTodo(ToDo toDo);

    @Update("UPDATE todos SET title = #{title} WHERE time = #{time}")
    void updateTodo(Object toDo);

    @Select("SELECT * FROM todos")
    List<ToDo> getAllTodo();

    @Update("UPDATE todos SET completed = #{completed} WHERE time = #{time}")
    void updateTodoStatus(ToDo toDo);

    @Update("UPDATE todos SET title = #{title}, completed = #{completed} WHERE time = #{time}")
    void doneEdit(ToDo toDo);

    @Delete("DELETE FROM todos WHERE completed = true")
    void removeCompleted();
}
