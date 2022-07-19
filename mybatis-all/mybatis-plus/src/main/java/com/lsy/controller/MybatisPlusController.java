package com.lsy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.lsy.model.User;
import com.lsy.service.MybatisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@Api(value = "用户controller", tags = {"用户操作API"})
public class MybatisPlusController {
    @Autowired
    MybatisService mybatisService;

    @GetMapping("/test")
    /**
     * @ApiIgnore:
     * 可以不被swagger显示在页面上(默认都会显示)
     */
    @ApiIgnore
    public void test() {
        mybatisService.test();
    }

    @PostMapping("/save")
    /**
     * @ApiOperation
     * value用于方法描述
     * notes用于提示内容
     * tags可以重新分组（视情况而用）
     */
    @ApiOperation(value = "保存", notes = "新增用户信息")
    /**
     * @ApiModel : 用于类 ；表示对类进行说明，用于参数用实体类接收
     * value–表示对象名
     * description–描述
     * @ApiModelProperty()用于方法，字段； 表示对model属性的说明或者数据操作更改    (默认实体类所有字段都会出现在界面上)
     * value–字段说明
     * name–重写属性名字
     * dataType–重写属性类型
     * required–是否必填
     * example–举例说明
     * hidden–隐藏
     */
    public void save(@ApiParam(name = "用户对象", value = "传入json格式", required = true) @RequestBody User user) {
        mybatisService.save(user);
    }

    @PostMapping("/findByEmail")
    @ApiOperation(value = "模糊查找Email", notes = "xxx@xx.xxx")
    /**
     * @ApiParam
     * name–参数名
     * value–参数说明
     * required–是否必填
     */
    public List<User> findByEmail(@ApiParam(name = "email", value = "要查找的email", required = true) @RequestParam(value = "email", required = true) String email) {
        return mybatisService.findByEmail(email);
    }

    @PostMapping("/update")
    public void updateBySomething(@RequestBody User user) {
        mybatisService.updateBySomething(user);
    }

    @PostMapping("/delete")
    public void deleteBySomething(@RequestParam String something) {
        mybatisService.deleteBySomething(something);
    }

    @PostMapping("/page")
    public IPage<User> deleteBySomething() {
        return mybatisService.page();
    }

    @PostMapping("/pageHelper")
    public PageInfo<User> pageHelper() {
        return mybatisService.pageHelper();
    }

    @GetMapping("/findBySql")
    public void findBySql() {
        mybatisService.findBySql();
    }


}
