package com.dlabs.acs.exception;

public class ApplicationException extends BaseException
{
	private String messageKey;

	public ApplicationException(String messageKey)
	{
		super(messageKey);
		this.messageKey = messageKey;
	}

	public ApplicationException(String messageKey, Throwable throwable)
	{
		super(throwable.getMessage(), throwable);
		this.messageKey = messageKey;
	}

	public String getMessageKey()
	{
		return messageKey;
	}

}
