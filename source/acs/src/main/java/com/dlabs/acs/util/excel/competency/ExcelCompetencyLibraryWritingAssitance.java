package com.dlabs.acs.util.excel.competency;

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

import com.dlabs.acs.dto.competency.CompetencyLibraryWritingAssistanceDto;
import com.dlabs.acs.util.Validator;
import com.dlabs.acs.util.excel.CellUtil;

public class ExcelCompetencyLibraryWritingAssitance {
	public static List<CompetencyLibraryWritingAssistanceDto> parse(InputStream fis)
	{
		
		List<CompetencyLibraryWritingAssistanceDto> list = new ArrayList<CompetencyLibraryWritingAssistanceDto>();
		CompetencyLibraryWritingAssistanceDto competencyLibraryWritingAssistance = null;
	
		
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
				competencyLibraryWritingAssistance = new CompetencyLibraryWritingAssistanceDto();
				
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					{
						if (cell.getColumnIndex() == 0)
						{
							
						}
						else if(cell.getColumnIndex() == 1)
						{
							competencyLibraryWritingAssistance.setCompetencyName(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 2)
						{
							competencyLibraryWritingAssistance.setLevel(CellUtil.getInteger(cell));
						}
						else if(cell.getColumnIndex() == 3)
						{
							competencyLibraryWritingAssistance.setDescription(cell.getStringCellValue());
						}
						
						
					}
				}
				if(Validator.isNotNull(competencyLibraryWritingAssistance.getCompetencyName() ))
				{
					list.add(competencyLibraryWritingAssistance);
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
		InputStream is =ExcelCompetencyLibraryWritingAssitance.class.getClassLoader().getResourceAsStream("init/CompetencyLibraryWritingAssistance.xlsx");
		
		List<CompetencyLibraryWritingAssistanceDto> list = parse(is);
		
		for(CompetencyLibraryWritingAssistanceDto competencyLibraryWritingAssistance : list)
		{
			System.out.println(competencyLibraryWritingAssistance);
		}
	}
}
