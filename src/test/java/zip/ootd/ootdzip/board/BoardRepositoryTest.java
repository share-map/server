package zip.ootd.ootdzip.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import zip.ootd.ootdzip.user.User;
import zip.ootd.ootdzip.user.UserGender;
import zip.ootd.ootdzip.user.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("게시글 작성 테스트")
    void writeBoardTest(){
        //given
        String userName = "test user name";
        UserGender gender = UserGender.MALE;
        LocalDate birthdate =LocalDate.of(2000, 1, 1);
        Integer userHeight = 180;
        User writer = userRepository.save(new User(userName,
                gender,
                birthdate,
                userHeight,
                80,
                true,
                null,
                false));

        String contents = "test contents";
        List<BoardImage> boardImages = new ArrayList<>();
        Board board = new Board();

        //when
        board.writeBoard(writer, contents, boardImages);
        Board savedBoard = boardRepository.save(board);
        //then
        assertThat(savedBoard).isEqualTo(board);

    }
}
