package com.easyapper.form.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

import com.easyapper.form.domain.enumeration.FormType;

/**
 * A FormV1.
 */
@Document(collection = "formv1")
public class FormV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("customer_id")
    private String customerId;

    @Field("form_type")
    private FormType formType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public FormV1 customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public FormType getFormType() {
        return formType;
    }

    public FormV1 formType(FormType formType) {
        this.formType = formType;
        return this;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FormV1 formV1 = (FormV1) o;
        if (formV1.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), formV1.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FormV1{" +
            "id=" + getId() +
            ", customerId='" + getCustomerId() + "'" +
            ", formType='" + getFormType() + "'" +
            "}";
    }
}
