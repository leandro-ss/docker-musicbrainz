package br.com.musicbrain.server.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PassTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(PassTokenUtil.class);

    private static final Integer EXP_PASS = 4;

    private static final Format FORMAT_PASS = new SimpleDateFormat("ddMMyyyy");

    public static String generatePass() {
        return generatePass(new Date());
    }

    public static String generatePass(Date date) {
        logger.info("call generatePass - date:{}",date);

        Integer result = new Integer(0);

        for (Character c : FORMAT_PASS.format(date).toCharArray()) {

            result += Integer.parseInt(c.toString());
        }

        result = (int) Math.pow(result, EXP_PASS);

        logger.debug("return generatePass::{}",result);

        return String.valueOf(result);
    }

    public static Boolean validatePass(String pass) {
        logger.debug("call validatePass - pass:{}",pass);

        return generatePass().equals(pass);
    }
}