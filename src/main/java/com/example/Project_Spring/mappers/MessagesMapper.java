package com.example.Project_Spring.mappers;


import com.example.Project_Spring.models.Messages;
import com.example.Project_Spring.models.MessagesDto;
import org.springframework.stereotype.Component;

@Component
public class MessagesMapper implements Mapper<Messages, MessagesDto> {
    @Override
    public MessagesDto map(Messages from) {
        return MessagesDto
                .builder()
                .id(from.getId())
                .email_sender(from.getEmail_sender())
                .id_recipient(from.getId_recipient())
                .id_sender(from.getId_sender())
                .text_msg(from.getText_msg())
                .topic(from.getTopic())
                .id_topic(from.getId_topic())
                .createdDate(from.getCreatedDate())
                .nameSender(from.getNameSender())
                .build();
    }
    @Override
    public Messages reverseMap(MessagesDto to) {
        return Messages
                .builder()
                .id(to.getId())
                .email_sender(to.getEmail_sender())
                .id_recipient(to.getId_recipient())
                .id_sender(to.getId_sender())
                .text_msg(to.getText_msg())
                .topic(to.getTopic())
                .id_topic(to.getId_topic())
                .createdDate(to.getCreatedDate())
                .nameSender(to.getNameSender())
                .build();
    }
}


//
//
//@Component
//public class GlassesMapper implements Mapper<Messages, MessagesDto> {
//    @Override
//    public GlassesDto map(Glasses from) {
//        return GlassesDto
//                .builder()
//                .glassesType(from.getGlassesType())
//                .glassesNumber(from.getGlassesNumber())
//                .glassesGender(from.getGlassesGender())
//                .form(from.getForm())
//                .price(from.getPrice())
//                .polarization(from.getPolarization())
//                .widthOfTheLens(from.getWidthOfTheLens())
//                .glassesImage(from.getGlassesImage())
//                .glassesMarks(from.getGlassesMarks())
//                .id(from.getId())
//                .build();
//    }
//    @Override
//    public Glasses reverseMap(GlassesDto to) {
//        return Glasses
//                .builder()
//                .glassesType(to.getGlassesType())
//                .glassesNumber(to.getGlassesNumber())
//                .glassesGender(to.getGlassesGender())
//                .form(to.getForm())
//                .price(to.getPrice())
//                .polarization(to.getPolarization())
//                .widthOfTheLens(to.getWidthOfTheLens())
//                .glassesImage(to.getGlassesImage())
//                .glassesMarks(to.getGlassesMarks())
//                .id(to.getId())
//                .build();
//    }
//}