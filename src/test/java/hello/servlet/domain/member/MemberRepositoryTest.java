package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {
    //만약 스프링 사용하면 싱글톤을 쓸필요가없다. 스프링이 자체 싱글톤해주기 때문이다.
    //MemberRepository.getInstance();이게 지금 싱글톤 되어있는 상황
    MemberRepository memberRepository = MemberRepository.getInstance();

    //테스트 끝나면 테스트 초기화를 깔금하기 위해
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void save(){
        //given
        Member member = new Member("hello", 20);
        //when
        Member saveMember = memberRepository.save(member);
        //then
        //강의에서 해석:멤버리파티토리에서 저장했던 멤버아이디에서 찾은 멤버 찾은 다음에 어썰션스 어설트댓 파인드 멤버는 찾아온 멤버는 같아야 한다.
        //내가쓴 해석: 멤버리파지토리에서 저장했던 멤버아이디 찾은 다음 어설션스.어설트댓 찾은 멤버아이디랑 저장멤버랑 같아야 한다.
        Member findMember = memberRepository.findById(saveMember.getId());
        assertThat(findMember).isEqualTo(saveMember);
    }
    @Test
    void finAll(){
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member1", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1,member2);
    }
}
