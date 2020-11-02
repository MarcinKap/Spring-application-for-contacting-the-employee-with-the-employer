//package com.example.Project_Spring.configuration;
//
//import org.springframework.messaging.Message;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//import java.sql.Date;
//import java.text.SimpleDateFormat;
//
//@Controller
//public class SocketController {
//
//    @MessageMapping("/secured/chat")
//    @SendTo("/secured/history")
//    public OutputMessage send(Message msg) throws Exception {
//        return new OutputMessage(
//                msg.getFrom(),
//                msg.getText(),
//                new SimpleDateFormat("HH:mm").format(new Date()));
//    }
//}