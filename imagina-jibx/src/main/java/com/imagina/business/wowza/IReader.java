package com.imagina.business.wowza;

import java.util.List;

import com.imagina.model.wowza.DataCenter;
import com.imagina.model.wowza.WowzaMediaServerPro;

public interface IReader {

	int	NODES	= 8;

	List<WowzaMediaServerPro> getData(DataCenter dataCenter);

}
