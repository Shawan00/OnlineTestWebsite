package com.exam.ptitexam.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.*;

import java.util.ArrayList;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.exam.ptitexam.domain.Question;

@Service
public class uploadFileService {
    public static List<Question> getQuestionsDataFromExcel(InputStream inputStream){
        List<Question> questions = new ArrayList<>();
    
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowIndex = 0;
            for(Row row : sheet) {
                if(rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Question question = new Question();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch(cellIndex) {
                        case 0:
                            question.setQuestionContent(cell.getStringCellValue());
                            break;
                        case 1:
                            question.setCorrectOptionIndex((int)cell.getNumericCellValue());
                            break;
                        case 2:
                            question.setOptionA(cell.getStringCellValue());
                            break;
                        case 3:
                            question.setOptionB(cell.getStringCellValue());
                            break;
                        case 4:
                            question.setOptionC(cell.getStringCellValue());
                            break;
                        case 5:
                            question.setOptionD(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                questions.add(question);
                
            }
        } catch (IOException e) {
          
            e.getStackTrace();
        }
        return questions;
        
    }
}
