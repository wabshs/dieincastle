package com.taffy.neko.controller;


import com.taffy.neko.Result.R;

import com.taffy.neko.models.dto.UpdateUserStatusDTO;
import com.taffy.neko.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
@Api(tags = "管理员模块")
public class AdminController {

    @Resource
    private AdminService adminService;

    @GetMapping("/articlePage")
    @ApiOperation(value = "条件分页查询全部文章")
    public R<?> articlePage(@RequestParam int pageNum,
                            @RequestParam int pageSize,
                            @RequestParam(defaultValue = "") String header,
                            @RequestParam(defaultValue = "0") String sortBy
    ) {
        return adminService.articlePage(pageNum, pageSize, header, sortBy);
    }

    @ApiOperation(value = "根据id删除文章")
    @DeleteMapping("/deleteArticle/{id}")
    public R<?> deleteArticleById(@PathVariable String id) {
        return adminService.deleteArticleById(id);
    }

    @GetMapping("/getCommentsById")
    @ApiOperation(value = "根据文章id分页查询所有评论")
    public R<?> getCommentByArticleId(@RequestParam int pageNum,
                                      @RequestParam int pageSize,
                                      @RequestParam String id) {
        return adminService.getCommentsById(pageNum, pageSize, id);
    }

    @DeleteMapping("/deleteCommentById/{id}")
    @ApiOperation(value = "根据id删除评论")
    public R<?> deleteCommentById(@PathVariable String id) {
        return adminService.deleteCommentById(id);
    }

    @ApiOperation(value = "分页查询用户信息")
    @GetMapping("/userInfoPage")
    public R<?> userPage(@RequestParam int pageNum,
                         @RequestParam int pageSize,
                         @RequestParam String userName,
                         @RequestParam String nickName){
        return adminService.userInfoPage(pageNum,pageSize,userName,nickName);
    }

    @PutMapping("/updateStatus")
    @ApiOperation(value = "更改用户状态")
    public R<?> updateStatus(@RequestBody UpdateUserStatusDTO reqDTO){
        return adminService.updateStatus(reqDTO);
    }

}
