package com.future.wms.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.future.wms.common.ActiveUser;
import com.future.wms.common.ResultObj;
import com.future.wms.common.WebUtils;
import com.future.wms.model.entity.SysLoginfo;
import com.future.wms.model.vo.SysUserVo;
import com.future.wms.service.ISysLoginfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @author evanliu
 * @create 2021-03-24 23:24
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ISysLoginfoService sysloginfoService;

    @RequestMapping("/login")
    public ResultObj login(SysUserVo userVo, String code, HttpSession session) {

        //获得存储在session中的验证码
        String sessionCode = (String) session.getAttribute("code");
        if (code != null && sessionCode.equals(code)) {
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token = new UsernamePasswordToken(userVo.getLoginname(), userVo.getPwd());
            try {
                //对用户进行认证登陆
                subject.login(token);
                //通过subject获取以认证活动的user
                ActiveUser ActiveUser = (ActiveUser) subject.getPrincipal();
                //将user存储到session中
                WebUtils.getSession().setAttribute("user", ActiveUser.getSysUser());
                //记录登陆日志
                SysLoginfo entity = new SysLoginfo();
                entity.setLoginname(ActiveUser.getSysUser().getName() + "-" + ActiveUser.getSysUser().getLoginname());
                entity.setLoginip(WebUtils.getRequest().getRemoteAddr());
                entity.setLogintime(new Date());
                sysloginfoService.save(entity);

                return ResultObj.LOGIN_SUCCESS;
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return ResultObj.LOGIN_ERROR_PASS;
            }
        } else {
            return ResultObj.LOGIN_ERROR_CODE;
        }

    }

    /**
     * 得到登陆验证码
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36, 4, 5);
        session.setAttribute("code", lineCaptcha.getCode());
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            lineCaptcha.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
