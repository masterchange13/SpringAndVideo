package com.mao.springandvideo.controller;

import com.mao.springandvideo.dao.Result;
import com.mao.springandvideo.dao.ToDo;
import com.mao.springandvideo.mapper.ToDoListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class ToDoListController {
    @Autowired
    private ToDoListMapper toDoListMapper;

    @GetMapping("/getAll")
    public Result getAllToDo(){
        return Result.success(toDoListMapper.getAllTodo());
    }

    @PostMapping("/add")
    public Result addToDo(@RequestBody ToDo toDo){
        System.out.println(toDo);
        toDoListMapper.addTodo(toDo);
        return Result.success("添加成功");
    }

    @PostMapping("/remove")
    public Result removeToDo(@RequestBody ToDo toDo){
        //System.out.println(toDo);
        toDoListMapper.removeTodo(toDo);
        return Result.success("删除成功");
    }

    @PostMapping("/update")
    public Result updateToDo(@RequestBody ToDo toDo){
        toDoListMapper.updateTodo(toDo);
        return Result.success("更新成功");
    }

    @PostMapping("/updateTodoStatus")
    public Result updateTodoStatus(@RequestBody ToDo toDo){

        toDoListMapper.updateTodoStatus(toDo);
        return Result.success("更新成功");
    }

    @PostMapping("/doneEdit")
    public Result doneEdit(@RequestBody ToDo toDo){
        toDoListMapper.doneEdit(toDo);
        return Result.success("更新成功");
    }

    @PostMapping("/removeCompleted")
    public Result removeCompleted(){
        toDoListMapper.removeCompleted();
        return Result.success("删除成功");
    }
}
