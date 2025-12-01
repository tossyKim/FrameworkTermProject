package kr.ac.kopo.kyg.projectkyg.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;

    @Column(unique = true)
    private String memberId;

    private String password;
    private String memberName;

    public static Member createMember(MemberFormDto memberFormDto,  PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setMemberId(memberFormDto.getMemberId());
        member.setPassword(passwordEncoder.encode(memberFormDto.getPassword()));
        member.setMemberName(memberFormDto.getMemberName());
        return member;
    }
}
