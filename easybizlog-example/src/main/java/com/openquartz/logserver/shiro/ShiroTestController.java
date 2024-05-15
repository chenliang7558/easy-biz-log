package com.openquartz.logserver.shiro;

import com.openquartz.easybizlog.core.annotation.LogRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author svnee
 */
@RestController
@Slf4j
public class ShiroTestController {

    //2. 访问 hello 方法
    @GetMapping("/doLogin")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "index";
        } catch (Exception e) {
            log.error("log record", e);
            return "error";
        }
    }

    @GetMapping("/hello")
    @LogRecord(success = "success hello hello", type = "shiro.login", bizNo = "99901091")
    public String hello() {
        return "hello";
    }

    @GetMapping("/login")
    public String login() {
        return "please login!";
    }
}
