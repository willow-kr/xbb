package com.mysite.xbb;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mysite.xbb.answer.Answer;
import com.mysite.xbb.answer.AnswerRepository;
import com.mysite.xbb.question.Question;
import com.mysite.xbb.question.QuestionRepository;
import com.mysite.xbb.question.QuestionService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootTest
class XbbApplicationTests {
	private static final Logger logger = LogManager.getLogger(XbbApplicationTests.class);

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionService questionService;
	
	@Test
	void contextLoads() {
	}
	
	//@Test
	void testJpa_1() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
	}
	
	//@Test
	void testJpa_2() {
		logger.info("[testJpa_2] Started");
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());
		
		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
		logger.info("[testJpa_2] Ended");
	}
	
	//@Test
	void testJpa_3() {
		Optional<Question> oq = this.questionRepository.findById(1);
		
		logger.info("[testJpa_3] Started");
		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
		logger.info("[testJpa_3] Ended");
	}

	//@Test
	void testJpa_4() {
		logger.info("[testJpa_4] Started");
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(3, q.getId());
		logger.info("[testJpa_4] Ended");
	}
	
	//@Test
	void testJpa_5() {
		logger.info("[testJpa_5] Started");
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(3, q.getId());
		logger.info("[testJpa_5] Ended");
	}
	
	//@Test
	void testJpa_6() {
		logger.info("[testJpa_6] Started");
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
		logger.info("[testJpa_6] Ended");
	}
	
	//@Test
	void testJpa_7() {
		logger.info("[testJpa_7] Started");
		Optional<Question> oq = this.questionRepository.findById(3);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
		logger.info("[testJpa_7] Ended");
	}
	
	//@Test
	void testJpa_8() {
		logger.info("[testJpa_8] Started");
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(3);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
		logger.info("[testJpa_8] Ended");
	}
	
	//@Test
	void testJpa_9() {
		logger.info("[testJpa_9] Started");
		Optional<Question> oq = this.questionRepository.findById(4);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q);;
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
		logger.info("[testJpa_9] Ended");
	}
	
	//@Test
	void testJpa_10() {
		logger.info("[testJpa_10] Started");
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(4, a.getQuestion().getId());
		logger.info("[testJpa_10] Ended");
	}
	
	
	//@Transactional
	//@Test
	void testJpa_11() {
		logger.info("[testJpa_11] Started");
		Optional<Question> oq = this.questionRepository.findById(4);
		assertTrue(oq.isPresent());
		Question q = oq.get();
	
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
		logger.info("[testJpa_11] Ended");
	}
	
	@Test
	void  test_Jpa_12() {
		logger.info("[testJpa_12] Started");
		for(int i=1; i<300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			this.questionService.create(subject, content, null);
		}
		logger.info("[testJpa_12] Ended");
	}
}
