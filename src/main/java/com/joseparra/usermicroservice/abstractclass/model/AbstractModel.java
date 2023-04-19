package com.joseparra.usermicroservice.abstractclass.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class AbstractModel {
	private Long id;
	private Integer version;
	private Date modificationTimestamp;
	private Date creationTimestamp;
}
