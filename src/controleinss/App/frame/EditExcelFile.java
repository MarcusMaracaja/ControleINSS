/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controleinss.App.frame;

/**
 *
 * @author Marcus
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EditExcelFile {
    public static void editCell(String filePath, int rowNumber, int columnNumber, String cellValue, String destinationPath) {
        try {
            // Carregar o arquivo existente
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file);

            // Obter a planilha desejada (por exemplo, a primeira planilha)
            Sheet sheet = workbook.getSheetAt(0);

            // Obter a linha desejada
            Row row = sheet.getRow(rowNumber - 1);
            if (row == null) {
                row = sheet.createRow(rowNumber - 1);
            }

            // Obter a célula desejada
            Cell cell = row.getCell(columnNumber - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);

            // Definir o valor da célula
            cell.setCellValue(cellValue);

            // Salvar as alterações no arquivo
            FileOutputStream out = new FileOutputStream(destinationPath);
            workbook.write(out);
            out.close();

            System.out.println("Valor adicionado com sucesso na célula (" + rowNumber + ", " + columnNumber + ")");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }
        // Versão do método que recebe um int
    public static void editCell(String filePath, int rowNumber, int columnNumber, int cellValue, String destinationPath) {
        String cellStringValue = String.valueOf(cellValue);
        editCell(filePath, rowNumber, columnNumber, cellStringValue, destinationPath);
    }

    public static String getCellContent(String filePath, int rowNumber, int columnNumber) {
        try {
            // Carregar o arquivo existente
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file);

            // Obter a planilha desejada (por exemplo, a primeira planilha)
            Sheet sheet = workbook.getSheetAt(0);

            // Obter a linha desejada
            Row row = sheet.getRow(rowNumber - 1);
            if (row != null) {
                // Obter a célula desejada
                Cell cell = row.getCell(columnNumber - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);

                // Obter o conteúdo da célula
                return cell.getStringCellValue();
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }

        return ""; // Retorna uma string vazia se ocorrer algum erro
    }
    
}
