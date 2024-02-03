package com.fasecampus.projectboard.repository;

import com.fasecampus.projectboard.domain.Article;
import com.fasecampus.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>     // T 안에 있는 모든 필드에 대한 검색기능 추가.
        , QuerydslBinderCustomizer<QArticle> {

    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true);       // listing을 하지 않은 property는 검색에서 제외하도록 설정.
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy); // 검색 필터 설정.
        //bindings.bind(root.title).first(StringExpression::likeIgnoreCase);      // queryString: like 's{v}'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);  // queryString: like '%s{v}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);  // queryString: like '%s{v}%'
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);

    }
}
