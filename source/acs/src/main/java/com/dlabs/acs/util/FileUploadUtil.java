package com.dlabs.acs.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class FileUploadUtil
{
	public static byte[] inputStreamToBytes(InputStream is)
	{
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[16384];
		byte[] result = new byte[16384];
		try
		{
			while ((nRead = is.read(data, 0, data.length)) != -1)
			{
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			result = buffer.toByteArray();
			buffer.close();
			buffer = null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public static boolean isNotAllowedExtentions(String fileName, String[] allowedExtentions)
	{
		if (Validator.isNull(fileName))
		{
			return true;
		}
		boolean isNotAllowed = true;
		int lastIndexOfPeriod = fileName.lastIndexOf(StringPool.PERIOD);
		if (lastIndexOfPeriod != -1)
		{
			String videoExtentions = fileName.substring(lastIndexOfPeriod);
			for (String ext : allowedExtentions)
			{
				if (ext.toLowerCase().equals(videoExtentions.toLowerCase()))
				{
					isNotAllowed = false;
					break;
				}
			}

		}
		return isNotAllowed;
	}
}
