package com.adtec.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * File: CirculationTemp
 *
 * @Author 王林柱
 * @Since 2018/3/12 10:50
 * @Ver 1.0
 */
public class CirculationTemp {




    public static void tempExchageData(String str){

        StringBuffer newTemp = new StringBuffer();
        newTemp.append("{");

        // 无序遍历Object节点
        JSONObject tempObj = JSONObject.parseObject(str);
       /* for(Map.Entry<String, Object> entry : tempObj.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }*/

        // 有序遍历Object节点
        LinkedHashMap<String,String> jsonMap = JSONObject.parseObject(str, new TypeReference<LinkedHashMap<String,String>>(){});
        int i = 0;
        for(Map.Entry<String, String> entry : jsonMap.entrySet()){
            i++;
            // 拼接key部分的值
            newTemp.append("\"").append(entry.getKey()).append("\"").append(":");
            if (entry.getKey().equals("nodeDataArray")){
                newTemp.append("[");
                //进行数据替换
                int j = 0;
                JSONArray nodeDataArray = JSONObject.parseArray(entry.getValue());
                for (Object nodeData : nodeDataArray){
                    j++;
                    JSONObject node = (JSONObject) nodeData;
                    if (null != node.get("role")) {
                        LinkedHashMap<String,String> tNode = new LinkedHashMap<>();
                        String key = node.get("key").toString();
                        String text = node.get("text").toString();
                        //String role = node.get("role").toString();
                        String role = "test";
                        String remark = node.get("remark").toString();
                        String loc = node.get("loc").toString();
                        //System.out.println(role + ":" + remark);
                        tNode.put("key",key);
                        tNode.put("text",text);
                        tNode.put("role",role);
                        tNode.put("remark",remark);
                        tNode.put("loc",loc);
                        newTemp.append(JSONObject.toJSON(tNode));
                    }else{
                        newTemp.append(node.toJSONString());
                    }
                    if (j < nodeDataArray.size()) {
                        newTemp.append(",");
                    }else{
                        newTemp.append("]");
                    }
                }
                newTemp.append(",");
                continue;
            }
            if ( entry.getValue().indexOf("[") == 0 || entry.getValue().indexOf("{") == 0){
                newTemp.append(entry.getValue());
            }else{
                newTemp.append("\"").append(entry.getValue()).append("\"");
            }
            if (i<jsonMap.size()) {
                newTemp.append(",");
            }else{
                newTemp.append("}");
            }
        }
        System.out.println(newTemp.toString());
    }

    public static void main(String[] args) {
        String str = "{\"class\":\"go.GraphLinksModel\",\"modelData\":{\"position\":\"-5 -5\"},\"nodeDataArray\":[{\"key\":\"1\",\"text\":\"开始\",\"figure\":\"Circle\",\"fill\":\"#4fba4f\",\"stepType\":1,\"loc\":\"90 110\"},{\"key\":\"2\",\"text\":\"结束\",\"figure\":\"Circle\",\"fill\":\"#CE0620\",\"stepType\":4,\"loc\":\"840 110\"}, {\"key\":\"3\",\"text\":\"发起\",\"role\":\"门户投配/理财师\",\"remark\":\"胡浪\",\"loc\":\"180 160\"},{\"key\":\"4\",\"text\":\"审核\",\"role\":\"运营管理岗\",\"remark\":\"赫鑫\",\"loc\":\"280 160\"},{\"key\":\"5\",\"text\":\"勾兑\",\"role\":\"托管岗\",\"remark\":\"张琪\",\"loc\":\"380 160\"},{\"key\":\"6\",\"text\":\"出具确认函\",\"role\":\"运营管理岗\",\"remark\":\"杜三盛\",\"loc\":\"510 160\"},{\"key\":\"7\",\"text\":\"入账\",\"role\":\"托管岗\",\"remark\":\"王林柱\",\"loc\":\"630 160\"},{\"key\":\"8\",\"text\":\"确认\",\"role\":\"运营管理岗\",\"remark\":\"张静静\",\"loc\":\"730 160\"}],\"linkDataArray\": [{\"from\":\"1\",\"to\":\"3\"},{\"from\":\"3\",\"to\":\"4\"},{\"from\":\"4\",\"to\":\"5\"}, {\"from\":\"5\",\"to\":\"6\"}, {\"from\":\"6\",\"to\":\"7\"}, {\"from\":\"7\",\"to\":\"8\"}, {\"from\":\"8\",\"to\":\"2\"}, {\"from\":\"4\",\"to\":\"1\",\"text\":\"已退回\"},{\"from\":\"4\",\"to\":\"2\",\"text\":\"已拒绝\"}]}";
        tempExchageData(str);
    }

}
