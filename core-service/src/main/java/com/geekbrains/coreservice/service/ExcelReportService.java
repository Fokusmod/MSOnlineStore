package com.geekbrains.coreservice.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.coreservice.Dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ExcelReportService {
    @Value(value = "${directory}")
    private String excelPath;
    private final ProductService productService;

    public void createDir() {
        if (!Files.exists(Path.of(excelPath))) {
            try {
                Files.createDirectory(Path.of(excelPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void createFile(String filename) {
        try (Workbook workbook = createWorkBook()) {
            createDir();
            workbook.write(new FileOutputStream(excelPath + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getFile(String filename) {
        try(FileInputStream fileInputStream = new FileInputStream(excelPath + filename)){
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Workbook createWorkBook() {

        Workbook productInfo = new XSSFWorkbook();
        Sheet productSheet = productInfo.createSheet("Products");

        CellStyle style = productInfo.createCellStyle();

        Font font = productInfo.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 14);

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(font);

        Row row = productSheet.createRow(0);

        Cell id = row.createCell(0);
        id.setCellValue("ID");
        id.setCellStyle(style);

        Cell title = row.createCell(1);
        title.setCellValue("TITLE");
        title.setCellStyle(style);

        Cell category = row.createCell(2);
        category.setCellValue("CATEGORY");
        category.setCellStyle(style);

        Cell price = row.createCell(3);
        price.setCellValue("PRICE");
        price.setCellStyle(style);

        Cell inStock = row.createCell(4);
        inStock.setCellValue("IN STOCK");
        inStock.setCellStyle(style);

        return fillExcelFile(productInfo, style);
    }


    public Workbook fillExcelFile(Workbook productInfo, CellStyle style) {

        List<ProductDto> productList = productService.findAll().stream().map(ProductDto::new).collect(Collectors.toList());

        for (int i = 0; i < productList.size(); i++) {
            Row row = productInfo.getSheet("Products").createRow(i + 1);

            Cell id = row.createCell(0);
            id.setCellValue(productList.get(i).getId());
            id.setCellStyle(style);

            Cell title = row.createCell(1);
            title.setCellValue(productList.get(i).getTitle());
            title.setCellStyle(style);

            Cell category = row.createCell(2);
            category.setCellValue(productList.get(i).getCategoryTitle());
            category.setCellStyle(style);

            Cell price = row.createCell(3);
            price.setCellValue(productList.get(i).getPrice());
            price.setCellStyle(style);

            Cell isStock = row.createCell(4);
            isStock.setCellValue("true");
            isStock.setCellStyle(style);
        }

        productInfo.getSheet("Products").autoSizeColumn(0, true);
        productInfo.getSheet("Products").autoSizeColumn(1, true);
        productInfo.getSheet("Products").autoSizeColumn(2, true);
        productInfo.getSheet("Products").autoSizeColumn(3, true);
        productInfo.getSheet("Products").autoSizeColumn(4, true);
        return productInfo;
    }


}
