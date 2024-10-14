package chaoking.java.allinone.tools;


import chaoking.java.allinone.tools.base.GsonUtil;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chao_w on 2024/10/14.
 */
public class bSubTitles {

    public static void main(String[] args) throws IOException {
       String msg = buildSubTitles();
        System.out.println(msg);
    }

    public static String buildSubTitles() throws IOException {
        StringBuilder sb = new StringBuilder();

        // 文件内容：[
        //    {
        //      "from": 0.3,
        //      "to": 1.77,
        //      "sid": 1,
        //      "location": 2,
        //      "content": "本世纪20年代初",
        //      "music": 0.0
        //    },
        //    {
        //      "from": 1.77,
        //      "to": 4.47,
        //      "sid": 2,
        //      "location": 2,
        //      "content": "随着美国主导地位面临持续挑战",
        //      "music": 0.0
        //    }]
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\resources\\bsubtitles.txt"),"UTF-8"));
        String line;
        StringBuilder lineSb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            if (StringUtils.isNoneBlank(line)) {
                lineSb.append(line);
            }
        }

        List<SubTitle> list = GsonUtil.fromJson(lineSb.toString(), new TypeToken<List<SubTitle>>() {
        }.getType());

        list.forEach(t->{
            sb.append(t.content);
            sb.append("\n");
        });

//        System.out.printf(sb.toString());
        return sb.toString();
    }

    static class SubTitle {
        String from;
        String to;
        String sid;
        String location;
        String content;
        String music;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMusic() {
            return music;
        }

        public void setMusic(String music) {
            this.music = music;
        }
    }
}
