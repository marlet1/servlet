package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려*/
public class MemberRepository {
    private static Map<Long,Member> store = new HashMap<>();
    //MemberRepository instance싱글톤으로 해둬서 static Map<Long,Member>여기부분에 static없어도 사용이 가능하다.
    //아이디가 증가하는 시퀀스로 사용할것이다.
    private static long sequence=0L;
    
    //스프링 싱글톤으로 만들기-지금은 스프링컨테이너만 사용하고 왠만하면 톰캣 띄울때만 스프링 컨테이너를 사용하게 된다.
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }
    //MemberRepository(){}싱글톤 만들때는 프라이벳으로 막아야한다. 그래서 아무나 생성하지 못하게 막아줘야 한다.
    private MemberRepository(){
    }
    //멤버 세이브
    public Member save(Member member){
     member.setId(++sequence);
     store.put(member.getId(),member);
     return member;
    }
    //파인드 아이디를 빠르게 찾기
    public Member findById(Long id){
        return store.get(id);
    }
    //스토어에 있는 값에 넘겨준다.
    public List<Member>findAll(){
        //스토어에 모든값을 다꺼네서 새로운 어레이리스트에 담아서 넘겨준다.
        //이렇게 하는 이유 new ArrayList 값을 넣거나 값을 조작해도 스토어의 밸류리스트을 건들고 싶지 않아서 이다.또한 저안에 구간이
        //스토어 밸류를 보호를 하기 위해서 이기도 하다.
        return new ArrayList<>(store.values());
    }
    //클리어스토어에 테스트할때 쓰이긴만 한다.
    public void clearStore(){
        store.clear();
    }
}
