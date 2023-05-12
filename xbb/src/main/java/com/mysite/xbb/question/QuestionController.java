package com.mysite.xbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import com.mysite.xbb.answer.AnswerForm;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	private static final Logger logger = LogManager.getLogger(QuestionController.class);
	private final QuestionService questionService;
	/*
	private final QuestionRepository questionRepository;

	@GetMapping("/question/list")
	//@ResponseBody
	public String list(Model model) {
		List<Question> questionList = this.questionRepository.findAll();
		model.addAttribute("questionList", questionList);
		return "question_list";
	}
	*/
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<Question> paging = this.questionService.getList(page);
		
		model.addAttribute("paging", paging);
		return "question_list";
	}

	@GetMapping(value="/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		logger.info("=====> [" + questionForm.getSubject() + "]");
		logger.info("=====> [" + questionForm.getContent() + "]");
		return "question_form";
	}
	
	/*
	@PostMapping("/create")
	public String questionCreate(@RequestParam String subject, @RequestParam String content) {
		this.questionService.create(subject, content);
		return "redirect:/question/list";
	}
	*/
	
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		this.questionService.create(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list";
	}
	
}
