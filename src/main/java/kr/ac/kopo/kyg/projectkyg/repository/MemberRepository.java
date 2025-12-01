package kr.ac.kopo.kyg.projectkyg.repository;

import kr.ac.kopo.kyg.projectkyg.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByMemberId(String memberId);
    Member findByMemberId(String memberId);
}
