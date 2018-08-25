package com.easyapper.form.service;

import com.easyapper.form.domain.FormV1;
import com.easyapper.form.repository.FormV1Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;
/**
 * Service Implementation for managing FormV1.
 */
@Service
public class FormV1Service {

    private final Logger log = LoggerFactory.getLogger(FormV1Service.class);

    private final FormV1Repository formV1Repository;

    public FormV1Service(FormV1Repository formV1Repository) {
        this.formV1Repository = formV1Repository;
    }

    /**
     * Save a formV1.
     *
     * @param formV1 the entity to save
     * @return the persisted entity
     */
    public FormV1 save(FormV1 formV1) {
        log.debug("Request to save FormV1 : {}", formV1);        return formV1Repository.save(formV1);
    }

    /**
     * Get all the formV1S.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<FormV1> findAll(Pageable pageable) {
        log.debug("Request to get all FormV1S");
        return formV1Repository.findAll(pageable);
    }


    /**
     * Get one formV1 by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Optional<FormV1> findOne(String id) {
        log.debug("Request to get FormV1 : {}", id);
        return formV1Repository.findById(id);
    }

    /**
     * Delete the formV1 by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete FormV1 : {}", id);
        formV1Repository.deleteById(id);
    }
}
