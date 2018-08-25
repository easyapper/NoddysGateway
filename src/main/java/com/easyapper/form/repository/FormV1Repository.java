package com.easyapper.form.repository;

import com.easyapper.form.domain.FormV1;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the FormV1 entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormV1Repository extends MongoRepository<FormV1, String> {

}
