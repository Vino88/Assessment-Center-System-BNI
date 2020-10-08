package com.dlabs.acs.util.excel.cap;

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

import com.dlabs.acs.dto.cap.CompetencyLibraryCapDto;
import com.dlabs.acs.util.Validator;
import com.dlabs.acs.util.excel.CellUtil;

public class ExcelCompetencyLibraryCap {
	public static List<CompetencyLibraryCapDto> parse(InputStream fis)
	{
		
		List<CompetencyLibraryCapDto> list = new ArrayList<CompetencyLibraryCapDto>();
		CompetencyLibraryCapDto competencyLibraryCompetencyLibaryCapDto = null;
		
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
				competencyLibraryCompetencyLibaryCapDto = new CompetencyLibraryCapDto();
				
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					{
						if (cell.getColumnIndex() == 0)
						{
							competencyLibraryCompetencyLibaryCapDto.setCompetencyName(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 1)
						{
							Integer number = CellUtil.getInteger(cell);
							if(number == null)
							{
								number = 0;
							}
							competencyLibraryCompetencyLibaryCapDto.setNumber(number);
						}
						
						
					}
				}
				if(Validator.isNotNull(competencyLibraryCompetencyLibaryCapDto.getCompetencyName() ) && competencyLibraryCompetencyLibaryCapDto.getNumber() > 0)
				{
					list.add(competencyLibraryCompetencyLibaryCapDto);
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
		InputStream is =ExcelCompetencyLibraryCap.class.getClassLoader().getResourceAsStream("init/CompetencyLibraryCap.xlsx");
		
		List<CompetencyLibraryCapDto> list = parse(is);
		
		for(CompetencyLibraryCapDto CompetencyLibaryCapDto : list)
		{
			System.out.println(CompetencyLibaryCapDto);
		}
	}
}
