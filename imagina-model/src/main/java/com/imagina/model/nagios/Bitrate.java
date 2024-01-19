package com.imagina.model.nagios;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

@Entity
@NamedNativeQuery(
		name = "bitrates", 
		query =		"SELECT obj.object_id as id, obj.name1 as name, stat.status_update_time as time, stat.output as output " 
					+ "FROM nagios_objects obj, nagios_servicestatus stat "
					+ "WHERE obj.name1 LIKE 'N700_%-VDC-PUB' "
					+ "AND obj.object_id = stat.service_object_id", 
		resultSetMapping = "bitrate-map")

@SqlResultSetMapping(
		  name = "bitrate-map",
		  entities = {
		    @EntityResult(
		      entityClass = Bitrate.class,
		      fields = {
		    	  @FieldResult(name = "id", column = "id"),
		    	  @FieldResult(name = "name", column = "name"), 
		    	  @FieldResult(name = "time", column = "time"), 
		    	  @FieldResult(name = "output", column = "output")
			  })
		  }
)
public class Bitrate implements Serializable {

	private static final long	serialVersionUID	= 781175562358557971L;

	private int					id;
	private String				name;
	private Date				time;
	private String				output;

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}
