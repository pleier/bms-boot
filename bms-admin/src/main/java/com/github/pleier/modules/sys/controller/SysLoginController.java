package com.github.pleier.modules.sys.controller;

import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.modules.sys.shiro.ShiroUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录
 *
 * @author : pleier
 * @date : 2018/3/26
 */
@Controller
public class SysLoginController {
    @Autowired
    private Producer producer;

    /**
     * 生成验证码
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException {

        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha  验证码
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/sys/login")
    public BmsResponse login(String username, String password, String captcha) {
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return BmsResponse.error("验证码不正确");
        }

        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return BmsResponse.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return BmsResponse.error("账号或密码不正确");
        } catch (LockedAccountException e) {
            return BmsResponse.error("账号已被锁定,请联系管理员");
        } catch (AuthenticationException e) {
            return BmsResponse.error("账户验证失败");
        }

        return BmsResponse.ok();
    }

    /**
     * 退出
     */
    @GetMapping(value = "logout")
    public String logout() {
        ShiroUtils.logout();
        return "redirect:login.html";
    }
}
