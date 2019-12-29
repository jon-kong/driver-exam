/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.web.controllers;

import com.exam.web.models.Option;
import com.exam.web.models.Question;
import com.exam.web.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.security.InvalidParameterException;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 * @author didin
 */
@Controller
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    private static final String [] symbol = {"A","B","C","D","E","F","G","H","I","G","K","L","M","N"};

    @RequestMapping("/list")
    public String product(Model model) {
        Iterable<Question> questions = questionRepository.findAll();
        int no = 0;
        for(Question question : questions){
            question.setNo(String.valueOf(++no));
        }

        model.addAttribute("questions", questions);
        return "list";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String save(Question question) {
        if(question == null){
            throw new InvalidParameterException("参数不能为空");
        }
        question.assertParam();

        question.setId(UUID.randomUUID().toString());
        for(int i = 0;i < question.getOptions().size(); i++){
            Option option = question.getOptions().get(i);

            option.setId(UUID.randomUUID().toString());
            option.setNo(symbol[i]);
        }
        question.setAnswer(question.getOptions().stream().filter(x -> x.getIsAnswer() != null && x.getIsAnswer() == 1).map(x -> x.getNo()).collect(Collectors.joining(",")));

        if(StringUtils.isEmptyOrWhitespace(question.getAnswer())){
            throw new InvalidParameterException("请至少选择一个正确答案");
        }

        questionRepository.save(question);

        return "redirect:/show/" + question.getId();
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        Question question = questionRepository.findOne(id);

        model.addAttribute("question", question);
        return "show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        Question product = questionRepository.findOne(id);
        questionRepository.delete(product);

        return "redirect:/list";
    }
    
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("question", questionRepository.findOne(id));
        return "edit";
    }
    
    @RequestMapping("/update")
    public String update(Question question) {
        if(question == null || StringUtils.isEmptyOrWhitespace(question.getId())){
            throw new InvalidParameterException("参数不能为空");
        }
        question.assertParam();

        if(question == null){
            return null;
        }

        for(int i = 0;i < question.getOptions().size(); i++){
            Option option = question.getOptions().get(i);

            option.setId(UUID.randomUUID().toString());
            option.setNo(symbol[i]);
        }
        question.setAnswer(question.getOptions().stream().filter(x -> x.getIsAnswer() != null && x.getIsAnswer() == 1).map(x -> x.getNo()).collect(Collectors.joining(",")));

        if(StringUtils.isEmptyOrWhitespace(question.getAnswer())){
            throw new InvalidParameterException("请至少选择一个正确答案");
        }
        questionRepository.delete(question.getId());


        question.setId(UUID.randomUUID().toString());
        questionRepository.save(question);

        return "redirect:/show/" + question.getId();
    }
}
