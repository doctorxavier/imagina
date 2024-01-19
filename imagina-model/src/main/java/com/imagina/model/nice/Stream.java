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
		name = "streams", 
		query =	"SELECT str.id, str.publish_resource, srv.url, str.multibitrate, MIN(str_inst.bitrate) as bitrate "
				+ "FROM servers as srv, streams as str "
				+ "LEFT JOIN streams_instances as str_inst "
				+ "ON str_inst.stream_id=str.id "
				+ "WHERE str.status='AVAILABLE' AND str.is_shared=0 AND str.stream_server_id=srv.id "
				+ "GROUP BY str.id ", 
		resultSetMapping = "stream-map")

@SqlResultSetMapping(
		  name = "stream-map",
		  entities = {
		    @EntityResult(
		      entityClass = Stream.class,
		      fields = {
		    	@FieldResult(name = "id", column = "id"),
		    	@FieldResult(name = "publishResource", column = "publish_resource"), 
		    	@FieldResult(name = "url", column = "url"),
		    	@FieldResult(name = "multibitrate", column = "multibitrate"), 
		    	@FieldResult(name = "bitrate", column = "bitrate")
			  })
		  }
)
public class Stream implements Serializable {

	private static final long	serialVersionUID	= -8327124425132705937L;

	private int					id;
	private String				publishResource;
	private String				url;
	private int					multibitrate;
	private Integer				bitrate;

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPublishResource() {
		return publishResource;
	}

	public void setPublishResource(String publishResource) {
		this.publishResource = publishResource;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getMultibitrate() {
		return multibitrate;
	}

	public void setMultibitrate(int multibitrate) {
		this.multibitrate = multibitrate;
	}

	public Integer getBitrate() {
		return bitrate;
	}

	public void setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
	}

}
