package com.ruoyi.test.conrtroller;

import com.ruoyi.test.domain.TcUser;
import com.ruoyi.test.service.TcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TcUserController {
    @Autowired
    private TcUserService tcUserService;

    //查询所有
    //    http://localhost/test/selectall
    @RequestMapping("selectAll")
    @ResponseBody
    public String selectAll(){
        List<TcUser> users=tcUserService.selectAll();
        users.forEach(System.out::println);
        return users.toString()+"";
    }


    //查询 by id
    //    http://localhost/test/selectById/1(此处1为要获取的id）
    @RequestMapping(value = "selectById/{id}", method = RequestMethod.GET)
    public String selectById(@PathVariable int id) {
        return tcUserService.selectById(id).toString();
    }

    //插入新用户
    //    http://localhost/test/insert?id=100&name=张三&password=20
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public TcUser insert(TcUser tcUser) {
        return tcUserService.insert(tcUser);
    }

    //通过用户id删除用户
    //    http://localhost/test/deleteById?id=1(此处1为要删除的id）
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public String delete(int id) {
        int result = tcUserService.deleteById(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    //更新 by id
    //  http://localhost/test/updateById?id=2&name=波波&password=12
    @RequestMapping(value = "/updateById", method = RequestMethod.GET)
    public String update(TcUser tcUser) {
        int result = tcUserService.updateById(tcUser);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }
}
