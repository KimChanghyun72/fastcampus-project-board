package com.fasecampus.projectboard.service;

import com.fasecampus.projectboard.domain.Article;
import com.fasecampus.projectboard.domain.type.SearchType;
import com.fasecampus.projectboard.dto.ArticleDTO;
import com.fasecampus.projectboard.dto.ArticleUpdateDTO;
import com.fasecampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks            // mock을 주입하는 대상
    private ArticleService sut;
    @Mock
    private ArticleRepository articleRepository;

    /*
    *  검색
         각 게시글 페이지로 이동
         페이지네이션
         홈 버튼 -> 게시판 페이지로 리다이렉션
         정렬 기능
        -> 어떻게 그려야 될까. 예전에 js 라이브러리를 사용한 적은 있는데. 데이터를 불러와서 임시로 담은 다음 그걸 정렬하는 식으로 사용했던 걸로 기억한다. Datatables 라이브러리.
    * */

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchArticles_thenReturnsArticleList() {        // 테스트는 우선 실패하는 테스트 -> 소기능 구현 -> 성공 -> 리팩토링 순으로 가야한다.
        // Given
        //SearchParameters param = SearchParameters.of(SearchType.TITLE, "search keyword");

        // When
        Page<ArticleDTO> articles = sut.searchArticles(SearchType.TITLE, "search keyword");       // 제목, 본문, id, 닉네임, 해시태그

        // Then
        assertThat(articles).isNotNull();               // 우선은 notnull만 체크. 성공하면 더 복잡하게(갯수, 더미데이터 등등)
    }

    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchArticle_thenReturnsArticle() {        // 테스트는 우선 실패하는 테스트 -> 소기능 구현 -> 성공 -> 리팩토링 순으로 가야한다.
        // Given

        // When
        ArticleDTO article = sut.searchArticle(1L);

        // Then
        assertThat(article).isNotNull();               // 우선은 notnull만 체크. 성공하면 더 복잡하게(갯수, 더미데이터 등등)
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
        // Given
        ArticleDTO dto = ArticleDTO.of(LocalDateTime.now(), "Uno", "title", "content", "hashtag");
        //willDoNothing().given(articleRepository).save(any(Article.class));
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.saveArticle(dto);

        // Then
        then(articleRepository).should().save(any(Article.class));      // Article 클래스에 대한 save 메소드가 호출되었는지만 확인. (고립 테스트, 유닛 테스트)
                                                                        // 실제 저장되었는지 확인은 레이어를 여러개 써야하기 때문에 고립테스트가 아님.

    }

    @DisplayName("게시글의 ID와 수정 정보를 입력하면, 게시글을 수정한다..")
    @Test
    void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdateArticle() {
        // Given
        ArticleDTO dto = ArticleDTO.of(LocalDateTime.now(), "Uno", "title", "content", "hashtag");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.updateArticle(1L, ArticleUpdateDTO.of("title", "content", "#java"));

        // Then
        then(articleRepository).should().save(any(Article.class));      // Article 클래스에 대한 save 메소드가 호출되었는지만 확인. (고립 테스트, 유닛 테스트)
        // 실제 저장되었는지 확인은 레이어를 여러개 써야하기 때문에 고립테스트가 아님.

    }

    @DisplayName("게시글의 ID를 입력하면 게시글을 삭제한다.")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeleteArticle() {
        // Given
        willDoNothing().given(articleRepository).delete(any(Article.class));        // 이 코드는 없어도 똑같은 동작을 보여줄 것이다?

        // When
        sut.deleteArticle(1L);

        // Then
        then(articleRepository).should().delete(any(Article.class));      // Article 클래스에 대한 save 메소드가 호출되었는지만 확인. (고립 테스트, 유닛 테스트)
        // 실제 저장되었는지 확인은 레이어를 여러개 써야하기 때문에 고립테스트가 아님.

    }


}