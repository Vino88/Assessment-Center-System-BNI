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

import com.dlabs.acs.dto.competency.CompetencyLibraryBehaviourDto;
import com.dlabs.acs.util.Validator;
import com.dlabs.acs.util.excel.CellUtil;

public class ExcelCompetencyLibraryBehaviour {
	public static List<CompetencyLibraryBehaviourDto> parse(InputStream fis)
	{
		
		List<CompetencyLibraryBehaviourDto> list = new ArrayList<CompetencyLibraryBehaviourDto>();
		CompetencyLibraryBehaviourDto competencyLibraryBehaviour = null;
	
		
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
				competencyLibraryBehaviour = new CompetencyLibraryBehaviourDto();
				
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
							competencyLibraryBehaviour.setCompetencyName(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 2)
						{
							competencyLibraryBehaviour.setBehaviourLevel(CellUtil.getInteger(cell));
						}
						else if(cell.getColumnIndex() == 3)
						{
							competencyLibraryBehaviour.setBehaviour(cell.getStringCellValue());
						}
						
						
					}
				}
				if(Validator.isNotNull(competencyLibraryBehaviour.getCompetencyName() ))
				{
					list.add(competencyLibraryBehaviour);
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
		InputStream is =ExcelCompetencyLibraryWritingAssitance.class.getClassLoader().getResourceAsStream("init/CompetencyLibraryBehaviour.xlsx");
		
		List<CompetencyLibraryBehaviourDto> list = parse(is);
		
		for(CompetencyLibraryBehaviourDto competencyLibraryBehaviour : list)
		{
			System.out.println(competencyLibraryBehaviour);
		}
	}
}
