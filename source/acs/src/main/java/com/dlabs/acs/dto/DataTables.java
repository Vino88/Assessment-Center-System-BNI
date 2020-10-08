package com.dlabs.acs.dto;

import java.util.List;

public class DataTables<T> extends AbstractDto
{
	public static final int PAGING_MAX_RECORD = 10;
	
	 private Long recordsTotal;
	    private Long recordsFiltered;
	   
	    private List<T> data;

		public Long getRecordsTotal() {
			return recordsTotal;
		}

		public void setRecordsTotal(Long recordsTotal) {
			this.recordsTotal = recordsTotal;
		}

		public Long getRecordsFiltered() {
			return recordsFiltered;
		}

		public void setRecordsFiltered(Long recordsFiltered) {
			this.recordsFiltered = recordsFiltered;
		}

		public List<T> getData() {
			return data;
		}

		public void setData(List<T> data) {
			this.data = data;
		}

}

