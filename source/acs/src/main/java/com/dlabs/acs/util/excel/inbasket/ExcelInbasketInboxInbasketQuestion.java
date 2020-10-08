package com.dlabs.acs.util.excel.inbasket;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dlabs.acs.dto.inbasket.InbasketInboxInbasketQuestionDto;
import com.dlabs.acs.util.excel.CellUtil;

public class ExcelInbasketInboxInbasketQuestion {
	public static List<InbasketInboxInbasketQuestionDto> parse(InputStream fis)
	{
		
		List<InbasketInboxInbasketQuestionDto> list = new ArrayList<InbasketInboxInbasketQuestionDto>();
		InbasketInboxInbasketQuestionDto inbasketInboxInbasketQuestion = null;
		
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
				inbasketInboxInbasketQuestion = new InbasketInboxInbasketQuestionDto();
				
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
							inbasketInboxInbasketQuestion.setInbasketQuestionQuestionNumber(number);
						}
						else if(cell.getColumnIndex() == 1)
						{
							Integer number = CellUtil.getInteger(cell);
							if(number == null)
							{
								number = 0;
							}
							inbasketInboxInbasketQuestion.setInbasketInboxQuestionNumber(number);
						}
						
						
					}
				}
				if(inbasketInboxInbasketQuestion.getInbasketInboxQuestionNumber() > 0 && inbasketInboxInbasketQuestion.getInbasketQuestionQuestionNumber() > 0)
				{
					list.add(inbasketInboxInbasketQuestion);
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
		InputStream is =ExcelInbasketInboxInbasketQuestion.class.getClassLoader().getResourceAsStream("init/InbasketInboxInbasketQuestion.xlsx");
		
		List<InbasketInboxInbasketQuestionDto> list = parse(is);
		
		for(InbasketInboxInbasketQuestionDto CompetencyLibaryCapDto : list)
		{
			System.out.println(CompetencyLibaryCapDto);
		}
	}
}
