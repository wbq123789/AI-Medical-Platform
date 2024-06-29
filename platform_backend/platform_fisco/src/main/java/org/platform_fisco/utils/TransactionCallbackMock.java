package org.platform_fisco.utils;

import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: fisco_backend
 * @description: callback 回调类
 * @author: 王贝强
 * @create: 2024-05-11 22:10
 */
public class TransactionCallbackMock extends TransactionCallback {
    private TransactionReceipt transactionReceipt;
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition;

    public TransactionCallbackMock() {
        condition = reentrantLock.newCondition();
    }

    public TransactionReceipt getResult() {
        try {
            reentrantLock.lock();
            while (transactionReceipt == null) {
                condition.awaitUninterruptibly();
            }
            return transactionReceipt;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override
    public void onResponse(TransactionReceipt transactionReceipt) {
        try {
            reentrantLock.lock();
            this.transactionReceipt = transactionReceipt;
            condition.signal();
        } finally {
            reentrantLock.unlock();
        }
    }
}
