package com.hi.app.tools.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MD5UtilsTest {

    private static Logger logger = LoggerFactory.getLogger("MD5UtilsTest");

    @Test
    public void contextMD5() {
        String str = "NZVLNTEXY2ETOTKYNC00ZMFJLTHJZWMTZDQ0M2FKYWE0YZZIMJE2NTIYNTU3MJY0MJU1NG==";
        logger.info(MD5Utils.md5Encode(str));
    }
}
