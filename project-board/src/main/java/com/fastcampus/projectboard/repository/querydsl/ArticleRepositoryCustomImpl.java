package com.fastcampus.projectboard.repository.querydsl;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {
    public ArticleRepositoryCustomImpl() {
        super(Article.class);       // 생성자에 해당하는 클래스를 넣어주면 됨.
    }

    @Override
    public List<String> findAllDistinctHashtags() {
        QArticle article = QArticle.article;

        return from(article)  // 제네릭은 리턴라입의 제네릭과 일치시킬 것.
                .distinct()
                .select(article.hashtag)
                .where(article.hashtag.isNotNull())
                .fetch();

//        JPQLQuery<String> query = from(article)  // 제네릭은 리턴라입의 제네릭과 일치시킬 것.
//                .distinct()
//                .select(article.hashtag)
//                .where(article.hashtag.isNotNull());
//
//        return query.fetch();       //fetch()?
    }
}
