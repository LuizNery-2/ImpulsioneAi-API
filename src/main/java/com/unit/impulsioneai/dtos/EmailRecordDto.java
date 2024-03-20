package com.unit.impulsioneai.dtos;

import java.time.LocalDateTime;

import com.unit.impulsioneai.enums.StatusEmail;

public record EmailRecordDto (String ownerRef, String emailFrom, String emailTo, 
String subject, String text, LocalDateTime sendDateEmail, StatusEmail statusEmail){

    
}
