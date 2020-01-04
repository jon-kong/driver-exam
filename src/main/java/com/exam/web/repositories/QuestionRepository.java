/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.web.repositories;

import com.exam.web.models.Question;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author didin
 */
public interface QuestionRepository extends CrudRepository<Question, String> {

}