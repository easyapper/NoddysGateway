package com.easyapper.form.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.easyapper.form.domain.FormV1;
import com.easyapper.form.service.FormV1Service;
import com.easyapper.form.web.rest.errors.BadRequestAlertException;
import com.easyapper.form.web.rest.util.HeaderUtil;
import com.easyapper.form.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FormV1.
 */
@RestController
@RequestMapping("/api")
public class FormV1Resource {

    private final Logger log = LoggerFactory.getLogger(FormV1Resource.class);

    private static final String ENTITY_NAME = "formV1";

    private final FormV1Service formV1Service;

    public FormV1Resource(FormV1Service formV1Service) {
        this.formV1Service = formV1Service;
    }

    /**
     * POST  /form-v-1-s : Create a new formV1.
     *
     * @param formV1 the formV1 to create
     * @return the ResponseEntity with status 201 (Created) and with body the new formV1, or with status 400 (Bad Request) if the formV1 has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/form-v-1-s")
    @Timed
    public ResponseEntity<FormV1> createFormV1(@RequestBody FormV1 formV1) throws URISyntaxException {
        log.debug("REST request to save FormV1 : {}", formV1);
        if (formV1.getId() != null) {
            throw new BadRequestAlertException("A new formV1 cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormV1 result = formV1Service.save(formV1);
        return ResponseEntity.created(new URI("/api/form-v-1-s/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /form-v-1-s : Updates an existing formV1.
     *
     * @param formV1 the formV1 to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated formV1,
     * or with status 400 (Bad Request) if the formV1 is not valid,
     * or with status 500 (Internal Server Error) if the formV1 couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/form-v-1-s")
    @Timed
    public ResponseEntity<FormV1> updateFormV1(@RequestBody FormV1 formV1) throws URISyntaxException {
        log.debug("REST request to update FormV1 : {}", formV1);
        if (formV1.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FormV1 result = formV1Service.save(formV1);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, formV1.getId().toString()))
            .body(result);
    }

    /**
     * GET  /form-v-1-s : get all the formV1S.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of formV1S in body
     */
    @GetMapping("/form-v-1-s")
    @Timed
    public ResponseEntity<List<FormV1>> getAllFormV1S(Pageable pageable) {
        log.debug("REST request to get a page of FormV1S");
        Page<FormV1> page = formV1Service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/form-v-1-s");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /form-v-1-s/:id : get the "id" formV1.
     *
     * @param id the id of the formV1 to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the formV1, or with status 404 (Not Found)
     */
    @GetMapping("/form-v-1-s/{id}")
    @Timed
    public ResponseEntity<FormV1> getFormV1(@PathVariable String id) {
        log.debug("REST request to get FormV1 : {}", id);
        Optional<FormV1> formV1 = formV1Service.findOne(id);
        return ResponseUtil.wrapOrNotFound(formV1);
    }

    /**
     * DELETE  /form-v-1-s/:id : delete the "id" formV1.
     *
     * @param id the id of the formV1 to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/form-v-1-s/{id}")
    @Timed
    public ResponseEntity<Void> deleteFormV1(@PathVariable String id) {
        log.debug("REST request to delete FormV1 : {}", id);
        formV1Service.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
