package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void validateItIsOtherUser_invalid() {
        Question question = new Question(11L, "wonder", "this contents");
        User otherUser = new User(11L, "applepickker", "abc112", "cho", "apple11@naver.com");
        question.writeBy(otherUser);

        User loginUser = new User(12L, "dal96k", "wjsdnals96", "woomin", "dal96k@hanmail.net");

        assertThatThrownBy(() ->
                question.validateItIsOtherUser(loginUser))
                    .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void validateItIsOtherUser_valid() throws CannotDeleteException {
        Question question = new Question(12, "wonder", "this contents");

        User loginUser = new User(12L, "dal96k", "wjsdnals96", "woomin", "dal96k@hanmail.net");
        question.writeBy(loginUser);

        assertThat(question.validateItIsOtherUser(loginUser)).isEqualTo(true);
    }
}
