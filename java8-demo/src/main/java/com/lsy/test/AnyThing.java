package com.lsy.test;


import com.lsy.juc.CAS;
import com.tencentcloudapi.aai.v20180522.models.SentenceRecognitionRequest;
import com.tencentcloudapi.aai.v20180522.models.SentenceRecognitionResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.commons.codec.digest.HmacUtils;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


public class AnyThing implements Cloneable {
    ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    int i = 10;
    CAS cas = new CAS();

    @Test
    public void tt() throws InterruptedException {
        new Thread(() -> {
            if (threadLocal.get() == null) {
                i++;
                threadLocal.set(i);
                System.out.println("1111:::" + threadLocal.get());
            } else {
                System.out.println("====" + threadLocal.get());
            }
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            if (threadLocal.get() == null) {
                i++;
                threadLocal.set(i);
                System.out.println("2222:::" + threadLocal.get());
            } else {
                System.out.println(threadLocal.get());
            }
        }).start();

    }

    public static void main(String[] args) throws InterruptedException {
        List<ClassLoadingTest> a = new ArrayList<>();
        ClassLoadingTest aa = new ClassLoadingTest();
        aa.setA(1);
        ClassLoadingTest ss = new ClassLoadingTest();
        ss.setA(1);
        ClassLoadingTest dd = new ClassLoadingTest();
        dd.setA(1);
        ClassLoadingTest ee = new ClassLoadingTest();
        ee.setA(1);
        ClassLoadingTest ff = new ClassLoadingTest();
        ff.setA(1);
        a.add(ff);
        System.out.println(a);
        System.out.println(a.size());
        /*for (ClassLoadingTest classLoadingTest : a) {

        }*/
        for (int i = 0; i < a.size(); i++) {
            if (i == 0)
                a.remove(a.get(i));
        }
        System.out.println(a);
        System.out.println(a.size());
    }

    private volatile ReentrantLock lock = new ReentrantLock();

    @Test
    public void t() throws IOException {
        /*Integer a = 999;
        Integer b = 999;
        System.out.println(a==b);
        System.out.println(a.equals(b));
        Integer c = 99;
        Integer d = 99;
        System.out.println(c==d);*/
        ClassLoadingTest c1 = new ClassLoadingTest();
        ClassLoadingTest c2 = c1;
        System.out.println(c1 == c2);
        final ClassLoadingTest c3 = c1;
        System.out.println(c1 == c3);
    }

    public void t4() throws InterruptedException {
        //Thread.sleep(2000);
        System.out.println(9999);
    }

