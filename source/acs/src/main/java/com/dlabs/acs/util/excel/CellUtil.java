package com.dlabs.acs.util.excel;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;

public class CellUtil {
	public static Integer getInteger(Cell cell)
	{
		Integer result =null;
		try{
			result=Integer.parseInt(cell.getStringCellValue());
		}catch(Exception e)
		{
			try
			{
				result = Double.valueOf(cell.getNumericCellValue()).intValue();
			}catch(Exception ex){}
			
		}
		
		return result;
	}
	
	public static double getDouble(Cell cell)
	{
		double result = 0;
		try{
			result=cell.getNumericCellValue();
		}catch(Exception e)
		{
			try
			{
				result = Double.parseDouble(cell.getStringCellValue());
			}catch(Exception ex){}
			
		}
		
		return result;
	}
	
	public static String getStringIfNumber(Cell cell)
	{
		String result = null;
		try{
			result=cell.getStringCellValue();
		}catch(Exception e)
		{
			try
			{
				NumberFormat nf = new DecimalFormat("###");
				result = nf.format(cell.getNumericCellValue());
			}catch(Exception ex){}
			
		}
		return result;
	}
	public static Date getDate(Cell cell)
	{
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try{
			date = sdf.parse(cell.getStringCellValue());
		}catch(ParseException p){
			p.printStackTrace();
		}catch(Exception e){
			try
			{
				date = cell.getDateCellValue();
			}catch(Exception exception)
			{}
			
		}
		
		return date;
	}
	
	public static Date getDateSlash(Cell cell)
	{
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try{
			date = sdf.parse(cell.getStringCellValue());
		}catch(ParseException p){
			date = null;
		}catch(Exception e){
			try
			{
				date = cell.getDateCellValue();
			}catch(Exception exception)
			{
				date = null;
			}
			
		}
		
		return date;
	}
}
