package com.dlabs.acs.util.excel.cap;

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
import com.dlabs.acs.entity.cap.Cap;
import com.dlabs.acs.util.StringPool;
import com.dlabs.acs.util.Validator;
import com.dlabs.acs.util.excel.CellUtil;

public class ExcelCap {
	public static List<Cap> parse(InputStream fis)
	{
		
		List<Cap> list = new ArrayList<Cap>();
		Cap cap = null;
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
				cap = new Cap();
				cap.setCommonFields(commonFields);
				
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
							cap.setNumber(number);
						}
						else if(cell.getColumnIndex() == 1)
						{
							cap.setQuestion(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 2)
						{
							cap.setqSituation(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 3)
						{
							cap.setqAction(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 4)
						{
							cap.setqResult(cell.getStringCellValue());
						}
						
					}
				}
				if(Validator.isNotNull(cap.getQuestion() ) && cap.getNumber() > 0)
				{
					list.add(cap);
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
		InputStream is =ExcelCap.class.getClassLoader().getResourceAsStream("init/Cap.xlsx");
		
		List<Cap> list = parse(is);
		
		for(Cap Cap : list)
		{
			System.out.println(Cap);
		}
	}
}
