package com.futao.springbootdemo.utils

import com.alibaba.fastjson.JSON
import com.futao.springbootdemo.foundation.LogicException
import com.futao.springbootdemo.foundation.configuration.HttpMessageConverterConfiguration
import com.futao.springbootdemo.model.system.ErrorMessage
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType
import org.apache.commons.codec.binary.Hex
import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.security.MessageDigest
import java.text.DecimalFormat
import java.util.*
import kotlin.reflect.KFunction1

/**
 * @author futao
 * Created on 2018/10/15.
 */

const val salt = "white55"
/**
 * 数据库表名前缀
 */
const val tablePrefix = "futao_"
val commonUtilsLogger = LoggerFactory.getLogger("CommonUtilsKt")

/**
 * md5加密方法
 */
fun String.md5(): String? {
    return if (StringUtils.isNotBlank(this)) {
        val md5Digest = MessageDigest.getInstance("MD5")
        String(Hex.encodeHex(md5Digest.digest(this.toByteArray(Charsets.UTF_8))))
    } else {
        null
    }
}

/**
 * 根据getter或者setter方法获取字段小写名称
 */
fun <T, R> KFunction1<T, R>.getFieldName(): String {
    val name = this.name
    return if (name.startsWith("get") || name.startsWith("set")) {
        name[3].toLowerCase() + name.substring(4)
    } else if (name.startsWith("is")) {
        name[2].toLowerCase() + name.substring(3)
    } else {
        throw LogicException.le(ErrorMessage.LogicErrorMessage.FIELD_NO_GETTER_OR_SETTER)
    }
}

fun uuid(): String {
    return (UUID.randomUUID()).toString().replace("-", "")
}

/**
 * 记录消息队列
 */
fun logMq(): Logger {
    return getLogger("mq")
}

/**
 * 记录http请求
 */
fun logHttp(): Logger {
    return getLogger("http")
}

/**
 * 记录消息发送
 */
fun logSms(): Logger {
    return getLogger("sms")
}

/**
 * 记录支付
 */
fun logPay(): Logger {
    return getLogger("pay")
}

fun getLogger(name: String): Logger {
    return LoggerFactory.getLogger(name)!!
}


/**
 * 获取汉字拼音的首字母
 */
fun hanziToPinyin(chinese: String): String {
    val sb = StringBuffer()
    val chars = chinese.toCharArray()
    val defaultFormat = HanyuPinyinOutputFormat()
    //大小写
    defaultFormat.caseType = HanyuPinyinCaseType.UPPERCASE
    //声调
    defaultFormat.toneType = HanyuPinyinToneType.WITHOUT_TONE
    //以V代表拼音u
    defaultFormat.vCharType = HanyuPinyinVCharType.WITH_V
    for (i in chars.indices) {
        if (chars[i].toInt() > 128) {
            try {
                sb.append(PinyinHelper.toHanyuPinyinStringArray(chars[i], defaultFormat)[0][0])
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } else {
            sb.append(chars[i])
        }
    }

    return sb.toString()
}

/**
 * 将字符串转为两位小数
 */
fun parseStringToTwoDecimalStr(str: String): String? {
    try {
        val double = java.lang.Double.parseDouble(str)
        return DecimalFormat("0.00").format(double)
    } catch (e: Exception) {
        throw LogicException.le(ErrorMessage.LogicErrorMessage.PARSE_TO_DOUBLE_FAIL)
    }
}

fun numVerifyCode(size: Int): String {
    var i = 0
    var sb = StringBuilder()
    while (i < size) {
        i++
        val random = Random()
        sb.append(random.nextInt(9))
    }
    return sb.toString()
}

/**
 * 将JSON String格式化返回
 */
fun String.formatJsonString(): String? {
    return try {
        JSON.toJSONString(JSON.parseObject(this), *HttpMessageConverterConfiguration.SERIALIZER_FEATURES)
    } catch (e: java.lang.Exception) {
        this
    }
}