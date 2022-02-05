package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); //회원저장

    Optional<Member> findById(Long id); //아이디로 회원찾기, Optional은 findById가 id를 가져올때 null이 있을 수 있기때문에 null을 그대로 반환하기보다는 Optional로 감싸서 반환한다.

    Optional<Member> findByName(String name); //이름으로 회원찾기
    
    List<Member> findAll(); //모든회원 리스트보기

}
