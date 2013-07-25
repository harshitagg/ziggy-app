package org.ei.ziggy.domain.form;

import com.google.gson.Gson;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.ei.ziggy.domain.SyncStatus;

public class FormSubmission {
    private String instanceId;
    private String entityId;
    private String formName;
    private String instance;
    private String version;
    private String serverVersion;
    private SyncStatus syncStatus;

    private FormInstance formInstance;

    public FormSubmission(String instanceId, String entityId, String formName, String instance, String version, SyncStatus syncStatus) {
        this.instanceId = instanceId;
        this.entityId = entityId;
        this.formName = formName;
        this.instance = instance;
        this.version = version;
        this.syncStatus = syncStatus;
    }

    public FormSubmission(String instanceId, String entityId, String formName, String instance, String version, String serverVersion, SyncStatus syncStatus) {
        this.instanceId = instanceId;
        this.entityId = entityId;
        this.formName = formName;
        this.instance = instance;
        this.version = version;
        this.serverVersion = serverVersion;
        this.syncStatus = syncStatus;
    }

    public String instanceId() {
        return instanceId;
    }

    public String entityId() {
        return entityId;
    }

    public String formName() {
        return formName;
    }

    public String instance() {
        return instance;
    }

    public String version() {
        return version;
    }

    public String serverVersion() {
        return serverVersion;
    }

    public SyncStatus syncStatus() {
        return syncStatus;
    }

    public FormSubmission setSyncStatus(SyncStatus syncStatus) {
        this.syncStatus = syncStatus;
        return this;
    }

    public FormSubmission setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
        return this;
    }

    public String getFieldValue(String fieldName) {
        if (formInstance == null) {
            formInstance = new Gson().fromJson(instance, FormInstance.class);
        }
        return formInstance.getFieldValue(fieldName);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o, "version");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "version");
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
