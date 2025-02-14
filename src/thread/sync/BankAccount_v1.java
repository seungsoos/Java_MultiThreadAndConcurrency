package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccount_v1 implements BankAccount{

    private int balance;

    public BankAccount_v1(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());

        // 잔고가 출금액보다 적으면 false
        log("[출금 시작] 잔고 = " + balance + ", 출금액 = " + amount);
        if (balance < amount) {
            log("[출금 실패] 잔고 = " + balance + ", 출금액 = " + amount);
            return false;
        }
        sleep(1000);
        balance = balance - amount;
        log("[출금 종료] 잔고 = " + balance + ", 출금액 = " + amount);

        log("거래 종료 : " + getClass().getSimpleName());
        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
