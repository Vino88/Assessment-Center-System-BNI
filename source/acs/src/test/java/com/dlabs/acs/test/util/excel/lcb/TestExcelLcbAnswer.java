package com.dlabs.acs.test.util.excel.lcb;

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

import com.dlabs.acs.dto.assessement.ParticipantLcbDto;
import com.dlabs.acs.entity.assessement.enumeration.LcbAnswer;
import com.dlabs.acs.util.excel.CellUtil;

public class TestExcelLcbAnswer {
	public static List<ParticipantLcbDto> parse(InputStream fis)
	{
		
		List<ParticipantLcbDto> list = new ArrayList<ParticipantLcbDto>();
		ParticipantLcbDto lcb = null;
		
		try
		{
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("KOMPETENCI 1 - mAN.pEOPLE");
//			XSSFSheet sheet = workbook.getSheet("kompetenci 1");
			Iterator<Row> rowIterator = sheet.iterator();

			getPCLbl: while (rowIterator.hasNext())
			{

				Row row = rowIterator.next();
				if (row.getRowNum() == 0)
				{
					continue;
				}
				lcb = new ParticipantLcbDto();
				
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					{
						if (cell.getColumnIndex() == 0)
						{
							lcb.setQuestionNumber(CellUtil.getInteger(cell));
						}else if (cell.getColumnIndex() == 1)
						{
							lcb.setLcbAnswer(LcbAnswer.valueOf(cell.getStringCellValue()));
						}
						
						
					}
				}
				if(lcb.getQuestionNumber() > 0)
				{
					list.add(lcb);
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
		InputStream is =TestExcelLcbAnswer.class.getClassLoader().getResourceAsStream("init/LcbAnswer.xlsx");
		
		List<ParticipantLcbDto> list = parse(is);
		
		for(ParticipantLcbDto lcb : list)
		{
			System.out.println(lcb);
		}
	}
}
