package com.geekbrains.coreservice.controller;

import com.geekbrains.coreservice.service.ExcelReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//-Протестировать задержку
//-Реализовать хранение ссылок в бд
//-FileService рефакторинг

@RestController
@RequiredArgsConstructor
public class FileSendingController {

    private final ExcelReportService excelReportService;

    @Value(value = "${gateway.path}")
    private String excelUrl;

    @MessageMapping("/web-socket")
    @SendTo("/topic/file")
    public String getExcelFile(String message) throws IOException {
        excelReportService.createFile(message);
        return excelUrl + message;
    }

    @GetMapping(value = "/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/octet-stream"));
        headers.set("content-disposition", "inline; filename=" + filename);
        return new ResponseEntity<>(excelReportService.getFile(filename), headers, HttpStatus.OK);
    }

}
