package com.dlabs.acs.util.excel.competency;

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
import com.dlabs.acs.entity.competency.CompetencyLibrary;
import com.dlabs.acs.util.StringPool;
import com.dlabs.acs.util.Validator;
import com.dlabs.acs.util.excel.CellUtil;

public class ExcelCompetencyLibrary {
	public static List<CompetencyLibrary> parse(InputStream fis)
	{
		
		List<CompetencyLibrary> list = new ArrayList<CompetencyLibrary>();
		CompetencyLibrary competencyLibrary = null;
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
				competencyLibrary = new CompetencyLibrary();
				competencyLibrary.setCommonFields(commonFields);
				
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					{
						if (cell.getColumnIndex() == 0)
						{
							competencyLibrary.setCategory(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 1)
						{
							competencyLibrary.setCompetencyName(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 2)
						{
							competencyLibrary.setCompetencyNameBahasa(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 3)
						{
							competencyLibrary.setCompetencyDescription(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 4)
						{
							competencyLibrary.setDisplayOrder(CellUtil.getDouble(cell));
						}
						
					}
				}
				if(Validator.isNotNull(competencyLibrary.getCompetencyName() ))
				{
					list.add(competencyLibrary);
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
		InputStream is =ExcelCompetencyLibrary.class.getClassLoader().getResourceAsStream("init/CompetencyLibrary.xlsx");
		
		List<CompetencyLibrary> list = parse(is);
		
		for(CompetencyLibrary competencyLibrary : list)
		{
			System.out.println(competencyLibrary);
		}
	}
}
