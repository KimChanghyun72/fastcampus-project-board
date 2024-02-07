package com.fasecampus.projectboard.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.annotation.Order;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
//@ToString
@ToString(callSuper = true)     // 연관된 extends한 객체까지 들어가서 toString을 찍겠다.
//@EqualsAndHashCode
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Article extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false) @Setter private String title;       // 제목
    @Setter @Column(name = "content", nullable = false, length = 10000) private String content;     // 본문

    @Setter private String hashtag;     // 해시태그

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    protected Article() {}      // 왜 protected? 내용 제공해준다는 문서는 hibernate문서를 말하는건가?

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    // 팩토리 메소드 기법 -> 디자인패턴 중에 있는건데.
    // 오 이건 또 뭐지?
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;       // pattern matching. Java 14에서 나옴.
        return id != null && id.equals(article.id);                 // 동등성 로직. 영속화를 통한 id가 없다면 무조건 다른 객체. id만 같다면 동등한 객체로 간주.
//        if (o == null || getClass() != o.getClass()) return false;
//        Article article = (Article) o;
//        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
