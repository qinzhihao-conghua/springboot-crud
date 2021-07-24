package com.zh.springbootcrud.controller;

import com.zh.springbootcrud.entity.UserEntity;
import com.zh.springbootcrud.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 注意，如果是前后分离的项目，请使用@ResponseBody注解，@RestController注解相当
 * 于@ResponseBody ＋ @Controller合在一起的作用，使用@RestController这个注解，
 * 就不能返回jsp,html页面，视图解析器无法解析jsp,html页面
 *
 * 1)如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，
 *   配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
 * 2)如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
 *   在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面
 *   若返回json等内容到页面，则需要加@ResponseBody注解
 * 3)如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
 * 解释来源：https://blog.csdn.net/maying0124/article/details/93460794
 *
 * 其实简单说就是如果是jsp页面的项目，才会有视图解析器这个概念，才会使用@Controller这个注解，但是在目前的
 * 大部分公司开发都是前后分离，几乎已经没有jsp开发的系统了，所以可以直接使用@RestController返回json数据就够了
 */
// @Controller
// @ResponseBody
@RestController
public class UserController {

    /**
     * 这里的@Resource注解就是把一个bean注入到当前的类中，可以不必通过配置文件或者导包的方式
     * 注入就可以使用该bean，默认是ByName的方式注入，如：
     * @Resource（name=“personDaoBean”）
     * private UserService userService;
     * 这样就可以直接使用UserService这个Bean，以及其setter和getter方法。
     * 在controller层中需要使用service层中对应的数据处理方法
     */
    @Resource
    private UserService userService;

    /**
     * 直接返回字符串
     * @return
     */
    @RequestMapping("/")
    public String login(){
        return "{content:登陆成功,id:1}";
    }

    /**
     * 查询所有数据，没有分页
     * @return
     */
    @RequestMapping("/getAllUser")
    public List getAllUser(){
        return userService.getAllUser();
    }

    /**
     * 自定义sql语句查询
     * @return
     */
    @RequestMapping("/getAllUserBySQL")
    public List getAllUserBySQL(){
        return userService.getAllUserBySQL();
    }
    /**
     * 自定义sql语句模糊查询，不分页
     * @return
     */
    @RequestMapping(value = "/getUserByNameSQL",params = {"userName"})
    public List getUserByNameSQL(@RequestParam String userName){
        return userService.getUserByNameSQL(userName);
    }

    /**
     * 模糊查询并分页
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getUserByNamePage",params = {"userName","currentPage","pageSize"})
    public List getUserByNamePage(@RequestParam String userName,int currentPage,int pageSize){
        return userService.getUserByNamePage(userName,currentPage,pageSize);
    }

    /**
     * 自定义sql语句查询并排序分页
     * @return
     */
    @RequestMapping(value = "/getUserSoreAndPageBySQL",params = {"page","size","sort"})
    public Page<UserEntity> getUserSoreAndPageBySQL(
            @RequestParam String page,
            @RequestParam String size,
            @RequestParam String sort
    ){
        return userService.getUserSoreAndPageBySQL(page,size,sort);
    }

    /**
     * 自定义sql语句新增
     * @RequestBody获取body参数
     * @return
     */
    @RequestMapping(value = "/addUserBySQL",method = RequestMethod.POST)
    public UserEntity addUserBySQL(@RequestBody UserEntity user){
        return userService.addUserBySQL(user);
    }

    /**
     * 自定义sql语句更新
     * @return
     */
    @RequestMapping(value = "/updateUserBySQL",method = RequestMethod.POST)
    public UserEntity updateUserBySQL(@RequestBody UserEntity user){
        return userService.updateUserBySQL(user);
    }

    /**
     * 自定义sql语句删除
     */
    @RequestMapping("/deleteUserBySQL")
    public int deleteUserBySQL(@RequestParam String id){
        return userService.deleteUserBySQL(id);
    }

}
