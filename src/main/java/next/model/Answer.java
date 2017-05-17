package next.model;

import java.util.Date;

import next.CannotOperateException;

public class Answer {
	private long answerId;

	private String writer;

	private String contents;

	private Date createdDate;

	private long questionId;

	public Answer(String writer, String contents, long questionId) {
		this(0, writer, contents, new Date(), questionId);
	}

	public Answer(long answerId, String writer, String contents,
			Date createdDate, long questionId) {
		this.answerId = answerId;
		this.writer = writer;
		this.contents = contents;
		this.createdDate = createdDate;
		this.questionId = questionId;
	}

	public boolean isDeletable(Question q) throws CannotOperateException {
		if (!this.writer.equals(q.getWriter())) {
			throw new CannotOperateException("다른 사용자가 추가한 댓글이 존재해 삭제할 수 없습니다.");
		}

		return true;
	}

	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getAnswerId() {
		return answerId;
	}

	public String getWriter() {
		return writer;
	}

	public String getContents() {
		return contents;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public long getTimeFromCreateDate() {
		return this.createdDate.getTime();
	}

	public long getQuestionId() {
		return questionId;
	}

	public boolean isSameUser(User user) {
		return user.isSameUser(this.writer);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (answerId ^ (answerId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (answerId != other.answerId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", writer=" + writer
				+ ", contents=" + contents + ", createdDate=" + createdDate
				+ ", questionId=" + questionId + "]";
	}
}
