package com.wokdsem.koncurrent;

final class CountDownLock {

	private int count;
	private boolean released;

	CountDownLock(int count) {
		this.count = Math.max(0, count);
	}

	synchronized boolean await() throws InterruptedException {
		if (isLockAlive()) {
			wait();
		}
		return !released;
	}

	synchronized void countDown() {
		if (isLockAlive()) {
			if (--count == 0) {
				notifyAll();
			}
		}
	}

	synchronized void releaseLock() {
		if (isLockAlive()) {
			released = true;
			notifyAll();
		}
	}

	private boolean isLockAlive() {
		return !(released || count == 0);
	}

}
