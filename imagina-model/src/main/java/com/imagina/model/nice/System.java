package com.imagina.model.nice;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

@Entity
@NamedNativeQuery(
		name = "systems", 
		query =	"SELECT sys.id as id, COUNT(sys.id) as count, sys.code as code, sys.name as name "
				+ "FROM systems sys, servers as srv, streams as str "
				+ "LEFT JOIN streams_instances as str_inst "
				+ "ON str_inst.stream_id=str.id "
				+ "WHERE str.status='AVAILABLE' AND str.is_shared=0 AND str.stream_server_id=srv.id AND "
				+ "str.system_id = sys.id "
				+ "GROUP BY sys.id", 
		resultSetMapping = "system-map")

@SqlResultSetMapping(
		  name = "system-map",
		  entities = {
		    @EntityResult(
		      entityClass = com.imagina.model.nice.System.class,
		      fields = {
		    	  @FieldResult(name = "id", column = "id"),
		    	  @FieldResult(name = "count", column = "count"), 
		    	  @FieldResult(name = "code", column = "code"), 
		    	  @FieldResult(name = "name", column = "name")
			  })
		  }
)
public class System implements Serializable {

	private static final long	serialVersionUID	= -1470934084848599639L;

	private int					count;
	private int					id;
	private String				code;
	private String				name;

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
