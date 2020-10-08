package com.dlabs.acs.util.excel;

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
import com.dlabs.acs.entity.UserAdmin;
import com.dlabs.acs.entity.lcb.enumeration.Role;
import com.dlabs.acs.util.StringPool;

public class ExcelUserAdmin {
	public static List<UserAdmin> parse(InputStream fis)
	{
		
		List<UserAdmin> list = new ArrayList<UserAdmin>();
		UserAdmin userAdmin = null;
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
				userAdmin = new UserAdmin();
				userAdmin.setCommonFields(commonFields);
				
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					{
						if (cell.getColumnIndex() == 0)
						{
							userAdmin.setFullname(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 1)
						{
							userAdmin.setEmail(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 2)
						{
							userAdmin.setUsername(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 3)
						{
							userAdmin.setPassword(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 4)
						{
							userAdmin.setRole(Role.valueOf(cell.getStringCellValue()));
						}
						
						
					}
				}
				if(userAdmin.getUsername() != null && userAdmin.getUsername().trim().length() > 0 && userAdmin.getPassword() != null && userAdmin.getPassword().trim().length() > 0)
				{
					list.add(userAdmin);
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
		InputStream is =ExcelUserAdmin.class.getClassLoader().getResourceAsStream("init/UserAdmin.xlsx");
		
		List<UserAdmin> list = parse(is);
		
		for(UserAdmin keyAction : list)
		{
			System.out.println(keyAction);
		}
	}
}
