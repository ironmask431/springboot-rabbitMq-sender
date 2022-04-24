package com.leesh.sender.module;

import com.leesh.sender.dto.DeptDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SenderModule {

    final RabbitTemplate rabbitTemplate;

    //@Scheduled(fixedRate = 1000) //100ms마다 실행
    public void sender1(){
        // System.out.println("주기적으로 실행");

        LocalDateTime nowDateTime = LocalDateTime.now();
        String time = nowDateTime.toString();

        System.out.println("==> [전송]sender time : "+time);
        //mq로 메세지 전송.
        rabbitTemplate.convertAndSend("time","time-first",time);
    }

    @Scheduled(fixedRate = 1000) //100ms마다 실행
    public void sender2(){
        // System.out.println("주기적으로 실행");

        LocalDateTime nowDateTime = LocalDateTime.now();
        String time = nowDateTime.toString();

        DeptDto deptDto = new DeptDto(10,"testName","testLoc",time);

        System.out.println("==> [전송]sender deptDto : "+deptDto.toString());
        //mq로 메세지 전송.
        rabbitTemplate.convertAndSend("deptDto","deptDto-first", deptDto);
    }
}