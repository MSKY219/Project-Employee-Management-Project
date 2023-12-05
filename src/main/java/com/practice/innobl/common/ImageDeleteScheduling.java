package com.practice.innobl.common;

import com.practice.innobl.service.emp.EmpService;
import jakarta.servlet.ServletContext;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class ImageDeleteScheduling {

    private final Logger logger = LoggerFactory.getLogger(ImageDeleteScheduling.class);

    private final EmpService empService;

    private final ServletContext application;

    // 사용중이지 않는 이미지 삭제
    @Scheduled(cron = "0 0/10 * * * *")
    public void imageDelete() {
        List<String> dbList = empService.getImgList();

        String folderPath = application.getRealPath("/images/");

        File path = new File(folderPath);

        File[] arr = path.listFiles();

        List<File>serverList = Arrays.asList(arr);

        if(!serverList.isEmpty()) {
            for(File serverImg : serverList) {
                String name = "/images/" + serverImg.getName();

                if(dbList.indexOf(name) == -1) {
                    logger.info(serverImg.getName() + "삭제됨");
                    serverImg.delete();
                }
            }
        }
    }

}
