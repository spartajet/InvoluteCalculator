package com.ptb.length.involutecalculator.util;

import android.view.KeyEvent;

/**
 * @description
 * @create 2017-08-12 下午11:57
 * @email spartajet.guo@gmail.com
 */

public class KeyCodeUtil {
    public static char keyCode2Char(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_0:
                return '0';
            case KeyEvent.KEYCODE_1:
                return '1';
            case KeyEvent.KEYCODE_2:
                return '2';
            case KeyEvent.KEYCODE_3:
                return '3';
            case KeyEvent.KEYCODE_4:
                return '4';
            case KeyEvent.KEYCODE_5:
                return '5';
            case KeyEvent.KEYCODE_6:
                return '6';
            case KeyEvent.KEYCODE_7:
                return '7';
            case KeyEvent.KEYCODE_8:
                return '8';
            case KeyEvent.KEYCODE_9:
                return '9';
            case KeyEvent.KEYCODE_DEL:
                return 8;
        }
        return '0';
    }
}
