package com.dlabs.acs.util.excel.lcb;

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
import com.dlabs.acs.entity.lcb.Lcb;
import com.dlabs.acs.entity.lcb.enumeration.LcbCategory;
import com.dlabs.acs.util.StringPool;
import com.dlabs.acs.util.Validator;
import com.dlabs.acs.util.excel.CellUtil;

public class ExcelLcb {
	public static List<Lcb> parse(InputStream fis)
	{
		
		List<Lcb> list = new ArrayList<Lcb>();
		Lcb lcb = null;
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
				lcb = new Lcb();
				lcb.setCommonFields(commonFields);
				
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					{
						if (cell.getColumnIndex() == 0)
						{
							try
							{
								lcb.setLbCategory(LcbCategory.valueOf(cell.getStringCellValue()));
							}catch(Exception e){}
						}else if (cell.getColumnIndex() == 1)
						{
							lcb.setNumber(CellUtil.getInteger(cell));
						}
						else if(cell.getColumnIndex() == 2)
						{
							lcb.setWeightA(CellUtil.getInteger(cell));
						}
						else if(cell.getColumnIndex() == 3)
						{
							lcb.setWeightB(CellUtil.getInteger(cell));
						}
						else if(cell.getColumnIndex() == 4)
						{
							lcb.setWeightC(CellUtil.getInteger(cell));
						}
						else if(cell.getColumnIndex() == 5)
						{
							lcb.setWeightD(CellUtil.getInteger(cell));
						}
						else if(cell.getColumnIndex() == 6)
						{
							lcb.setWeightE(CellUtil.getInteger(cell));
						}
						
						
						else if(cell.getColumnIndex() == 7)
						{
							lcb.setQuestion(cell.getStringCellValue());
						}
						
						
						else if(cell.getColumnIndex() == 8)
						{
							lcb.setChoiceA(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 9)
						{
							lcb.setChoiceB(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 10)
						{
							lcb.setChoiceC(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 11)
						{
							lcb.setChoiceD(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 12)
						{
							lcb.setChoiceE(cell.getStringCellValue());
						}
						
						
					}
				}
				if(lcb.getLbCategory() != null && Validator.isNotNull(lcb.getChoiceA()) && lcb.getWeightA() != null && lcb.getNumber() > 0)
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
		InputStream is =ExcelLcb.class.getClassLoader().getResourceAsStream("init/Lcb.xlsx");
		
		List<Lcb> list = parse(is);
		
		for(Lcb lcb : list)
		{
			System.out.println(lcb);
		}
	}
}
