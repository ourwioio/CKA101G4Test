package com.webond.servicetype.model;

import java.util.List;
import java.util.Map;

public interface ServiceTypeDAO_interface {
	public void insert(ServiceTypeVO serviceType);
	public void delete(ServiceTypeVO serviceType);
	public void update(ServiceTypeVO serviceType);
    public ServiceTypeVO findByPrimaryKey(Integer serviceTypeId);
	public List<ServiceTypeVO> getAll();
}
