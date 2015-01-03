package com.zhaoimpulse.webchat.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author zhaohaifeng
 * @since 2014-12-06
 */
@Controller
public class WebChatController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebChatController.class);

    private static Integer IDENTITY = 0;

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping("/")
    public String chat(Model model) {
        Integer userId = IDENTITY;
        IDENTITY++;
        model.addAttribute("userId", userId);
        return "chat";

    }

    @ResponseBody
    @RequestMapping("/test")
    public String test(Model model) {
        Integer userId = IDENTITY;
        IDENTITY++;
        model.addAttribute("userId", userId);
        return "test";

    }

    @MessageMapping("/web/chat")
    public String webChat(WebChatVo webChatVo) {
        LOGGER.info(JSON.toJSONString(webChatVo));
        LOGGER.info(
                "用户" + webChatVo.getFromUserId() + "向用户" + webChatVo.getToUserId() + "发送了一条消息:" +
                        webChatVo.getMessage());
        simpMessagingTemplate
                .convertAndSend("/ws/web/chat/" + webChatVo.getToUserId(),
                        webChatVo);

        return "success";
    }

    @MessageExceptionHandler
    @SendToUser(value = "/web/chat/errors", broadcast = false)
    public String handleException(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
        return exception.getMessage();
    }
}
