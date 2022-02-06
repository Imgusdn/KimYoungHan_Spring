package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);                    // sequence번호를 1씩 증가하여 아이디를 지정
        store.put(member.getId(), member);           // Map형식의 store에 입력받은 id를 get으로 담고(Key), save 메소드 실행시 입력받은 member(name)을 Value로 담는다.
        return member;                               // member 클래스에 다시 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));   // id로 회원보기는 store에 .get(id)로 값을 볼 수 있으나,
                                                     // Null값이 있을 수 있으니 Optional의 .ofNullable로 감싸서 반환해주면
                                                     // Null값이 생겨도 반환이 된다 -> 클라이언트에서 따로 작업
    }

    @Override
    public Optional<Member> findByName(String name) {
      return store.values().stream()                                         // store의 values 값을 .filter(람다)를 사용하여 member에서,
                .filter(member -> member.getName().equals(name))        // getName이 파라미터로 넘어온 name과 같은경우에만 필터링이 되고
                .findAny();                                             // 그 결과가 Optional로 반환이 되고 반복을 계속 하면서 찾지못하면 Null이 반환된다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());      // 반환은 반복돌리기 편한 ArrayList로 store의 values값(member)를 반환해준다.
    }

    public void clearStore(){
        store.clear();
    }
}
