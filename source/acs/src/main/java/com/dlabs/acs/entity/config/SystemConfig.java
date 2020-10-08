package com.dlabs.acs.entity.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.dlabs.acs.embeddable.CommonFields;
import com.dlabs.acs.entity.AbstractEntity;

@NamedQueries({
	@NamedQuery(name="SystemConfig.getBySearch", query="FROM SystemConfig where LOWER(module) like :search OR LOWER(systemCode) like :search OR LOWER(systemValue) like :search order by id asc"),
	@NamedQuery(name="SystemConfig.countBySearch", query="select count(*) FROM SystemConfig where LOWER(module) like :search OR LOWER(systemCode) like :search OR LOWER(systemValue) like :search "),
	
	@NamedQuery(name="SystemConfig.getByModuleAndSystemCode", query="FROM SystemConfig where module =:module AND systemCode = :systemCode order by id asc"),
	@NamedQuery(name="SystemConfig.getByModule", query="FROM SystemConfig where module =:module order by id asc"),
})
@Entity
public class SystemConfig extends AbstractEntity{
	@GeneratedValue
	@Id
	private Long id;
	private CommonFields commonFields;
	
	@Column(length=100)
	private String module;
	@Column(length=100)
	private String systemCode;
	@Column(length=100)
	private String systemValue;
	@Column(length=255)
	private String description;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public CommonFields getCommonFields() {
		return commonFields;
	}

	public void setCommonFields(CommonFields commonFields) {
		this.commonFields = commonFields;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getSystemValue() {
		return systemValue;
	}

	public void setSystemValue(String systemValue) {
		this.systemValue = systemValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
