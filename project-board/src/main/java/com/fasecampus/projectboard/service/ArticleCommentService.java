package com.fasecampus.projectboard.service;

import com.fasecampus.projectboard.domain.ArticleComment;
import com.fasecampus.projectboard.repository.ArticleCommentRepository;
import com.fasecampus.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    @Transactional(readOnly = true)
    public List<ArticleComment> searchArticleComment(long articleId) {
        return List.of();
    }
}
