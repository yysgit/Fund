package com.yys.fund.controller;

import com.yys.fund.config.jwt.JwtEntity;
import com.yys.fund.config.jwt.JwtIgnore;
import com.yys.fund.config.jwt.JwtUtils;
import com.yys.fund.entity.DbUser;
import com.yys.fund.service.DbUserService;
import com.yys.fund.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("fund/userLogin")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private DbUserService userService;
    @Autowired
    private JwtEntity jwtEntity;
    @RequestMapping("/login.html")
    public String fundHtml() {
        return "login";
    }
    @RequestMapping("/home.html")
    public ModelAndView homeHtml(HttpServletRequest request, ModelAndView modelAndView) {

        DbUser dbUser = (DbUser)request.getSession().getAttribute("dbUser");
        if (dbUser == null) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.addObject("dbUser",dbUser);
        modelAndView.setViewName("home");

        return modelAndView;
    }

    /**
     * 管理员登陆
     *
     * @param map
     * @param request
     * @param request
     * @return
     */
    @RequestMapping("/login")
    @JwtIgnore // 加此注解, 请求不做token验证
    @ResponseBody
    public ResultUtil login(@RequestBody Map map, HttpServletRequest request, ModelAndView modelAndView) {
        try {

            logger.debug("输出debug级别的日志.....");
            logger.info("输出info级别的日志.....");
            logger.error("输出error级别的日志.....");

            String username=String.valueOf(map.get("pass"));
            String password=String.valueOf(map.get("checkPass"));

            DbUser dbUser = userService.login(username, password);
            if (dbUser != null) {
                String access_token = JwtUtils.createToken(dbUser.getId().toString(), jwtEntity);
                if (access_token == null) {
                    System.out.println("===== 用户签名失败 =====");
                    return ResultUtil.error("用户签名失败!");
                }
                System.out.println("===== 用户" + username + "生成签名" + access_token + "=====");

                request.getSession().setAttribute("dbUser",dbUser);
                modelAndView.addObject("id","21343");

                return ResultUtil.success(JwtUtils.getAuthorizationHeader(access_token));
            } else {
                return ResultUtil.error("用户名或者密码错误!");
            }
        } catch (Exception e) {

            logger.error("登陆异常:" + e);
            return ResultUtil.error("登陆异常!");
        }
    }


    @RequestMapping("/loginOut")
    @JwtIgnore // 加此注解, 请求不做token验证
    @ResponseBody
    public ResultUtil loginOut(@RequestBody Map map,  HttpServletRequest request) {
        request.getSession().removeAttribute("dbUser");
        return ResultUtil.success();
    }

    /**
     * 添加管理用户
     *
     * @param request
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public ResultUtil addUser(HttpServletRequest request, @RequestBody DbUser user) {
        try {



//            //-----特殊处理开始--------
//            DbUser adminUser2 = (DbUser) request.getAttribute("adminUser");
//            DbOrgan organ2 = organService.findOrganById(adminUser2.getOrganId());
//            //运营员特殊处理 level 等于2 表示运营部
//            if (organ2 != null && organ2.getLevel() == 2) {
//                //查询父级下面的所有机构
//                List<DbOrgan> organChildList = organService.findOrganByParentId(organ2.getParentId());
//                for (DbOrgan organ3 : organChildList) {
//                    if (organ3.getLevel() == 3) {
//                        adminUserMy.setOrganId(organ3.getId());
//                    }
//                }
//            } else {
//                adminUserMy.setOrganId(StringISNULLUtil.mapToInteger(adminUser1.get("organId")));
//            }
//            //-----特殊处理结束--------
//
//            adminUserMy.setRoleId(StringISNULLUtil.mapToInteger(adminUser1.get("roleId")));
//
//            adminUserMy.setPassword(MD5.MD5Pwd(adminUserMy.getName(), "888888"));
//            //验证名称是否重复
//            List<DbUser> adminUserList = adminService.findUserByName(null, adminUserMy.getName());
//            if (adminUserList != null && adminUserList.size() > 0) {
//                return ResultUtil.error("添加失败,名称重复!");
//            }
//            adminService.addUser(adminUserMy);
            return ResultUtil.success("添加成功!");
        } catch (Exception e) {

            logger.error("添加管理用户错误: " + e);
            return ResultUtil.error("添加失败!");
        }
    }


    /**
     * 更新用户
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public ResultUtil deleteUser(HttpServletRequest request, Integer adminUserId) {
        try {
            DbUser dbUser=(DbUser)request.getSession().getAttribute("dbUser");
            if(dbUser==null){
                return ResultUtil.error("删除失败,未登录!");
            }
            DbUser adminUser = new DbUser();
            adminUser.setId(adminUserId);
            adminUser.setDeleteStatus(1);
            userService.deleteUser(adminUser);
            return ResultUtil.success("删除成功!");
        } catch (Exception e) {

            logger.error("删除管理用户错误: " + e);
            return ResultUtil.error("删除失败!");
        }
    }

}
