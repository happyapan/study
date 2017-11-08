package com.my.es.test;

import com.my.es.bean.StoreProductBean;
import com.my.es.util.StoreProductUtil;
import com.my.tools.P;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by ccc016 on 2017/4/6.
 */
public class InitDataToES {
    public  static void main(String []args){
        try {
            InputStream xlsFile = new FileInputStream("E:\\TMALL_NOV11.xls");
            HSSFWorkbook catalogs = new HSSFWorkbook(xlsFile);
            StoreProductUtil storeProductUtil=new StoreProductUtil();
            // Read the Sheet
            int i=0;
            for (int numSheet = 0; numSheet < catalogs.getNumberOfSheets(); ) {
                numSheet++;
                HSSFSheet oneCatalog = catalogs.getSheetAt(numSheet);
                P.P("init-------------> "+ oneCatalog.getSheetName());
                // Read the Row
                int productId=numSheet*10000;
                for (int rowNum = 1; rowNum <= oneCatalog.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = oneCatalog.getRow(rowNum);
                    StoreProductBean one=new StoreProductBean();
                    one.setId(""+(productId+rowNum));
                    one.setStoreCode("01");
                    one.setCatalog(getValue(hssfRow.getCell(0)));
                    one.setName(getValue(hssfRow.getCell(1)));
                    one.setListPrice(Float.parseFloat(getNumericValue(hssfRow.getCell(2))));
                    one.setOfferPrice(Float.parseFloat(getNumericValue(hssfRow.getCell(3))));
                    one.setStock(Long.parseLong(getNumericValue(hssfRow.getCell(6))));
                    one.setBrand(getValue(hssfRow.getCell(7)).replace("官方", "").replace("旗舰店", ""));
                    one.setComment(getValue(hssfRow.getCell(8)));
                    i++;
                    P.P(one.getBrand()+"  "+one.getOfferPrice());
                    storeProductUtil.createOneEntity(one);
                }

            }
            P.P(""+i);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static String getValue(HSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }
    private static String getNumericValue(HSSFCell xssfRow) {
        String val=getValue(xssfRow);
        try{
            Double.parseDouble(val)  ;
            return val;
        } catch (Exception e){
            return "0";
        }

    }
}