    public void t2() {
        lock.lock();
        try {
            System.out.println("1111");
            //TimeUnit.SECONDS.sleep(2);
            //Thread.sleep(1000);
            Thread t4 = new Thread(() -> {
                try {
                    t4();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t4.start();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        Float x = Float.valueOf(a);
        Float y = Float.valueOf(b);
        System.out.println(x.equals(y));
    }

    public void t3() {
        lock.lock();
        try {
            System.out.println("2222");
            //TimeUnit.SECONDS.sleep(2);
            //Thread.sleep(1000);
            System.out.println("end   ... ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public List<List<String>> displayTable(List<List<String>> orders) {
//        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        List<List<String>> all = new ArrayList<>();
        List<String> foodName = new ArrayList<>();
        foodName.add("Table");
        foodName.add("Beef Burrito");
        foodName.add("Ceviche");
        foodName.add("Fried Chicken");
        foodName.add("Water");
        all.add(foodName);
        Map<Integer, List<String>> map = new HashMap<>();
        for (int i = 0; i < orders.size(); i++) {
            List<String> list = orders.get(i);
            Integer table = Integer.valueOf(list.get(1));
            String food = list.get(2);
            if (map.get(table) != null) {
                List<String> l = map.get(table);
                order(l, food);
            } else {
                List<String> one = new ArrayList<>();
                one.add(table + "");
                one.add("0");
                one.add("0");
                one.add("0");
                one.add("0");
                order(one, food);
                map.put(table, one);
                all.add(one);
            }
        }
        return all;
    }

    public void order(List<String> list, String food) {
        switch (food) {
            case "Beef Burrito":
                list.set(1, String.valueOf(Integer.valueOf(list.get(1)) + 1));
                break;
            case "Ceviche":
                list.set(2, String.valueOf(Integer.valueOf(list.get(2)) + 1));
                break;
            case "Fried Chicken":
                list.set(3, String.valueOf(Integer.valueOf(list.get(3)) + 1));
                break;
            case "Water":
                list.set(4, String.valueOf(Integer.valueOf(list.get(4)) + 1));
                break;
            default:
                break;
        }
    }

    @Test
    public void test4() {
        Set set = new HashSet();
        String a = "重地";
        String b = "通话";
        System.out.println(a.hashCode() == b.hashCode());
        System.out.println(a.equals(b));
        set.add(a);
        set.add(b);
        set.forEach(x -> System.out.println(x));
    }

    @Test
    public void test5() throws CloneNotSupportedException {
        AnyThing anyThing = new AnyThing();
        AnyThing a = (AnyThing) anyThing.clone();
        System.out.println(anyThing == a);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Test
    public void test11() {
        String s = "[sssss]";
        System.out.println(s.substring(1, s.length() - 1));
    }

    @Test
    public void test12() throws Exception {
//        String publicKey = "PXOyHK6cwzkFTP4SaQzHJ45pIVR6UcT3";
        String publicKey = "Gu5t9xGARNpq86cd98joQYCN3Cozk1qA";
        /*String privateKey = "https://wenzhi.api.qcloud.com/v2/index.php?\n" +
                "Action=LexicalSynonym\n" +
                "&Nonce=345122\n" +
                "&Region=bj\n" +
                "&SecretId=AKIDJSyEFzRcW9WbPwDDwPbWAXeU3KL5YkgL\n" +
                "&Timestamp= " + System.currentTimeMillis() / 1000 +
                "&text=周杰伦结婚";*/
        String privateKey = "GETcvm.api.qcloud.com/v2/index.php?Action=DescribeInstances&Nonce=11886&Region=gz&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA&Timestamp=1465185768&instanceIds.0=ins-09dx96dg&limit=20&offset=0";
        System.out.println("时间戳==" + System.currentTimeMillis() / 1000);
        String hashResult = HmacUtils.hmacSha1Hex(publicKey, privateKey);
        String encodeResult = Base64.getEncoder().encodeToString(hashResult.getBytes());
//        System.out.println(Base64.getUrlEncoder().encodeToString(encodeResult.getBytes()));
        System.out.println(encodeResult);
        String s = "NSI3UqqD99b/UJb4tbG/xZpRW64=";
        System.out.println(Base64.getUrlEncoder().encodeToString(s.getBytes()));
    }


    private final static String CHARSET = "UTF-8";

    public String sign(String s, String key, String method) throws Exception {
        Mac mac = Mac.getInstance(method);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(CHARSET), mac.getAlgorithm());
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(s.getBytes(CHARSET));
        return DatatypeConverter.printBase64Binary(hash);
    }

    public String getStringToSign(TreeMap<String, Object> params) {
        StringBuilder s2s = new StringBuilder("POSTwenzhi.api.qcloud.com/v2/index.php?");
//            StringBuilder s2s = new StringBuilder("GETasr.tencentcloudapi.com/?");
        // 签名时要求对参数进行字典排序，此处用TreeMap保证顺序
        for (String k : params.keySet()) {
            s2s.append(k).append("=").append(params.get(k).toString()).append("&");
        }

        return s2s.toString().substring(0, s2s.length() - 1);
    }

    public String getUrl(TreeMap<String, Object> params) throws Exception {
        StringBuilder url = new StringBuilder("https://wenzhi.api.qcloud.com/v2/index.php?");
//            StringBuilder url = new StringBuilder("https://asr.tencentcloudapi.com/?");
        // 实际请求的url中对参数顺序没有要求
        for (String k : params.keySet()) {
            // 需要对请求串进行urlencode，由于key都是英文字母，故此处仅对其value进行urlencode
            if (k == "Signature") {
                url.append(k).append("=").append(URLEncoder.encode(params.get(k).toString(), CHARSET)).append("&");
                System.out.println(URLEncoder.encode(params.get(k).toString(), CHARSET));
            } else
                url.append(k).append("=").append(params.get(k).toString()).append("&");
        }
        return url.toString().substring(0, url.length() - 1);
    }

    @Test
    public void main() throws Exception {
        TreeMap<String, Object> params = new TreeMap<String, Object>(); // TreeMap可以自动排序
        String key = "PXOyHK6cwzkFTP4SaQzHJ45pIVR6UcT3";
        // 公共参数
        params.put("Nonce", new Random().nextInt(Integer.MAX_VALUE));
        params.put("Timestamp", System.currentTimeMillis() / 1000);
        params.put("SecretId", "AKIDJSyEFzRcW9WbPwDDwPbWAXeU3KL5YkgL");
        params.put("Action", "LexicalSynonym");
        params.put("Region", "ap-beijing");
        // 业务参数
        params.put("text", "狗");

        // 公共参数
        params.put("Signature", sign(getStringToSign(params), key, "HmacSHA1"));
        System.out.println(System.currentTimeMillis() / 1000);





            /*params.put("Nonce", new Random().nextInt(Integer.MAX_VALUE));
            params.put("Timestamp", System.currentTimeMillis() / 1000);
            params.put("SecretId", "AKIDJSyEFzRcW9WbPwDDwPbWAXeU3KL5YkgL");
            params.put("Action", "SentenceRecognition");
            params.put("Version", "2019-06-14");
            params.put("Region", "ap-shanghai");
            // 业务参数
            params.put("ProjectId", 0);
            params.put("SubServiceType", 2);
            params.put("EngSerViceType", "16k");
            params.put("SourceType", 0);
            params.put("VoiceFormat", "wav");
            params.put("UsrAudioKey", "www111");
            params.put("Url", "https://ruskin-1256085166.cos.ap-guangzhou.myqcloud.com/test.wav");
            // 公共参数
            params.put("Signature", sign(getStringToSign(params), key, "HmacSHA1"));*/
        System.out.println(getUrl(params));
//            System.out.println(getUrl(params));
//            String s = HttpUtil.get(getUrl(params));
//            System.out.println(s);

    }

    @Test
    public void test13() throws IOException {
        // 采用语音URL方式调用
        try{
            //重要：<Your SecretId>、<Your SecretKey>需要替换成用户自己的账号信息
            //请参考接口说明中的使用步骤1进行获取。
            Credential cred = new Credential("AKIDJSyEFzRcW9WbPwDDwPbWAXeU3KL5YkgL", "PXOyHK6cwzkFTP4SaQzHJ45pIVR6UcT3");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("https://wenzhi.api.qcloud.com/v2/index.php");
            TreeMap<String, Object> params = new TreeMap<String, Object>(); // TreeMap可以自动排序
            String key = "PXOyHK6cwzkFTP4SaQzHJ45pIVR6UcT3";
            // 公共参数
            params.put("Nonce", new Random().nextInt(Integer.MAX_VALUE));
            params.put("Timestamp", System.currentTimeMillis() / 1000);
            params.put("SecretId", "AKIDJSyEFzRcW9WbPwDDwPbWAXeU3KL5YkgL");
            params.put("Action", "LexicalSynonym");
            params.put("Region", "ap-beijing");
            // 业务参数
            params.put("text", "狗");
//            SentenceRecognitionRequest.fromJsonString()
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    /**
     * Credential cred = new Credential("Your SecretId", "Your SecretKey");
     *
     *             HttpProfile httpProfile = new HttpProfile();
     *             httpProfile.setEndpoint("asr.tencentcloudapi.com");
     *
     *             ClientProfile clientProfile = new ClientProfile();
     *             clientProfile.setHttpProfile(httpProfile);
     *
     *             AsrClient client = new AsrClient(cred, "ap-shanghai", clientProfile);
     *
     *             String params = "{\"ProjectId\":0,\"SubServiceType\":2,\"EngSerViceType\":\"16k\",\"SourceType\":0,\"Url\":\"https://ruskin-1256085166.cos.ap-guangzhou.myqcloud.com/test.wav\",\"VoiceFormat\":\"wav\",\"UsrAudioKey\":\"session-123\"}";
     *             SentenceRecognitionRequest req = SentenceRecognitionRequest.fromJsonString(params, SentenceRecognitionRequest.class);
     *
     *             SentenceRecognitionResponse resp = client.SentenceRecognition(req);
     *
     *             System.out.println(SentenceRecognitionRequest.toJsonString(resp));
     */


}
