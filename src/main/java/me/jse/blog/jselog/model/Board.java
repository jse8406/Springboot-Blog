package me.jse.blog.jselog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String title;


    @Lob //대용량 데이터 쓸 때
    @Column(columnDefinition = "longblob")
    private String content;

    private int count;

    // Board와 user 테이블을 join하여 user 정보를 가져오기 위함. ORM은 forign key가 아니라 객체로 가져옴.
    @ManyToOne(fetch = FetchType.EAGER) //Many = Board, One = Member, Eager = 무조건 가져와라
    @JoinColumn(name = "memberId") //실제 DB상에서 User 테이블에 userId로 저장됨. 오브젝트를 적어도 실제 db상엔 FK가 생성됨
    private Member member;

    // 기본 전략이 Lazy이다. 필요할 때 가져오는 전략, Board 정보만 필요한데 그 때마다 join문을 실행하면 낭비라 Lazy 전략을 사용한다.
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedby는 연관관계의 주인이 아니다. (난 FK가 아니에요) DB에 컬럼을 만들지 마세요.
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
