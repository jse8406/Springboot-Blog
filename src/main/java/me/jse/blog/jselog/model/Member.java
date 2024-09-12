package me.jse.blog.jselog.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity //MySQL에 테이블 생성
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Oracle은 SEQUENCE, MySQL은 Auto_Increment를 알아서 따라가게함.
    private int id;
    @Column(nullable = false, unique = true, length = 30)
    private  String username;
    @Column(nullable = false, length = 100) //해쉬로 암호화하면 길어질 수 있음
    private String password;
    @Column(nullable = false, length = 50)
    private String email;
    @Enumerated(EnumType.STRING) //DB에는 RoleType이라는 Enum이라는 것이 없다. 그래서 String으로 변환해서 넣어준다.
    private RoleType role; //권한 Enum을 통해 도메인(범위, 정의역)을 정해서 권한을 줄 수 있다.
    @CreationTimestamp //시간 자동 입력
    private Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
}
