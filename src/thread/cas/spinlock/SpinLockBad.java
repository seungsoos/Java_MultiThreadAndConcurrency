package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockBad {

    private volatile boolean lock = false;

    public void Lock() {
        log("락 획득 시도");
        while (true) {
            if (!lock) { // 1. 락 사용여부 확인
                sleep(100); // 문제 상황 확인용
                lock = true;
                break;
            } else {
                // 락획득 까지 스핀 대기
                log("락 획득 실패 - 스핀 대기");
            }
        }
        log("락 획득 완료");
    }

    public void unlock() {
        lock = false;
        log("락 반납 완료");
    }
}
