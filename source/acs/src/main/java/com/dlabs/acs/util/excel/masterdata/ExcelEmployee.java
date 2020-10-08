package com.dlabs.acs.util.excel.masterdata;

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

import com.dlabs.acs.dto.masterdata.EmployeeDto;
import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.exception.ApplicationException;
import com.dlabs.acs.util.StringPool;
import com.dlabs.acs.util.Validator;
import com.dlabs.acs.util.excel.CellUtil;

public class ExcelEmployee {
	public static List<EmployeeDto> parse(InputStream fis)
	{
		
		List<EmployeeDto> list = new ArrayList<EmployeeDto>();
		
		List<EmployeeDto> listFailed = new ArrayList<EmployeeDto>();
		EmployeeDto employee = null;
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
				employee = new EmployeeDto();
				
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					{
						if (cell.getColumnIndex() == 0)
						{
							employee.setNik(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 1)
						{
							employee.setEmail(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 2)
						{
							employee.setFullName(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 3)
						{
							employee.setPlaceOfBirth(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 4)
						{
							employee.setDateOfBirth(CellUtil.getDateSlash(cell));
						}
						else if(cell.getColumnIndex() == 5)
						{
							employee.setPhone(CellUtil.getStringIfNumber(cell));
						}
						else if(cell.getColumnIndex() == 6)
						{
							employee.setWorkingArea(cell.getStringCellValue());
						}
						
						else if(cell.getColumnIndex() == 7)
						{
							employee.setCurrentPositionName(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 8)
						{
							employee.setCurrentPeriode(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 9)
						{
							employee.setCurrentResponsibility(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 10)
						{
							employee.setCurrentDirectSupervisor(cell.getStringCellValue());
						}
						
						else if(cell.getColumnIndex() == 11)
						{
							employee.setCurrentSupervisorEmail(cell.getStringCellValue());
						}
					
						
					}
				}
				if(employee.getNik() != null && employee.getNik().trim().length() > 0 && Validator.isNotNull(employee.getEmail()) && Validator.isNotNull(employee.getFullName())
						&& Validator.isNotNull(employee.getPlaceOfBirth()) && employee.getDateOfBirth() != null
						)
				{
					employee.setNik(employee.getNik().trim());
					employee.setEmail(employee.getEmail().trim());
					employee.setFullName(employee.getFullName().trim());
					
					
					list.add(employee);
					
					if(list.size() > 100)
					{
						throw new ApplicationException("Maximum data uploaded is 100 records");
					}
				}
				else
				{
					listFailed.add(employee);
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
	
	
	
	
	public static List<EmployeeDto> parseWithFailed(InputStream fis)
	{
		
		List<EmployeeDto> list = new ArrayList<EmployeeDto>();
		
		List<EmployeeDto> listFailed = new ArrayList<EmployeeDto>();
		EmployeeDto employee = null;
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
				employee = new EmployeeDto();
				
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					{
						if (cell.getColumnIndex() == 0)
						{
							employee.setNik(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 1)
						{
							employee.setEmail(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 2)
						{
							employee.setFullName(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 3)
						{
							employee.setPlaceOfBirth(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 4)
						{
							employee.setDateOfBirth(CellUtil.getDateSlash(cell));
						}
						else if(cell.getColumnIndex() == 5)
						{
							employee.setPhone(CellUtil.getStringIfNumber(cell));
						}
						else if(cell.getColumnIndex() == 6)
						{
							employee.setWorkingArea(cell.getStringCellValue());
						}
						
						else if(cell.getColumnIndex() == 7)
						{
							employee.setCurrentPositionName(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 8)
						{
							employee.setCurrentPeriode(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 9)
						{
							employee.setCurrentResponsibility(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex() == 10)
						{
							employee.setCurrentDirectSupervisor(cell.getStringCellValue());
						}
						
						else if(cell.getColumnIndex() == 11)
						{
							employee.setCurrentSupervisorEmail(cell.getStringCellValue());
						}
					
						
					}
				}
				if(employee.getNik() != null && employee.getNik().trim().length() > 0 && Validator.isNotNull(employee.getEmail()) && Validator.isNotNull(employee.getFullName())
						&& Validator.isNotNull(employee.getPlaceOfBirth()) && employee.getDateOfBirth() != null
						)
				{
					employee.setNik(employee.getNik().trim());
					employee.setEmail(employee.getEmail().trim());
					employee.setFullName(employee.getFullName().trim());
					
					
					list.add(employee);
					
					if(list.size() > 100)
					{
						throw new ApplicationException("Maximum data uploaded is 100 records");
					}
				}
				else
				{
					listFailed.add(employee);
				}
				
			}

			fis.close();
		}
		catch (IOException ioe)
		{
			return Collections.EMPTY_LIST;
		}

		return listFailed;

	}
}
