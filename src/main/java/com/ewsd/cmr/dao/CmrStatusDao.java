package com.ewsd.cmr.dao;

import com.ewsd.cmr.entity.CmrStatus;

public interface CmrStatusDao {

	CmrStatus findStatusByName(String statusName);

}
