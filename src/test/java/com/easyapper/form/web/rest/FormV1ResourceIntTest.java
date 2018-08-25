package com.easyapper.form.web.rest;

import com.easyapper.form.FormapplicationApp;

import com.easyapper.form.domain.FormV1;
import com.easyapper.form.repository.FormV1Repository;
import com.easyapper.form.service.FormV1Service;
import com.easyapper.form.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


import static com.easyapper.form.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.easyapper.form.domain.enumeration.FormType;
/**
 * Test class for the FormV1Resource REST controller.
 *
 * @see FormV1Resource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FormapplicationApp.class)
public class FormV1ResourceIntTest {

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final FormType DEFAULT_FORM_TYPE = FormType.SURVEY;
    private static final FormType UPDATED_FORM_TYPE = FormType.SIGNUP;

    @Autowired
    private FormV1Repository formV1Repository;

    

    @Autowired
    private FormV1Service formV1Service;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restFormV1MockMvc;

    private FormV1 formV1;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FormV1Resource formV1Resource = new FormV1Resource(formV1Service);
        this.restFormV1MockMvc = MockMvcBuilders.standaloneSetup(formV1Resource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormV1 createEntity() {
        FormV1 formV1 = new FormV1()
            .customerId(DEFAULT_CUSTOMER_ID)
            .formType(DEFAULT_FORM_TYPE);
        return formV1;
    }

    @Before
    public void initTest() {
        formV1Repository.deleteAll();
        formV1 = createEntity();
    }

    @Test
    public void createFormV1() throws Exception {
        int databaseSizeBeforeCreate = formV1Repository.findAll().size();

        // Create the FormV1
        restFormV1MockMvc.perform(post("/api/form-v-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formV1)))
            .andExpect(status().isCreated());

        // Validate the FormV1 in the database
        List<FormV1> formV1List = formV1Repository.findAll();
        assertThat(formV1List).hasSize(databaseSizeBeforeCreate + 1);
        FormV1 testFormV1 = formV1List.get(formV1List.size() - 1);
        assertThat(testFormV1.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testFormV1.getFormType()).isEqualTo(DEFAULT_FORM_TYPE);
    }

    @Test
    public void createFormV1WithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formV1Repository.findAll().size();

        // Create the FormV1 with an existing ID
        formV1.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormV1MockMvc.perform(post("/api/form-v-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formV1)))
            .andExpect(status().isBadRequest());

        // Validate the FormV1 in the database
        List<FormV1> formV1List = formV1Repository.findAll();
        assertThat(formV1List).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllFormV1S() throws Exception {
        // Initialize the database
        formV1Repository.save(formV1);

        // Get all the formV1List
        restFormV1MockMvc.perform(get("/api/form-v-1-s?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formV1.getId())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.toString())))
            .andExpect(jsonPath("$.[*].formType").value(hasItem(DEFAULT_FORM_TYPE.toString())));
    }
    

    @Test
    public void getFormV1() throws Exception {
        // Initialize the database
        formV1Repository.save(formV1);

        // Get the formV1
        restFormV1MockMvc.perform(get("/api/form-v-1-s/{id}", formV1.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(formV1.getId()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID.toString()))
            .andExpect(jsonPath("$.formType").value(DEFAULT_FORM_TYPE.toString()));
    }
    @Test
    public void getNonExistingFormV1() throws Exception {
        // Get the formV1
        restFormV1MockMvc.perform(get("/api/form-v-1-s/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateFormV1() throws Exception {
        // Initialize the database
        formV1Service.save(formV1);

        int databaseSizeBeforeUpdate = formV1Repository.findAll().size();

        // Update the formV1
        FormV1 updatedFormV1 = formV1Repository.findById(formV1.getId()).get();
        updatedFormV1
            .customerId(UPDATED_CUSTOMER_ID)
            .formType(UPDATED_FORM_TYPE);

        restFormV1MockMvc.perform(put("/api/form-v-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormV1)))
            .andExpect(status().isOk());

        // Validate the FormV1 in the database
        List<FormV1> formV1List = formV1Repository.findAll();
        assertThat(formV1List).hasSize(databaseSizeBeforeUpdate);
        FormV1 testFormV1 = formV1List.get(formV1List.size() - 1);
        assertThat(testFormV1.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testFormV1.getFormType()).isEqualTo(UPDATED_FORM_TYPE);
    }

    @Test
    public void updateNonExistingFormV1() throws Exception {
        int databaseSizeBeforeUpdate = formV1Repository.findAll().size();

        // Create the FormV1

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFormV1MockMvc.perform(put("/api/form-v-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formV1)))
            .andExpect(status().isBadRequest());

        // Validate the FormV1 in the database
        List<FormV1> formV1List = formV1Repository.findAll();
        assertThat(formV1List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteFormV1() throws Exception {
        // Initialize the database
        formV1Service.save(formV1);

        int databaseSizeBeforeDelete = formV1Repository.findAll().size();

        // Get the formV1
        restFormV1MockMvc.perform(delete("/api/form-v-1-s/{id}", formV1.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FormV1> formV1List = formV1Repository.findAll();
        assertThat(formV1List).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormV1.class);
        FormV1 formV11 = new FormV1();
        formV11.setId("id1");
        FormV1 formV12 = new FormV1();
        formV12.setId(formV11.getId());
        assertThat(formV11).isEqualTo(formV12);
        formV12.setId("id2");
        assertThat(formV11).isNotEqualTo(formV12);
        formV11.setId(null);
        assertThat(formV11).isNotEqualTo(formV12);
    }
}
