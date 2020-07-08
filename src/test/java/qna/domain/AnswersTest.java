package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    @Test
    void validateItHasOtherAnswer_invalid() throws CannotDeleteException {
        Answers answers = new Answers();

        User otherUser = new User(11L, "applepickker", "abc112", "cho", "apple11@naver.com");
        Answer answer = new Answer(otherUser, new Question(), "contents");
        answers.add(answer);

        User loginUser = new User(12L, "dal96k", "wjsdnals96", "woomin", "dal96k@hanmail.net");

        assertThatThrownBy(() ->
            answers.validateItHasOtherAnswer(loginUser))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void validateItHasOtherAnswer_valid() throws CannotDeleteException {
        Answers answers = new Answers();

        User loginUser = new User(11L, "dal96k", "wjsdnals96", "woomin", "dal96k@hanmail.net");
        Answer answer = new Answer(loginUser, new Question(), "contents");
        answers.add(answer);

        assertThat(answers.validateItHasOtherAnswer(loginUser)).isEqualTo(true);
    }
}
