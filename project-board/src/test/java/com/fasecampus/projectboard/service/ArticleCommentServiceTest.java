package com.fasecampus.projectboard.service;

import com.fasecampus.projectboard.domain.Article;
import com.fasecampus.projectboard.domain.ArticleComment;
import com.fasecampus.projectboard.repository.ArticleCommentRepository;
import com.fasecampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks
    private ArticleCommentService sut;

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleCommentRepository articleCommentRepository;

    @DisplayName("게시글 ID로 조회하면 해당 게시글의 댓글 목록을 불러온다.")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsArticleComments() {
        // Given
        long articleId = 1L;

        given(articleRepository.findById(articleId))
                .willReturn(Optional.of(
                        Article.of("title", "content", "#java")
                ));

        // When
        List<ArticleComment> articleComments = sut.searchArticleComment(articleId);

        // Then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")  // crud 테스트 코드는 강의와 별도로 작성함. -> 정리할 때 입력해볼 것.
    @Test
    void givenArticleId_when_then() {

    }

}