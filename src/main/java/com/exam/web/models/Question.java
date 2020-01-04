/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.web.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import java.security.InvalidParameterException;
import java.util.List;

/**
 *考试题目
 */
@Document(collection = "questions")
public class Question {

    @Id
    String id;
    String no;
    String topic;
    String answer;
    List<Option> options;
    String pic;
    String analysis;

    public void assertParam(){
        if(StringUtils.isEmptyOrWhitespace(topic) || CollectionUtils.isEmpty(options)){
            throw new InvalidParameterException("题目和选项内容不能为空");
        }
    }


    public Question() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
