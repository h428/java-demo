package com.hao.demo.netty.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DebugUtil {

    public static SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

    public static String time() {
        return sdf.format(new Date());
    }

    public static String bytesToString(ByteBuffer byteBuffer) {
        byteBuffer.flip();
        String out = Charset.defaultCharset().decode(byteBuffer).toString();
        byteBuffer.compact();
        return out;
    }
}
