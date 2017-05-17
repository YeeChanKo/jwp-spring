package next.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import next.CannotOperateException;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;

@RunWith(MockitoJUnitRunner.class)
public class QnaServiceTest {

	@Mock
	private QuestionDao questionDao;
	@Mock
	private AnswerDao answerDao;

	private QnaService qnaService;
	private Question q;
	private Answer a;
	private User u;

	@Before
	public void setup() {
		qnaService = new QnaService(questionDao, answerDao);
		q = new Question(0, "id", "title", "contents", new Date(), 2);
		a = new Answer(100, "id", "contents", new Date(), 0);
		u = new User("id", "pw", "javajigi", "java@jigi");
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void questionDoesNotExist() throws Exception {
		qnaService.deleteQuestion(q.getQuestionId(), u);
	}

	@Test(expected = CannotOperateException.class)
	public void questionUserIsNotSame() throws Exception {
		when(questionDao.findById(q.getQuestionId())).thenReturn(q);
		u.setUserId("id2");
		qnaService.deleteQuestion(q.getQuestionId(), u);
	}

	@Test
	public void questionWithoutCommmentCanBeDeleted() throws Exception {
		when(questionDao.findById(q.getQuestionId())).thenReturn(q);
		qnaService.deleteQuestion(q.getQuestionId(), u);
		verify(questionDao).delete(q.getQuestionId());
	}

	@Test
	public void questionWithOwnCommentCanBeDeleted() throws Exception {
		// set question
		q.setCountOfComment(1);
		when(questionDao.findById(q.getQuestionId())).thenReturn(q);
		// set answers on question
		when(answerDao.findAllByQuestionId(q.getQuestionId()))
				.thenReturn(Arrays.asList(a));

		qnaService.deleteQuestion(q.getQuestionId(), u);
		verify(questionDao).delete(q.getQuestionId());
	}

	@Test(expected = CannotOperateException.class)
	public void questionWithOthersCommentCannotBeDeleted() throws Exception {
		// set question
		q.setCountOfComment(1);
		when(questionDao.findById(q.getQuestionId())).thenReturn(q);
		// set answers on question
		a.setWriter("id2");
		when(answerDao.findAllByQuestionId(q.getQuestionId()))
				.thenReturn(Arrays.asList(a));

		qnaService.deleteQuestion(q.getQuestionId(), u);
		verify(questionDao).delete(q.getQuestionId());
	}
}
