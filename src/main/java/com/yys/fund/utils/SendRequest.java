package com.yys.fund.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gongyulian on 2018/5/7.
 */
public class SendRequest {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                //  System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            //  System.out.println(result);
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param map 请求参数，请求参数应该是一个Map<String,Object>。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, Object> map) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(EasyEduUtil.objToJson(map));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static Map getFundDataListOne(String fundCode, long timestamp) {
        try {
            String result = SendRequest.sendGet("https://fund.eastmoney.com/pingzhongdata/" + fundCode + ".js?v=" + timestamp, "");
            String[] resultList = result.split(";");
            Map resultMap = new HashMap();
            for (String resultStr : resultList) {
                if (resultStr.indexOf("Data_netWorthTrend") != -1) {
                    String[] fundDataList = resultStr.split("=");
                    resultMap.put("fundDataStr", JSON.parseArray(fundDataList[1]));
                    Map map = getMaxNetWorth((JSONArray) resultMap.get("fundDataStr"));
                    //最大净值和日期:
                    resultMap.put("maxNetWorth", map.get("maxNetWorth"));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    resultMap.put("maxNetWorthDate", simpleDateFormat.format(new Date(Long.parseLong(String.valueOf(map.get("maxNetWorthDate"))))));
                }
                if (resultStr.indexOf("fS_name") != -1) {
                    String[] fundDataList = resultStr.split("=");
                    resultMap.put("fundName", fundDataList[1].replaceAll("\"",""));
                }
                if (resultStr.indexOf("fS_code") != -1) {
                    String[] fundDataList = resultStr.split("=");
                    resultMap.put("fundCode", fundDataList[1]);
                }


            }
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map getMaxNetWorth(JSONArray jsonArray) {
        String maxNetWorth = "";
        String maxNetWorthDate = "";
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (!"".equals(maxNetWorth) && sub(Double.valueOf(maxNetWorth), jsonObject.getDouble("y")) < 0) {
                maxNetWorth = jsonObject.getString("y");
                maxNetWorthDate = jsonObject.getString("x");
            } else if ("".equals(maxNetWorth)) {
                maxNetWorth = jsonObject.getString("y");
                maxNetWorthDate = jsonObject.getString("x");
            }
        }
        Map map = new HashMap();
        map.put("maxNetWorth", maxNetWorth);
        map.put("maxNetWorthDate", maxNetWorthDate);

        return map;
    }


    private static double sub(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.subtract(b2).doubleValue();
    }

    public static Map getFundDataListTwo(String fundCode, long timestamp) {
        try {
            String result = SendRequest.sendGet("https://fundgz.1234567.com.cn/js/" + fundCode + ".js?rt=" + new Date().getTime() + "&callback=jsonpgz&_=" + new Date().getTime(), "");
            String results = result.split("jsonpgz\\(")[1].split("\\);")[0];
            JSONObject jsonObject = JSONObject.parseObject(results);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String todayStr = simpleDateFormat.format(new Date());
            long today = simpleDateFormat.parse(todayStr).getTime();
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String resultDate = String.valueOf(jsonObject.get("gztime"));
            long netWorthTime = simpleDateFormat2.parse(resultDate).getTime();


            Map fundInfoParam = null;
//            if (netWorthTime > today) {
//                fundInfoParam = new HashMap();
//                fundInfoParam.put("fundInfoCode", fundCode);
//                fundInfoParam.put("fundDay", simpleDateFormat.parse(resultDate.substring(0,10)).getTime());
//                fundInfoParam.put("fundNetWorth", jsonObject.get("gsz"));
//            }
            fundInfoParam = new HashMap();
            fundInfoParam.put("fundInfoCode", fundCode);
            fundInfoParam.put("fundDay", simpleDateFormat.parse(resultDate.substring(0,10)).getTime());
            fundInfoParam.put("fundNetWorth", jsonObject.get("gsz"));
            return fundInfoParam;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Map result = SendRequest.getFundDataListTwo("012414", new Date().getTime());
        System.out.println(result);
    }


}
