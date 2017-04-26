package com.mektec.common;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class UID {
	private static AtomicInteger sn = new AtomicInteger(0);
	public static final String generate(String prefix) {
		return prefix + generate();
	}
	public static final String generate() {
		final String RANGE = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ";
		final int BASE = 34;
		int n = sn.getAndIncrement() % 1000;

		long id = new Date().getTime() * 1000 + n;
		String idStr="";
		//10进制 to 34进制转换
		while (id > 0) {
			idStr = RANGE.charAt((int)(id % BASE)) + idStr;
			id = id / BASE;
		}

        if(n >= 1000000) {
            sn.set(0);
        }
		return idStr;
	}
}
