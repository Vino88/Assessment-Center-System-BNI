package com.dlabs.acs.util.excel.lcb;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dlabs.acs.dto.lcb.CompetencyLibraryLcbDto;
import com.dlabs.acs.entity.lcb.enumeration.LcbCategory;
import com.dlabs.acs.util.Validator;
import com.dlabs.acs.util.excel.CellUtil;

public class ExcelCompetencyLibraryLcb {
	
	public static List<CompetencyLibraryLcbDto> parseSheet(XSSFSheet sheet)
	{
		List<CompetencyLibraryLcbDto> list = new ArrayList<CompetencyLibraryLcbDto>();
		CompetencyLibraryLcbDto competencyLibraryLcb = null;
		try
		{
			
			Map<Integer, Integer> mapColumnIndexAndQuestionNumber = new HashMap<Integer, Integer>();

			Iterator<Row> rowIterator = sheet.iterator();

			getPCLbl: while (rowIterator.hasNext())
			{

				Row row = rowIterator.next();
				if (row.getRowNum() == 0)
				{
					Iterator<Cell> headerCellIterator = row.cellIterator();
					
					while (headerCellIterator.hasNext())
					{
						Cell headerCell = headerCellIterator.next();
						if (headerCell.getCellType() == Cell.CELL_TYPE_STRING || headerCell.getCellType() == Cell.CELL_TYPE_NUMERIC)
						{
							Integer questionNumber = CellUtil.getInteger(headerCell);
							if(questionNumber != null)
							{
								mapColumnIndexAndQuestionNumber.put(headerCell.getColumnIndex(), questionNumber);
							}
							
						}
					}
					continue;
				}
				
				
				
				Iterator<Cell> cellIterator = row.cellIterator();
				String value = null;
				String competencyName = null;

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					{
						if(cell.getColumnIndex() == 0)
						{
							competencyName = cell.getStringCellValue();
							continue;
						}
						value = cell.getStringCellValue();
						Integer questionNumber = mapColumnIndexAndQuestionNumber.get(cell.getColumnIndex());
						if(questionNumber != null)
						{
							competencyLibraryLcb = new CompetencyLibraryLcbDto();
							competencyLibraryLcb.setCompetencyName(competencyName);
							competencyLibraryLcb.setQuestionNumber(questionNumber);
							
							if(competencyLibraryLcb.getQuestionNumber() != null && Validator.isNotNull(competencyLibraryLcb.getCompetencyName()) && "x".equals(value))
							{
								list.add(competencyLibraryLcb);
							}
						}
						
						
					}
				}
				
				
			}

		}
		catch (Exception ioe)
		{
			return Collections.EMPTY_LIST;
		}

		return list;
	}
	public static List<CompetencyLibraryLcbDto> parse(InputStream fis)
	{
		List<CompetencyLibraryLcbDto> list = new ArrayList<CompetencyLibraryLcbDto>();
		try
		{
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(LcbCategory.LCB1.toString());
			list.addAll(parseSheet(sheet));
			
			sheet = workbook.getSheet(LcbCategory.LCB2.toString());
			list.addAll(parseSheet(sheet));
			
			sheet = workbook.getSheet(LcbCategory.LCB3.toString());
			list.addAll(parseSheet(sheet));
			
			sheet = workbook.getSheet(LcbCategory.LCB4.toString());
			list.addAll(parseSheet(sheet));

			fis.close();
		}
		catch (IOException ioe)
		{
			return Collections.EMPTY_LIST;
		}

		return list;

	}
	
	
	public static void main(String[] args) {
		InputStream is =ExcelCompetencyLibraryLcb.class.getClassLoader().getResourceAsStream("init/CompetencyLibraryLcb.xlsx");
		
		List<CompetencyLibraryLcbDto> list = parse(is);
		
		for(CompetencyLibraryLcbDto lcb : list)
		{
			System.out.println(lcb);
		}
	}
}
