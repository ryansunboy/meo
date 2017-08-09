package com.asiainfo.meo.system.common.app.model.entity;

import java.util.Date;

public class CmEntityParticipant
{
    
    private long partiId;
    private String entityId;
    private Integer entityType;
    private Integer participant;
    private Integer sts;
    private Date createDate;
    private Date modifyDate;

    public static final int STS_ACTIVE = 1;
    // Constructors
    public static final String CM_ENTITY_PARTICIPANT_SEQ = "SEQ_ENTITY_PARTICIPANT_PARI_ID";
    public static final Integer ENTITY_TYPE_CAMPAIGN = 1;

    /** default constructor */
    public CmEntityParticipant() {
    }

    /** minimal constructor */
    public CmEntityParticipant(long partiId, String entityId) {
        this.partiId = partiId;
        this.entityId = entityId;
    }

    /** full constructor */
    public CmEntityParticipant(long partiId, String entityId,
            Integer entityType, Integer participant, Integer sts,
            Date createDate, Date modifyDate) {
        this.partiId = partiId;
        this.entityId = entityId;
        this.entityType = entityType;
        this.participant = participant;
        this.sts = sts;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    // Property accessors

    public long getPartiId() {
        return this.partiId;
    }

    public void setPartiId(long partiId) {
        this.partiId = partiId;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Integer getEntityType() {
        return this.entityType;
    }

    public void setEntityType(Integer entityType) {
        this.entityType = entityType;
    }

    public Integer getParticipant() {
        return this.participant;
    }

    public void setParticipant(Integer participant) {
        this.participant = participant;
    }

    public Integer getSts() {
        return this.sts;
    }

    public void setSts(Integer sts) {
        this.sts = sts;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

}
