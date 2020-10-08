package com.dlabs.acs.util.excel.analysys;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.analysys.Analysys;
import com.dlabs.acs.entity.analysys.AnalysysType;
import com.dlabs.acs.util.StringPool;
import com.dlabs.acs.util.Validator;
import com.dlabs.acs.util.excel.CellUtil;

public class ExcelAnalysys {
	public static List<Analysys> parse(InputStream fis)
	{
		
		List<Analysys> list = new ArrayList<Analysys>();
		Analysys analysys = null;
		Date now = new Date();
		
		CommonFields commonFields = new CommonFields();
		commonFields.setCreatedBy(StringPool.SCHEDULER);
		commonFields.setCreatedDate(now);
		commonFields.setLastModifiedBy(StringPool.SCHEDULER);
		commonFields.setLastModifiedDate(now);
		
		try
		{
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			getPCLbl: while (rowIterator.hasNext())
			{

				Row row = rowIterator.next();
				if (row.getRowNum() == 0)
				{
					continue;
				}
				analysys = new Analysys();
				analysys.setCommonFields(commonFields);
				
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					{
						if (cell.getColumnIndex() == 0)
						{
							Integer number = CellUtil.getInteger(cell);
							if(number == null)
							{
								number = 0;
							}
							analysys.setNumber(number);
						}
						else if(cell.getColumnIndex() == 1)
						{
							analysys.setTitle(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 2)
						{
							try
							{
								analysys.setAnalysysType(AnalysysType.valueOf(cell.getStringCellValue()));
							}catch(Exception e){}
							
						}
						else if(cell.getColumnIndex() == 3)
						{
							analysys.setQuestion(cell.getStringCellValue());
						}
					}
				}
				if(Validator.isNotNull(analysys.getQuestion() ) && analysys.getNumber() > 0)
				{
					list.add(analysys);
				}
				
			}

			fis.close();
		}
		catch (IOException ioe)
		{
			return Collections.EMPTY_LIST;
		}

		return list;

	}
	
	public static void main(String[] args) {
		InputStream is =ExcelAnalysys.class.getClassLoader().getResourceAsStream("init/Analysys.xlsx");
		
		List<Analysys> list = parse(is);
		
		for(Analysys analysys : list)
		{
			System.out.println(analysys);
		}
	}
}
