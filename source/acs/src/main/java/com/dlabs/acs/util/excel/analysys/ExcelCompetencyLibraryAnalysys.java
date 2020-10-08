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

import com.dlabs.acs.dto.analysys.CompetencyLibraryAnalysysDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.util.StringPool;
import com.dlabs.acs.util.Validator;
import com.dlabs.acs.util.excel.CellUtil;

public class ExcelCompetencyLibraryAnalysys {
	public static List<CompetencyLibraryAnalysysDto> parse(InputStream fis)
	{
		
		List<CompetencyLibraryAnalysysDto> list = new ArrayList<CompetencyLibraryAnalysysDto>();
		CompetencyLibraryAnalysysDto competencyLibraryAnalysys = null;
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
				competencyLibraryAnalysys = new CompetencyLibraryAnalysysDto();
				competencyLibraryAnalysys.setCommonFields(commonFields);
				
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					{
						if (cell.getColumnIndex() == 0)
						{
							competencyLibraryAnalysys.setCompetencyName(cell.getStringCellValue());
						}else if (cell.getColumnIndex() == 1)
						{
							Integer number = CellUtil.getInteger(cell);
							if(number == null)
							{
								number = 0;
							}
							competencyLibraryAnalysys.setNumber(number);
						}
						
					}
				}
				if(Validator.isNotNull(competencyLibraryAnalysys.getCompetencyName() ) && competencyLibraryAnalysys.getNumber() > 0)
				{
					list.add(competencyLibraryAnalysys);
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
		InputStream is =ExcelCompetencyLibraryAnalysys.class.getClassLoader().getResourceAsStream("init/CompetencyLibraryAnalysys.xlsx");
		
		List<CompetencyLibraryAnalysysDto> list = parse(is);
		
		for(CompetencyLibraryAnalysysDto CompetencyLibraryAnalysysDto : list)
		{
			System.out.println(CompetencyLibraryAnalysysDto);
		}
	}
}
