package com.avanciale.TGVAIR.DAO;

import com.avancial.socle.exceptions.ASocleException;

public interface IDAO {

	void save(Object t) throws ASocleException ;
	void update (Object t);
	void delete (Object t) ;
}
